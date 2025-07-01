import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import apiService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import ProjectUtilityForm from './ProjectUtilityForm';
import ProjectUtilityTable from './ProjectUtilityTable';

const API_ENDPOINT = '/project-utilities';
const PROJECTS_ENDPOINT = '/projects';
const TIME_FACTORS_ENDPOINT = '/time-factors';

const initialFormState = { 
  projectId: '',
  timeFactorId: '',
  experienceFactor: 0.0,
  basePercentage: 0.0,
  finalPercentage: 0.0,
  status: 'A'
};

const ProjectUtilityPage = () => {
  const [records, setRecords] = useState([]);
  const [projects, setProjects] = useState([]);
  const [timeFactors, setTimeFactors] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const { addNotification } = useNotifier();

  const fetchData = useCallback(async () => {
    setIsLoading(true);
    try {
      const [utilitiesData, projectsData, factorsData] = await Promise.all([
        apiService.getAll(API_ENDPOINT),
        apiService.getAll(PROJECTS_ENDPOINT),
        apiService.getAll(TIME_FACTORS_ENDPOINT),
      ]);
      setRecords(utilitiesData);
      setProjects(projectsData.content || projectsData);
      setTimeFactors(factorsData);
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
      addNotification('Por favor, seleccione un registro de utilidad para modificar.', 'warning');
      return;
    }
    setFormData(selectedRecord);
    setIsEditing(true);
  };

  const handleFormChange = (e) => {
    const { name, value, type } = e.target;
    const val = type === 'number' ? (value === '' ? '' : parseFloat(value)) : value;
    setFormData((prev) => ({ ...prev, [name]: val }));
  };

  const handleSave = async (e) => {
    e.preventDefault();
    if (!formData.projectId || !formData.timeFactorId) {
      addNotification('Los campos Proyecto y Factor de Tiempo son obligatorios.', 'error');
      return;
    }
    setIsLoading(true);

    const requestData = {
      experienceFactor: parseFloat(formData.experienceFactor),
      basePercentage: parseFloat(formData.basePercentage),
      finalPercentage: parseFloat(formData.finalPercentage),
      status: formData.status,
    };
    
    // El ID del proyecto y el factor de tiempo se usan para crear/actualizar
    const projectId = parseInt(formData.projectId, 10);
    const timeFactorId = parseInt(formData.timeFactorId, 10);

    try {
      // Para una relación 1-a-1 con PK=FK, PUT se usa tanto para crear como para actualizar.
      await apiService.update(`${API_ENDPOINT}/${projectId}`, timeFactorId, requestData);
      addNotification(`Utilidad para el proyecto #${projectId} guardada con éxito.`, 'success');
      
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
    if (window.confirm(`¿Está seguro de que desea eliminar la utilidad del proyecto #${selectedRecord.projectId}?`)) {
      setIsLoading(true);
      try {
        await apiService.remove(API_ENDPOINT, selectedRecord.projectId);
        addNotification('Registro de utilidad eliminado con éxito.', 'success');
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
          <h1 className="text-3xl font-bold text-gray-800">Gestión de Utilidad de Proyecto</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">← Volver al Menú</Link>
        </div>

        <ProjectUtilityForm
          formData={formData}
          handleFormChange={handleFormChange}
          handleSubmit={handleSave}
          isLoading={isLoading}
          isEditing={isEditing}
          projects={projects}
          timeFactors={timeFactors}
          existingUtilityIds={records.map(r => r.projectId)}
        />
        
        <div className="flex flex-wrap gap-3 mb-6">
          <button onClick={handleSave} disabled={isLoading} className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 disabled:bg-blue-400">
            {isEditing ? 'Guardar Cambios' : 'Crear/Actualizar Utilidad'}
          </button>
          <button onClick={handleModify} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 rounded-md hover:bg-gray-300 disabled:bg-gray-100 disabled:text-gray-400">Modificar</button>
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-red-700 bg-red-100 rounded-md hover:bg-red-200 disabled:bg-gray-100 disabled:text-gray-400">Eliminar</button>
          <button type="button" onClick={handleClear} className="ml-auto px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">Limpiar Formulario</button>
        </div>

        <ProjectUtilityTable
          records={records}
          handleSelectRecord={setSelectedRecord}
          selectedRecordId={selectedRecord?.projectId}
          isLoading={isLoading}
        />
      </div>
    </div>
  );
};

export default ProjectUtilityPage;
