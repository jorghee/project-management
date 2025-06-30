import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import apiService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import EmployeeForm from './EmployeeForm';
import EmployeeTable from './EmployeeTable';

const API_ENDPOINT = '/employees';
const POSITIONS_ENDPOINT = '/positions';
const EXPERIENCE_LEVELS_ENDPOINT = '/experience-levels';

const initialFormState = { id: '', name: '', status: 'A', positionId: '', experienceLevelId: '' };

const EmployeePage = () => {
  const [records, setRecords] = useState([]);
  const [positions, setPositions] = useState([]);
  const [experienceLevels, setExperienceLevels] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const { addNotification } = useNotifier();

  const fetchData = useCallback(async () => {
    setIsLoading(true);
    try {
      const [employeesData, positionsData, levelsData] = await Promise.all([
        apiService.getAll(API_ENDPOINT),
        apiService.getAll(POSITIONS_ENDPOINT),
        apiService.getAll(EXPERIENCE_LEVELS_ENDPOINT),
      ]);
      setRecords(employeesData.content); // Extraemos el contenido de la página
      setPositions(positionsData);
      setExperienceLevels(levelsData);
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
      addNotification('Por favor, seleccione un registro para modificar.', 'warning');
      return;
    }

    const formRecord = {
        ...selectedRecord,
        positionId: selectedRecord.positionId,
        experienceLevelId: selectedRecord.experienceLevelId
    };
    setFormData(formRecord);
    setIsEditing(true);
  };

  const handleFormChange = (e) => {
    const { name, value, type } = e.target;
    const val = type === 'number' ? (value === '' ? '' : parseInt(value, 10)) : value;
    setFormData((prev) => ({ ...prev, [name]: val }));
  };

  const handleSave = async (e) => {
    e.preventDefault();
    if (!formData.id || !formData.name || !formData.positionId || !formData.experienceLevelId) {
      addNotification('Todos los campos, incluyendo selecciones, son obligatorios.', 'error');
      return;
    }
    setIsLoading(true);

    try {
      const requestData = {
        name: formData.name,
        status: formData.status,
        positionId: formData.positionId,
        experienceLevelId: formData.experienceLevelId
      };

      if (isEditing) {
        await apiService.update(API_ENDPOINT, formData.id, requestData);
        addNotification('Empleado actualizado con éxito.', 'success');
      } else {
        requestData.id = formData.id;
        await apiService.create(API_ENDPOINT, requestData);
        addNotification('Empleado creado con éxito.', 'success');
      }
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
      addNotification('Por favor, seleccione un empleado para eliminar.', 'warning');
      return;
    }
    if (window.confirm(`¿Está seguro de que desea eliminar al empleado #${selectedRecord.id}?`)) {
      setIsLoading(true);
      try {
        const recordToDelete = { ...selectedRecord, status: '*' };
        await apiService.update(API_ENDPOINT, selectedRecord.id, {
            name: recordToDelete.name,
            status: recordToDelete.status,
            positionId: recordToDelete.positionId,
            experienceLevelId: recordToDelete.experienceLevelId
        });
        addNotification('Empleado eliminado lógicamente.', 'success');
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
          <h1 className="text-3xl font-bold text-gray-800">Gestión de Empleados</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">← Volver al Menú</Link>
        </div>

        <EmployeeForm
          formData={formData}
          handleFormChange={handleFormChange}
          handleSubmit={handleSave}
          isLoading={isLoading}
          isEditing={isEditing}
          positions={positions}
          experienceLevels={experienceLevels}
        />
        
        <div className="flex flex-wrap gap-3 mb-6">
          <button onClick={handleSave} disabled={isLoading} className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 disabled:bg-blue-400">
            {isEditing ? 'Guardar Cambios' : 'Crear Empleado'}
          </button>
          <button onClick={handleModify} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 rounded-md hover:bg-gray-300 disabled:bg-gray-100 disabled:text-gray-400">Modificar</button>
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading || selectedRecord?.status === '*'} className="px-4 py-2 text-sm font-medium text-red-700 bg-red-100 rounded-md hover:bg-red-200 disabled:bg-gray-100 disabled:text-gray-400">Eliminar</button>
          <button type="button" onClick={handleClear} className="ml-auto px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">Limpiar Formulario</button>
        </div>

        <EmployeeTable
          records={records}
          handleSelectRecord={setSelectedRecord}
          selectedRecordId={selectedRecord?.id}
          isLoading={isLoading}
        />
      </div>
    </div>
  );
};

export default EmployeePage;
