import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import apiService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import ComplexityTable from './ComplexityTable';

const API_ENDPOINT = '/complexities';
const PROJECTS_ENDPOINT = '/projects';
const UTILITY_FACTORS_ENDPOINT = '/utility-factors';

const ComplexityPage = () => {
  const [allComplexities, setAllComplexities] = useState([]);
  const [filteredComplexities, setFilteredComplexities] = useState([]);
  const [projects, setProjects] = useState([]);
  const [utilityFactors, setUtilityFactors] = useState([]);
  
  const [selectedProjectId, setSelectedProjectId] = useState('');
  const [selectedFactorId, setSelectedFactorId] = useState('');
  
  const [isLoading, setIsLoading] = useState(false);
  const { addNotification } = useNotifier();

  const fetchData = useCallback(async () => {
    setIsLoading(true);
    try {
      const [complexitiesData, projectsData, factorsData] = await Promise.all([
        apiService.getAll(API_ENDPOINT),
        apiService.getAll(PROJECTS_ENDPOINT),
        apiService.getAll(UTILITY_FACTORS_ENDPOINT),
      ]);
      setAllComplexities(complexitiesData);
      setFilteredComplexities(complexitiesData);
      setProjects(projectsData.content || projectsData);
      setUtilityFactors(factorsData);
    } catch (error) {
      addNotification(`Error al cargar datos: ${error.message}`, 'error');
    } finally {
      setIsLoading(false);
    }
  }, [addNotification]);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  useEffect(() => {
    let results = [...allComplexities];

    if (selectedProjectId) {
      results = results.filter(c => c.projectUtilityId === parseInt(selectedProjectId, 10));
    }

    if (selectedFactorId) {
      results = results.filter(c => c.utilityFactorId === parseInt(selectedFactorId, 10));
    }
    
    setFilteredComplexities(results);
  }, [selectedProjectId, selectedFactorId, allComplexities]);


  const handleResetFilters = () => {
    setSelectedProjectId('');
    setSelectedFactorId('');
  };


  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <div className="max-w-7xl mx-auto bg-white p-8 rounded-lg shadow-lg">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-3xl font-bold text-gray-800">Visualización de Complejidad de Proyectos</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">← Volver al Menú</Link>
        </div>

        {/* Sección de Filtros */}
        <div className="mb-6 p-4 border rounded-md bg-gray-50 flex flex-wrap items-end gap-4">
          <div className="flex-1 min-w-[200px]">
            <label htmlFor="projectFilter" className="block text-sm font-medium text-gray-700">Filtrar por Proyecto</label>
            <select
              id="projectFilter"
              value={selectedProjectId}
              onChange={(e) => setSelectedProjectId(e.target.value)}
              className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
            >
              <option value="">Todos los Proyectos</option>
              {projects.map(p => (
                <option key={p.id} value={p.id}>{p.name}</option>
              ))}
            </select>
          </div>
          <div className="flex-1 min-w-[200px]">
            <label htmlFor="factorFilter" className="block text-sm font-medium text-gray-700">Filtrar por Factor de Utilidad</label>
            <select
              id="factorFilter"
              value={selectedFactorId}
              onChange={(e) => setSelectedFactorId(e.target.value)}
              className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
            >
              <option value="">Todos los Factores</option>
              {utilityFactors.map(f => (
                <option key={f.id} value={f.id}>{f.description}</option>
              ))}
            </select>
          </div>
          <div>
            <button onClick={handleResetFilters} className="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">
              Limpiar Filtros
            </button>
          </div>
        </div>

        <ComplexityTable
          records={filteredComplexities}
          isLoading={isLoading}
        />
      </div>
    </div>
  );
};

export default ComplexityPage;
