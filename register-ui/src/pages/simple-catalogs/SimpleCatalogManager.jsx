import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import catalogService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';

// Estado inicial para el formulario
const initialFormState = { id: '', description: '', status: 'A' };

const SimpleCatalogManager = ({ title, apiEndpoint }) => {
  const [records, setRecords] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const { addNotification } = useNotifier();

  // Función para cargar los registros desde la API
  const fetchRecords = useCallback(async () => {
    setIsLoading(true);
    try {
      const data = await catalogService.getAll(apiEndpoint);
      setRecords(data);
    } catch (error) {
      addNotification(`Error al cargar ${title}: ${error.message}`, 'error');
    } finally {
      setIsLoading(false);
    }
  }, [apiEndpoint, title, addNotification]);

  useEffect(() => {
    fetchRecords();
  }, [fetchRecords]);

  // Manejar selección de una fila en la tabla
  const handleSelectRecord = (record) => {
    setSelectedRecord(record);
    setFormData(record);
  };

  // Manejar cambios en el formulario
  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };
  
  // Limpiar formulario y selección
  const reset = () => {
    setFormData(initialFormState);
    setSelectedRecord(null);
  }

  // Manejar el submit del formulario (Crear o Actualizar)
  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      if (formData.id) { // Actualizar
        await catalogService.update(apiEndpoint, formData.id, formData);
        addNotification(`${title} actualizado con éxito.`, 'success');
      } else { // Crear
        await catalogService.create(apiEndpoint, formData);
        addNotification(`${title} creado con éxito.`, 'success');
      }
      reset();
      await fetchRecords();
    } catch (error) {
      addNotification(`Error al guardar: ${error.message}`, 'error');
    } finally {
      setIsLoading(false);
    }
  };

  // Manejar eliminación de un registro
  const handleDelete = async () => {
    if (!selectedRecord) {
      addNotification('Por favor, seleccione un registro para eliminar.', 'warning');
      return;
    }
    if (window.confirm(`¿Está seguro de que desea eliminar el registro #${selectedRecord.id}?`)) {
      setIsLoading(true);
      try {
        await catalogService.remove(apiEndpoint, selectedRecord.id);
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
      <div className="max-w-4xl mx-auto bg-white p-8 rounded-lg shadow-lg">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-3xl font-bold text-gray-800">{title}</h1>
          <Link to="/" className="px-4 py-2 text-sm font-medium text-white bg-gray-600 rounded-md hover:bg-gray-700">
            ← Volver al Menú
          </Link>
        </div>

        {/* Formulario */}
        <form onSubmit={handleSubmit} className="mb-6 p-4 border rounded-md bg-gray-50 space-y-4">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div>
              <label htmlFor="id" className="block text-sm font-medium text-gray-700">Código</label>
              <input type="text" name="id" value={formData.id} onChange={handleFormChange} disabled className="mt-1 block w-full px-3 py-2 bg-gray-200 border border-gray-300 rounded-md shadow-sm" />
            </div>
            <div>
              <label htmlFor="description" className="block text-sm font-medium text-gray-700">Descripción</label>
              <input type="text" name="description" value={formData.description} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500" />
            </div>
            <div>
              <label htmlFor="status" className="block text-sm font-medium text-gray-700">Estado</label>
              <select name="status" value={formData.status} onChange={handleFormChange} className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                <option value="A">Activo</option>
                <option value="I">Inactivo</option>
              </select>
            </div>
          </div>
          {/* Botones de Acción del Formulario */}
          <div className="flex justify-end gap-3">
            <button type="button" onClick={reset} className="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">Limpiar</button>
            <button type="submit" disabled={isLoading} className="px-4 py-2 text-sm font-medium text-white bg-indigo-600 rounded-md hover:bg-indigo-700 disabled:bg-indigo-400">
              {isLoading ? 'Guardando...' : formData.id ? 'Actualizar' : 'Crear'}
            </button>
          </div>
        </form>

        {/* Botón de Eliminar */}
        <div className="mb-6 flex justify-start">
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-white bg-red-600 rounded-md hover:bg-red-700 disabled:bg-red-400">
            Eliminar Seleccionado
          </button>
        </div>

        {/* Tabla de Registros */}
        <div className="overflow-x-auto border rounded-lg">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Código</th>
                <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Descripción</th>
                <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Estado</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {isLoading && <tr><td colSpan="3" className="text-center p-4">Cargando...</td></tr>}
              {!isLoading && records.map((record) => (
                <tr 
                  key={record.id} 
                  onClick={() => handleSelectRecord(record)}
                  className={`cursor-pointer transition-colors ${selectedRecord?.id === record.id ? 'bg-blue-100' : 'hover:bg-gray-50'}`}
                >
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{record.id}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{record.description}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm">
                    {record.status === 'A' 
                      ? <span className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">Activo</span>
                      : <span className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-yellow-100 text-yellow-800">Inactivo</span>}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default SimpleCatalogManager;
