import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import catalogService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import ExperienceLevelForm from './ExperienceLevelForm';
import ExperienceLevelTable from './ExperienceLevelTable';

const API_ENDPOINT = '/experience-levels';
const ENTITY_NAME = 'Nivel de Experiencia';
const initialFormState = { id: '', description: '', value: 0.0, status: 'A' };

const ExperienceLevelPage = () => {
  const [records, setRecords] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const { addNotification } = useNotifier();

  // La lógica es idéntica a PositionPage, solo cambian las constantes
  const fetchRecords = useCallback(async () => {
    setIsLoading(true);
    try {
      const data = await catalogService.getAll(API_ENDPOINT);
      setRecords(data);
    } catch (error) {
      addNotification(`Error al cargar ${ENTITY_NAME}s: ${error.message}`, 'error');
    } finally {
      setIsLoading(false);
    }
  }, [addNotification]);

  useEffect(() => { fetchRecords(); }, [fetchRecords]);

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
    if (formData.id === '' || formData.id === null) {
      addNotification('El campo Código (ID) no puede estar vacío.', 'error');
      return;
    }
    setIsLoading(true);
    try {
      if (isEditing) {
        await catalogService.update(API_ENDPOINT, formData.id, formData);
        addNotification(`${ENTITY_NAME} actualizado con éxito.`, 'success');
      } else {
        await catalogService.create(API_ENDPOINT, formData);
        addNotification(`${ENTITY_NAME} creado con éxito.`, 'success');
      }
      handleClear();
      await fetchRecords();
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
    if (window.confirm(`¿Está seguro de que desea eliminar el registro #${selectedRecord.id}?`)) {
      setIsLoading(true);
      try {
        const recordToDelete = { ...selectedRecord, status: '*' };
        await catalogService.update(API_ENDPOINT, selectedRecord.id, recordToDelete);
        addNotification('Registro eliminado lógicamente.', 'success');
        handleClear();
        await fetchRecords();
      } catch (error) {
        addNotification(`Error al eliminar: ${error.message}`, 'error');
      } finally {
        setIsLoading(false);
      }
    }
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <div className="max-w-6xl mx-auto bg-white p-8 rounded-lg shadow-lg">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-3xl font-bold text-gray-800">Niveles de Experiencia</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">← Volver al Menú</Link>
        </div>

        <ExperienceLevelForm formData={formData} handleFormChange={handleFormChange} handleSubmit={handleSave} isLoading={isLoading} isEditing={isEditing} />

        <div className="flex flex-wrap gap-3 mb-6">
          <button onClick={handleSave} disabled={isLoading} className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 disabled:bg-blue-400">{isEditing ? 'Guardar Cambios' : 'Crear Registro'}</button>
          <button onClick={handleModify} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 rounded-md hover:bg-gray-300 disabled:bg-gray-100 disabled:text-gray-400">Modificar</button>
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading || selectedRecord?.status === '*'} className="px-4 py-2 text-sm font-medium text-red-700 bg-red-100 rounded-md hover:bg-red-200 disabled:bg-gray-100 disabled:text-gray-400">Eliminar</button>
          <button type="button" onClick={handleClear} className="ml-auto px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">Limpiar Formulario</button>
        </div>
        
        <ExperienceLevelTable records={records} handleSelectRecord={setSelectedRecord} selectedRecordId={selectedRecord?.id} isLoading={isLoading} />
      </div>
    </div>
  );
};

export default ExperienceLevelPage;
