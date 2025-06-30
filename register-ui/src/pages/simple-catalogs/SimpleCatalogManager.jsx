import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import catalogService from '../../api/apiService';
import { useNotifier } from '../../context/NotificationContext';

const initialFormState = { id: '', description: '', status: 'A' };

const SimpleCatalogManager = ({ title, apiEndpoint }) => {
  const [records, setRecords] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [formData, setFormData] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const { addNotification } = useNotifier();

  // --- Lógica de Carga de Datos ---
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

  // --- Manejadores de Estado del Formulario y Botones ---
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
    const val = type === 'number' ? (value === '' ? '' : parseInt(value, 10)) : value;
    setFormData((prev) => ({ ...prev, [name]: val }));
  };

  // --- Lógica CRUD ---
  const handleSave = async (e) => {
    e.preventDefault();
    if (formData.id === '' || formData.id === null) {
      addNotification('El campo Código (ID) no puede estar vacío.', 'error');
      return;
    }
    setIsLoading(true);

    try {
      if (isEditing) { // Actualizar
        await catalogService.update(apiEndpoint, formData.id, formData);
        addNotification(`${title} actualizado con éxito.`, 'success');
      } else { // Crear
        // La validación de ID duplicado se maneja en el backend.
        // El frontend solo necesita mostrar el error si ocurre.
        await catalogService.create(apiEndpoint, formData);
        addNotification(`${title} creado con éxito.`, 'success');
      }
      handleClear();
      await fetchRecords();
    } catch (error) {
      // Requisito 3 (Crear con ID duplicado): El error vendrá del backend.
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
    if (window.confirm(`¿Está seguro de que desea eliminar el registro #${selectedRecord.id}? Esta acción marcará el registro como eliminado.`)) {
      setIsLoading(true);
      try {
        const recordToDelete = { ...selectedRecord, status: '*' };
        await catalogService.update(apiEndpoint, selectedRecord.id, recordToDelete);
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

  // --- Componentes de Renderizado ---
  const StatusBadge = ({ status }) => {
    const styles = {
      A: 'bg-green-100 text-green-800',
      I: 'bg-yellow-100 text-yellow-800',
      '*': 'bg-red-100 text-red-800',
    };
    const text = { A: 'Activo', I: 'Inactivo', '*': 'Eliminado' };
    return <span className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full ${styles[status] || 'bg-gray-100 text-gray-800'}`}>{text[status] || status}</span>;
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
        <form onSubmit={handleSave} className="mb-6 p-4 border rounded-md bg-gray-50 space-y-4">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div>
              <label htmlFor="id" className="block text-sm font-medium text-gray-700">Código (ID)</label>
              <input 
                type="number" 
                name="id" 
                value={formData.id} 
                onChange={handleFormChange}
                disabled={isEditing} // Requisito 1: ID inmutable en edición.
                required
                className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 ${isEditing ? 'bg-gray-200 cursor-not-allowed' : 'bg-white'}`}
              />
            </div>
            <div>
              <label htmlFor="description" className="block text-sm font-medium text-gray-700">Descripción</label>
              <input type="text" name="description" value={formData.description} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500" />
            </div>
            <div>
              <label htmlFor="status" className="block text-sm font-medium text-gray-700">Estado del Registro</label>
              <select name="status" value={formData.status} onChange={handleFormChange} disabled={!isEditing} className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 ${!isEditing ? 'bg-gray-200 cursor-not-allowed' : 'bg-white'}`}>
                {/* Requisito 2 (Eliminar): Permitir reactivar/inactivar desde '*' */}
                <option value="A">Activo</option>
                <option value="I">Inactivo</option>
                {/* Mostramos la opción 'Eliminado' solo si el registro está en ese estado, pero no se puede seleccionar activamente */}
                {formData.status === '*' && <option value="*">Eliminado</option>}
              </select>
            </div>
          </div>
        </form>

        {/* Botones de Acción */}
        <div className="flex flex-wrap gap-3 mb-6">
          <button onClick={handleSave} disabled={isLoading} className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 disabled:bg-blue-400">
            {isEditing ? 'Guardar Cambios' : 'Crear Registro'}
          </button>
          <button onClick={handleModify} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-200 rounded-md hover:bg-gray-300 disabled:bg-gray-100 disabled:text-gray-400">
            Modificar
          </button>
          <button onClick={handleDelete} disabled={!selectedRecord || isLoading} className="px-4 py-2 text-sm font-medium text-red-700 bg-red-100 rounded-md hover:bg-red-200 disabled:bg-gray-100 disabled:text-gray-400">
            Eliminar
          </button>
          <button type="button" onClick={handleClear} className="ml-auto px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50">
            Limpiar Formulario
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
                  onClick={() => setSelectedRecord(record)}
                  // Requisito 2 (Eliminar): Ya no se tacha, solo se cambia el color de fondo
                  className={`cursor-pointer transition-colors 
                    ${record.status === '*' ? 'bg-red-50 text-gray-500' : ''}
                    ${selectedRecord?.id === record.id ? 'bg-blue-200' : 'hover:bg-gray-50'}`}
                >
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">{record.id}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm">{record.description}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm">
                    <StatusBadge status={record.status} />
                  </td>
                </tr>
              ))}
              {!isLoading && records.length === 0 && (
                <tr><td colSpan="3" className="text-center p-4 text-gray-500">No hay registros para mostrar.</td></tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default SimpleCatalogManager;
