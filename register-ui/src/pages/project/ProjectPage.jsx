import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import apiService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import ProjectForm from './ProjectForm';
import ProjectTable from './ProjectTable';

const API_ENDPOINT = '/projects';
const CLIENTS_ENDPOINT = '/clients';
const STATUSES_ENDPOINT = '/project-status';

const initialFormState = { 
  id: '', 
  name: '', 
  startDate: '', 
  endDate: '', 
  estimatedAmount: 0.0,
  realAmount: 0.0,
  status: 'A', 
  clientId: '', 
  projectStatusId: ''
};

const ProjectPage = () => {
  const [records, setRecords] = useState([]);
  const [clients, setClients] = useState([]);
  const [projectStatuses, setProjectStatuses] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const { addNotification } = useNotifier();

  // Carga todos los datos necesarios para la página
  const fetchData = useCallback(async () => {
    setIsLoading(true);
    try {
      // Usamos Promise.all para cargar los datos en paralelo
      const [projectsData, clientsData, statusesData] = await Promise.all([
        apiService.getAll(`${API_ENDPOINT}?sort=id,asc`), // Paginado, ordenado por ID
        apiService.getAll(CLIENTS_ENDPOINT), // Se asume que no está paginado
        apiService.getAll(STATUSES_ENDPOINT)
      ]);
      setRecords(projectsData.content || projectsData); // Compatible con paginación y sin ella
      setClients(clientsData.content || clientsData);
      setProjectStatuses(statusesData);
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
      addNotification('Por favor, seleccione un proyecto para modificar.', 'warning');
      return;
    }
    const recordToEdit = {
        ...selectedRecord,
        startDate: selectedRecord.startDate || '',
        endDate: selectedRecord.endDate || '',
    };
    setFormData(recordToEdit);
    setIsEditing(true);
  };

  const handleFormChange = (e) => {
    const { name, value, type } = e.target;
    const val = type === 'number' ? (value === '' ? '' : parseFloat(value)) : value;
    setFormData((prev) => ({ ...prev, [name]: val }));
  };

  const handleSave = async (e) => {
    e.preventDefault();
    if (!formData.id || !formData.name || !formData.clientId || !formData.projectStatusId) {
      addNotification('Los campos ID, Nombre, Cliente y Estado del Proyecto son obligatorios.', 'error');
      return;
    }
    setIsLoading(true);

    const requestData = {
      id: formData.id ? parseInt(formData.id, 10) : null,
      name: formData.name,
      startDate: formData.startDate || null,
      endDate: formData.endDate || null,
      estimatedAmount: parseFloat(formData.estimatedAmount),
      realAmount: parseFloat(formData.realAmount),
      status: formData.status,
      clientId: parseInt(formData.clientId, 10),
      projectStatusId: parseInt(formData.projectStatusId, 10),
    };

    try {
      if (isEditing) {
        await apiService.update(API_ENDPOINT, formData.id, requestData);
        addNotification('Proyecto actualizado con éxito.', 'success');
      } else {
        requestData.id = parseInt(formData.id, 10);
        await apiService.create(API_ENDPOINT, requestData);
        addNotification('Proyecto creado con éxito.', 'success');
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
      addNotification('Por favor, seleccione un proyecto para eliminar.', 'warning');
      return;
    }
    if (window.confirm(`¿Está seguro de que desea eliminar el proyecto #${selectedRecord.id}?`)) {
      setIsLoading(true);
      try {
        const recordToDelete = { ...selectedRecord, status: '*' };
        await apiService.update(API_ENDPOINT, selectedRecord.id, {
            ...recordToDelete,
            clientId: recordToDelete.clientId,
            projectStatusId: recordToDelete.projectStatusId
        });
        addNotification('Proyecto eliminado lógicamente.', 'success');
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
          <h1 className="text-3xl font-bold text-gray-800">Gestión de Proyectos</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">← Volver al Menú</Link>
        </div>

        <ProjectForm
          formData={formData}
          handleFormChange={handleFormChange}
          handleSubmit={handleSave}
          isLoading={isLoading}
          isEditing={isEditing}
          clients={clients}
          projectStatuses={projectStatuses}
        />
        
        <div className="flex flex-wrap gap-3 mb-6">
          <button onClick={handleSave} disabled={isLoading} className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 disabled:bg-blue-400">
            {isEditing ? 'Guardar Cambios' : 'Crear Proyecto'}
          </button>
          <button onClick={handleModify} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 rounded-md hover:bg-gray-300 disabled:bg-gray-100 disabled:text-gray-400">Modificar</button>
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading || selectedRecord?.status === '*'} className="px-4 py-2 text-sm font-medium text-red-700 bg-red-100 rounded-md hover:bg-red-200 disabled:bg-gray-100 disabled:text-gray-400">Eliminar</button>
          <button type="button" onClick={handleClear} className="ml-auto px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">Limpiar Formulario</button>
        </div>

        <ProjectTable
          records={records}
          handleSelectRecord={setSelectedRecord}
          selectedRecordId={selectedRecord?.id}
          isLoading={isLoading}
        />
      </div>
    </div>
  );
};

export default ProjectPage;
