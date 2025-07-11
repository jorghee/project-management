import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import apiService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import ActivityForm from './ActivityForm';
import ActivityTable from './ActivityTable';

const API_ENDPOINT = '/activities';
const STAGES_ENDPOINT = '/stages/all';
const EMPLOYEES_ENDPOINT = '/employees';

const initialFormState = { 
  id: '', 
  name: '', 
  estimatedTime: 0,
  realTime: 0,
  status: 'A', 
  stageId: '',
  responsibleId: ''
};

const ActivityPage = () => {
  const [records, setRecords] = useState([]);
  const [stages, setStages] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const { addNotification } = useNotifier();

  // Carga todos los datos necesarios para la página
  const fetchData = useCallback(async () => {
    setIsLoading(true);
    try {
      const [activitiesData, stagesData, employeesData] = await Promise.all([
        apiService.getAll(API_ENDPOINT),
        apiService.getAll(STAGES_ENDPOINT),
        apiService.getAll(EMPLOYEES_ENDPOINT)
      ]);
      setRecords(activitiesData);
      setStages(stagesData);
      setEmployees(employeesData.content || employeesData); // Empleados puede estar paginado
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
      addNotification('Por favor, seleccione una actividad para modificar.', 'warning');
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
    if (!formData.id || !formData.name || !formData.stageId || !formData.responsibleId) {
      addNotification('Los campos ID, Nombre, Etapa y Responsable son obligatorios.', 'error');
      return;
    }
    setIsLoading(true);

    const requestData = {
      id: formData.id ? parseInt(formData.id, 10) : null,
      name: formData.name,
      estimatedTime: parseInt(formData.estimatedTime, 10),
      realTime: parseInt(formData.realTime, 10),
      status: formData.status,
      stageId: parseInt(formData.stageId, 10),
      responsibleId: parseInt(formData.responsibleId, 10),
    };

    try {
      if (isEditing) {
        await apiService.update(API_ENDPOINT, formData.id, requestData);
        addNotification('Actividad actualizada con éxito.', 'success');
      } else {
        requestData.id = parseInt(formData.id, 10);
        await apiService.create(API_ENDPOINT, requestData);
        addNotification('Actividad creada con éxito.', 'success');
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
      addNotification('Por favor, seleccione una actividad para eliminar.', 'warning');
      return;
    }
    if (window.confirm(`¿Está seguro de que desea eliminar la actividad #${selectedRecord.id}?`)) {
      setIsLoading(true);
      try {
        const recordToDelete = { ...selectedRecord, status: '*' };
        await apiService.update(API_ENDPOINT, selectedRecord.id, {
            ...recordToDelete,
            stageId: recordToDelete.stageId,
            responsibleId: recordToDelete.responsibleId
        });
        addNotification('Actividad eliminada lógicamente.', 'success');
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
          <h1 className="text-3xl font-bold text-gray-800">Gestión de Actividades</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">← Volver al Menú</Link>
        </div>

        <ActivityForm
          formData={formData}
          handleFormChange={handleFormChange}
          handleSubmit={handleSave}
          isLoading={isLoading}
          isEditing={isEditing}
          stages={stages}
          employees={employees}
        />
        
        <div className="flex flex-wrap gap-3 mb-6">
          <button onClick={handleSave} disabled={isLoading} className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 disabled:bg-blue-400">
            {isEditing ? 'Guardar Cambios' : 'Crear Actividad'}
          </button>
          <button onClick={handleModify} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 rounded-md hover:bg-gray-300 disabled:bg-gray-100 disabled:text-gray-400">Modificar</button>
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading || selectedRecord?.status === '*'} className="px-4 py-2 text-sm font-medium text-red-700 bg-red-100 rounded-md hover:bg-red-200 disabled:bg-gray-100 disabled:text-gray-400">Eliminar</button>
          <button type="button" onClick={handleClear} className="ml-auto px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">Limpiar Formulario</button>
        </div>

        <ActivityTable
          records={records}
          handleSelectRecord={setSelectedRecord}
          selectedRecordId={selectedRecord?.id}
          isLoading={isLoading}
        />
      </div>
    </div>
  );
};

export default ActivityPage;
