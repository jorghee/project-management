import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import apiService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import ComplexityShuttle from './ComplexityShuttle';
import ComplexityTable from './ComplexityTable';

const API_ENDPOINT = '/complexities';
const PROJECTS_ENDPOINT = '/projects';
const UTILITY_FACTORS_ENDPOINT = '/utility-factors';

const ComplexityPage = () => {
  const [projectComplexities, setProjectComplexities] = useState([]);
  const [projects, setProjects] = useState([]);
  const [allUtilityFactors, setAllUtilityFactors] = useState([]);
  const [selectedProjectId, setSelectedProjectId] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const { addNotification } = useNotifier();

  const fetchInitialData = useCallback(async () => {
    setIsLoading(true);
    try {
      const [projectsData, factorsData] = await Promise.all([
        apiService.getAll(PROJECTS_ENDPOINT),
        apiService.getAll(UTILITY_FACTORS_ENDPOINT),
      ]);
      setProjects(projectsData.content || projectsData);
      setAllUtilityFactors(factorsData);
    } catch (error) {
      addNotification(`Error al cargar datos: ${error.message}`, 'error');
    } finally {
      setIsLoading(false);
    }
  }, [addNotification]);

  useEffect(() => {
    fetchInitialData();
  }, [fetchInitialData]);

  // Carga las complejidades de un proyecto cuando se selecciona
  const fetchComplexitiesForProject = useCallback(async (projectId) => {
    if (!projectId) {
      setProjectComplexities([]);
      return;
    }
    setIsLoading(true);
    try {
      const data = await apiService.getAll(`${API_ENDPOINT}/by-project/${projectId}`);
      setProjectComplexities(data);
    } catch (error) {
      addNotification(`Error al cargar factores para el proyecto: ${error.message}`, 'error');
    } finally {
      setIsLoading(false);
    }
  }, [addNotification]);

  useEffect(() => {
    fetchComplexitiesForProject(selectedProjectId);
  }, [selectedProjectId]);


  // Lógica para asignar un factor
  const handleAssignFactor = async (factorId) => {
    setIsLoading(true);
    try {
      await apiService.create(`${API_ENDPOINT}/${selectedProjectId}/${factorId}`, {});
      addNotification('Factor asignado con éxito.', 'success');
      fetchComplexitiesForProject(selectedProjectId); // Recargar la lista de asignados
    } catch (error) {
      addNotification(`Error al asignar factor: ${error.message}`, 'error');
    } finally {
      setIsLoading(false);
    }
  };

  // Lógica para remover un factor
  const handleRemoveFactor = async (factorId) => {
    setIsLoading(true);
    try {
      const fullPath = `${API_ENDPOINT}/${selectedProjectId}/${factorId}`;
      await apiService.removeByPath(fullPath);
      addNotification('Factor removido con éxito.', 'success');
      fetchComplexitiesForProject(selectedProjectId); // Recargar la lista de asignados
    } catch (error) {
      addNotification(`Error al remover factor: ${error.message}`, 'error');
    } finally {
      setIsLoading(false);
    }
  };

  const assignedFactorIds = new Set(projectComplexities.map(c => c.utilityFactorId));
  const assignedFactors = allUtilityFactors.filter(f => assignedFactorIds.has(f.id));
  const availableFactors = allUtilityFactors.filter(f => !assignedFactorIds.has(f.id));

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <div className="max-w-7xl mx-auto bg-white p-8 rounded-lg shadow-lg">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-3xl font-bold text-gray-800">Gestión de Complejidad de Proyectos</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">← Volver al Menú</Link>
        </div>

        {/* Sección de Selección de Proyecto */}
        <div className="mb-6 p-4 border rounded-md bg-gray-50">
          <label htmlFor="projectSelector" className="block text-sm font-medium text-gray-700 mb-2">
            Seleccione un Proyecto para gestionar sus Factores de Complejidad
          </label>
          <select
            id="projectSelector"
            value={selectedProjectId}
            onChange={(e) => setSelectedProjectId(e.target.value)}
            className="block w-full max-w-md px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
          >
            <option value="">-- Seleccionar Proyecto --</option>
            {projects.map(p => (
              <option key={p.id} value={p.id}>{p.name}</option>
            ))}
          </select>
        </div>

        {/* Interfaz de Shuttle (solo se muestra si se selecciona un proyecto) */}
        {selectedProjectId && (
          <div className="mb-8">
            <ComplexityShuttle
              availableFactors={availableFactors}
              assignedFactors={assignedFactors}
              onAssign={handleAssignFactor}
              onRemove={handleRemoveFactor}
            />
          </div>
        )}

        {/* Tabla de Resumen (opcional, muestra las relaciones del proyecto seleccionado) */}
        <h2 className="text-xl font-semibold text-gray-700 mb-4">Resumen de Factores Asignados</h2>
        <ComplexityTable
          records={projectComplexities}
          isLoading={isLoading}
        />
      </div>
    </div>
  );
};

export default ComplexityPage;
