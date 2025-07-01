import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import apiService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';
import ClientForm from './ClientForm';
import ClientTable from './ClientTable';

const API_ENDPOINT = '/clients';
const CLIENT_TYPES_ENDPOINT = '/client-types';

const initialFormState = { 
  id: '', 
  name: '', 
  entryDate: '', 
  terminationDate: '', 
  clientStatus: 'A', 
  status: 'A', 
  clientTypeId: '' 
};

const ClientPage = () => {
  const [records, setRecords] = useState([]);
  const [clientTypes, setClientTypes] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const { addNotification } = useNotifier();

  const fetchData = useCallback(async () => {
    setIsLoading(true);
    try {
      const [clientsData, clientTypesData] = await Promise.all([
        apiService.getAll(API_ENDPOINT),
        apiService.getAll(CLIENT_TYPES_ENDPOINT),
      ]);
      setRecords(clientsData.content);
      setClientTypes(clientTypesData);
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
      addNotification('Por favor, seleccione un cliente para modificar.', 'warning');
      return;
    }
    const recordToEdit = {
        ...selectedRecord,
        entryDate: selectedRecord.entryDate || '',
        terminationDate: selectedRecord.terminationDate || '',
    };
    setFormData(recordToEdit);
    setIsEditing(true);
  };

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = async (e) => {
    e.preventDefault();
    if (!formData.id || !formData.name || !formData.clientTypeId) {
      addNotification('Los campos ID, Nombre y Tipo de Cliente son obligatorios.', 'error');
      return;
    }
    setIsLoading(true);

    const requestData = {
      id: formData.id ? parseInt(formData.id, 10) : null,
      name: formData.name,
      entryDate: formData.entryDate || null,
      terminationDate: formData.terminationDate || null,
      clientStatus: formData.clientStatus,
      status: formData.status,
      clientTypeId: parseInt(formData.clientTypeId, 10),
    };

    try {
      if (isEditing) {
        await apiService.update(API_ENDPOINT, formData.id, requestData);
        addNotification('Cliente actualizado con éxito.', 'success');
      } else {
        requestData.id = parseInt(formData.id, 10);
        await apiService.create(API_ENDPOINT, requestData);
        addNotification('Cliente creado con éxito.', 'success');
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
      addNotification('Por favor, seleccione un cliente para eliminar.', 'warning');
      return;
    }
    if (window.confirm(`¿Está seguro de que desea eliminar al cliente #${selectedRecord.id}?`)) {
      setIsLoading(true);
      try {
        const recordToDelete = { ...selectedRecord, status: '*' };
        await apiService.update(API_ENDPOINT, selectedRecord.id, {
            name: recordToDelete.name,
            entryDate: recordToDelete.entryDate,
            terminationDate: recordToDelete.terminationDate,
            clientStatus: recordToDelete.clientStatus,
            status: recordToDelete.status,
            clientTypeId: recordToDelete.clientTypeId,
        });
        addNotification('Cliente eliminado lógicamente.', 'success');
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
          <h1 className="text-3xl font-bold text-gray-800">Gestión de Clientes</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">← Volver al Menú</Link>
        </div>

        <ClientForm
          formData={formData}
          handleFormChange={handleFormChange}
          handleSubmit={handleSave}
          isLoading={isLoading}
          isEditing={isEditing}
          clientTypes={clientTypes}
        />
        
        <div className="flex flex-wrap gap-3 mb-6">
          <button onClick={handleSave} disabled={isLoading} className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 disabled:bg-blue-400">
            {isEditing ? 'Guardar Cambios' : 'Crear Cliente'}
          </button>
          <button onClick={handleModify} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 rounded-md hover:bg-gray-300 disabled:bg-gray-100 disabled:text-gray-400">Modificar</button>
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading || selectedRecord?.status === '*'} className="px-4 py-2 text-sm font-medium text-red-700 bg-red-100 rounded-md hover:bg-red-200 disabled:bg-gray-100 disabled:text-gray-400">Eliminar</button>
          <button type="button" onClick={handleClear} className="ml-auto px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">Limpiar Formulario</button>
        </div>

        <ClientTable
          records={records}
          handleSelectRecord={setSelectedRecord}
          selectedRecordId={selectedRecord?.id}
          isLoading={isLoading}
        />
      </div>
    </div>
  );
};

export default ClientPage;
