import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import catalogService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import PositionForm from './PositionForm';
import PositionTable from './PositionTable';

const API_ENDPOINT = '/positions';
const ENTITY_NAME = 'Cargo';

const initialFormState = { id: '', description: '', costPerHour: '', status: 'A' };

const PositionPage = () => {
  const [records, setRecords] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const { addNotification } = useNotifier();

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

  useEffect(() => {
    fetchRecords();
  }, [fetchRecords]);

  const handleSelectRecord = (record) => {
    setSelectedRecord(record);
    setFormData(record);
  };

  const handleFormChange = (e) => {
    const { name, value, type } = e.target;
    const val = type === 'number' ? parseFloat(value) : value;
    setFormData((prev) => ({ ...prev, [name]: val }));
  };
  
  const reset = () => {
    setFormData(initialFormState);
    setSelectedRecord(null);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      if (formData.id) {
        await catalogService.update(API_ENDPOINT, formData.id, formData);
        addNotification(`${ENTITY_NAME} actualizado con éxito.`, 'success');
      } else {
        await catalogService.create(API_ENDPOINT, formData);
        addNotification(`${ENTITY_NAME} creado con éxito.`, 'success');
      }
      reset();
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
        await catalogService.remove(API_ENDPOINT, selectedRecord.id);
        addNotification('Registro eliminado con éxito.', 'success');
        reset();
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
          <h1 className="text-3xl font-bold text-gray-800">Gestión de Cargos</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">
            ← Volver al Menú
          </Link>
        </div>

        <PositionForm 
          formData={formData}
          handleFormChange={handleFormChange}
          handleSubmit={handleSubmit}
          reset={reset}
          isLoading={isLoading}
        />

        <div className="mb-6 flex justify-start">
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-white bg-red-600 rounded-md hover:bg-red-700 disabled:bg-red-400">
            Eliminar Seleccionado
          </button>
        </div>

        <PositionTable 
          records={records}
          handleSelectRecord={handleSelectRecord}
          selectedRecordId={selectedRecord?.id}
          isLoading={isLoading}
        />
      </div>
    </div>
  );
};

export default PositionPage;
