import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import apiService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import TaskForm from './TaskForm';
import TaskTable from './TaskTable';

const API_ENDPOINT = '/tasks';
const ACTIVITIES_ENDPOINT = '/activities';
const TASK_TYPES_ENDPOINT = '/task-types';
const PRIORITIES_ENDPOINT = '/priorities';

const initialFormState = { 
  id: '', 
  description: '', 
  estimatedTime: 0,
  realTime: 0,
  taskStatus: 'P', // P: Pendiente, E: En ejecución, F: Finalizada, C: Cancelada
  status: 'A', 
  activityId: '',
  taskTypeId: '',
  priorityId: ''
};

const TaskPage = () => {
  const [records, setRecords] = useState([]);
  const [activities, setActivities] = useState([]);
  const [taskTypes, setTaskTypes] = useState([]);
  const [priorities, setPriorities] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const { addNotification } = useNotifier();

  const fetchData = useCallback(async () => {
    setIsLoading(true);
    try {
      const [tasksData, activitiesData, typesData, prioritiesData] = await Promise.all([
        apiService.getAll(`${API_ENDPOINT}?sort=id,asc`),
        apiService.getAll(ACTIVITIES_ENDPOINT),
        apiService.getAll(TASK_TYPES_ENDPOINT),
        apiService.getAll(PRIORITIES_ENDPOINT),
      ]);
      setRecords(tasksData.content || tasksData);
      setActivities(activitiesData);
      setTaskTypes(typesData);
      setPriorities(prioritiesData);
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
      addNotification('Por favor, seleccione una tarea para modificar.', 'warning');
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
    if (!formData.id || !formData.description || !formData.activityId || !formData.taskTypeId || !formData.priorityId) {
      addNotification('Todos los campos, incluyendo selecciones, son obligatorios.', 'error');
      return;
    }
    setIsLoading(true);

    const requestData = {
      id: formData.id ? parseInt(formData.id, 10) : null,
      description: formData.description,
      estimatedTime: parseInt(formData.estimatedTime, 10),
      realTime: parseInt(formData.realTime, 10),
      taskStatus: formData.taskStatus,
      status: formData.status,
      activityId: parseInt(formData.activityId, 10),
      taskTypeId: parseInt(formData.taskTypeId, 10),
      priorityId: parseInt(formData.priorityId, 10),
    };

    try {
      if (isEditing) {
        await apiService.update(API_ENDPOINT, formData.id, requestData);
        addNotification('Tarea actualizada con éxito.', 'success');
      } else {
        requestData.id = parseInt(formData.id, 10);
        await apiService.create(API_ENDPOINT, requestData);
        addNotification('Tarea creada con éxito.', 'success');
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
      addNotification('Por favor, seleccione una tarea para eliminar.', 'warning');
      return;
    }
    if (window.confirm(`¿Está seguro de que desea eliminar la tarea #${selectedRecord.id}?`)) {
      setIsLoading(true);
      try {
        const recordToDelete = { ...selectedRecord, status: '*' };
        await apiService.update(API_ENDPOINT, selectedRecord.id, {
            ...recordToDelete,
            activityId: recordToDelete.activityId,
            taskTypeId: recordToDelete.taskTypeId,
            priorityId: recordToDelete.priorityId
        });
        addNotification('Tarea eliminada lógicamente.', 'success');
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
          <h1 className="text-3xl font-bold text-gray-800">Gestión de Tareas</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">← Volver al Menú</Link>
        </div>

        <TaskForm
          formData={formData}
          handleFormChange={handleFormChange}
          handleSubmit={handleSave}
          isLoading={isLoading}
          isEditing={isEditing}
          activities={activities}
          taskTypes={taskTypes}
          priorities={priorities}
        />
        
        <div className="flex flex-wrap gap-3 mb-6">
          <button onClick={handleSave} disabled={isLoading} className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 disabled:bg-blue-400">
            {isEditing ? 'Guardar Cambios' : 'Crear Tarea'}
          </button>
          <button onClick={handleModify} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 rounded-md hover:bg-gray-300 disabled:bg-gray-100 disabled:text-gray-400">Modificar</button>
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading || selectedRecord?.status === '*'} className="px-4 py-2 text-sm font-medium text-red-700 bg-red-100 rounded-md hover:bg-red-200 disabled:bg-gray-100 disabled:text-gray-400">Eliminar</button>
          <button type="button" onClick={handleClear} className="ml-auto px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">Limpiar Formulario</button>
        </div>

        <TaskTable
          records={records}
          handleSelectRecord={setSelectedRecord}
          selectedRecordId={selectedRecord?.id}
          isLoading={isLoading}
        />
      </div>
    </div>
  );
};

export default TaskPage;
