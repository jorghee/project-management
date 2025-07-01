import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import apiService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import AvailabilityForm from './AvailabilityForm';
import AvailabilityTable from './AvailabilityTable';

const API_ENDPOINT = '/availabilities';
const EMPLOYEES_ENDPOINT = '/employees';

const initialFormState = { 
  employeeId: '',
  availabilityStatus: 'D', // D: Disponible, O: Ocupado, V: Vacaciones
  weeklyCapacity: 40,
  currentLoad: 0,
  status: 'A'
};

const AvailabilityPage = () => {
  const [records, setRecords] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const { addNotification } = useNotifier();

  const fetchData = useCallback(async () => {
    setIsLoading(true);
    try {
      // Para relaciones 1-a-1, es común que la API de disponibilidad devuelva una lista de todos los estados.
      // Si no hay un endpoint getAll, se podría obtener la lista de empleados y luego buscar la disponibilidad de cada uno.
      // Asumiremos un endpoint getAll para Availabilities para simplificar.
      const [availabilitiesData, employeesData] = await Promise.all([
        apiService.getAll(API_ENDPOINT),
        apiService.getAll(EMPLOYEES_ENDPOINT)
      ]);
      setRecords(availabilitiesData);
      setEmployees(employeesData.content || employeesData);
    } catch (error) {
      addNotification(`Error al cargar datos: ${error.message}`, 'error');
    } finally {
      setIsLoading(false);
    }
  }, [addNotification]);

  useEffect(() => {
    fetchData();
  }, [fetchData]);
  
  const handleClear = () => {
    setFormData(initialFormState);
    setSelectedRecord(null);
    setIsEditing(false);
  };

  const handleModify = () => {
    if (!selectedRecord) {
      addNotification('Por favor, seleccione un registro de disponibilidad para modificar.', 'warning');
      return;
    }
    setFormData(selectedRecord);
    setIsEditing(true);
  };

  const handleFormChange = (e) => {
    const { name, value, type } = e.target;
    const val = type === 'number' ? (value === '' ? '' : parseInt(value, 10)) : value;
    setFormData((prev) => ({ ...prev, [name]: val }));
  };

  const handleSave = async (e) => {
    e.preventDefault();
    if (!formData.employeeId) {
      addNotification('Debe seleccionar un empleado.', 'error');
      return;
    }
    setIsLoading(true);

    const requestData = {
      availabilityStatus: formData.availabilityStatus,
      weeklyCapacity: parseInt(formData.weeklyCapacity, 10),
      currentLoad: parseInt(formData.currentLoad, 10),
      status: formData.status,
    };
    
    const employeeId = parseInt(formData.employeeId, 10);

    try {
      // En una relación 1-a-1 con PK=FK, PUT se usa tanto para crear como para actualizar.
      await apiService.update(`${API_ENDPOINT}/${employeeId}`, null, requestData); // El segundo arg de update es el ID, pero aquí no es necesario en el cuerpo.
      addNotification(`Disponibilidad para el empleado #${employeeId} guardada con éxito.`, 'success');
      
      handleClear();
      await fetchData();
    } catch (error) {
      addNotification(`Error al guardar: ${error.message}`, 'error');
    } finally {
      setIsLoading(false);
    }
  };
  
  const handleDelete = async () => {
    if (!selectedRecord) {
      addNotification('Por favor, seleccione un registro para eliminar.', 'warning');
      return;
    }
    if (window.confirm(`¿Está seguro de que desea eliminar la disponibilidad del empleado #${selectedRecord.employeeId}?`)) {
      setIsLoading(true);
      try {
        await apiService.remove(API_ENDPOINT, selectedRecord.employeeId);
        addNotification('Registro de disponibilidad eliminado con éxito.', 'success');
        handleClear();
        await fetchData();
      } catch (error) {
        addNotification(`Error al eliminar: ${error.message}`, 'error');
      } finally {
        setIsLoading(false);
      }
    }
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <div className="max-w-7xl mx-auto bg-white p-8 rounded-lg shadow-lg">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-3xl font-bold text-gray-800">Gestión de Disponibilidad de Empleados</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">← Volver al Menú</Link>
        </div>

        <AvailabilityForm
          formData={formData}
          handleFormChange={handleFormChange}
          handleSubmit={handleSave}
          isLoading={isLoading}
          isEditing={isEditing}
          employees={employees}
          existingAvailabilityIds={records.map(r => r.employeeId)}
        />
        
        <div className="flex flex-wrap gap-3 mb-6">
          <button onClick={handleSave} disabled={isLoading} className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 disabled:bg-blue-400">
            {isEditing ? 'Guardar Cambios' : 'Crear/Actualizar Disponibilidad'}
          </button>
          <button onClick={handleModify} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 rounded-md hover:bg-gray-300 disabled:bg-gray-100 disabled:text-gray-400">Modificar</button>
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-red-700 bg-red-100 rounded-md hover:bg-red-200 disabled:bg-gray-100 disabled:text-gray-400">Eliminar</button>
          <button type="button" onClick={handleClear} className="ml-auto px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">Limpiar Formulario</button>
        </div>

        <AvailabilityTable
          records={records}
          handleSelectRecord={setSelectedRecord}
          selectedRecordId={selectedRecord?.employeeId}
          isLoading={isLoading}
        />
      </div>
    </div>
  );
};

export default AvailabilityPage;
