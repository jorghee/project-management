import React from 'react';

const ClientForm = ({ formData, handleFormChange, handleSubmit, isLoading, isEditing, clientTypes }) => {
  return (
    <form onSubmit={handleSubmit} className="mb-6 p-4 border rounded-md bg-gray-50 space-y-4">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <div>
          <label htmlFor="id" className="block text-sm font-medium text-gray-700">Código (ID)</label>
          <input
            type="number" name="id" value={formData.id} onChange={handleFormChange}
            disabled={isEditing} required
            className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 ${isEditing ? 'bg-gray-200 cursor-not-allowed' : 'bg-white'}`}
          />
        </div>
        
        <div>
          <label htmlFor="name" className="block text-sm font-medium text-gray-700">Nombre del Cliente</label>
          <input
            type="text" name="name" value={formData.name} onChange={handleFormChange}
            required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          />
        </div>

        <div>
          <label htmlFor="clientTypeId" className="block text-sm font-medium text-gray-700">Tipo de Cliente</label>
          <select
            name="clientTypeId" value={formData.clientTypeId} onChange={handleFormChange}
            required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          >
            <option value="">Seleccione un tipo...</option>
            {clientTypes.map(ct => (
              <option key={ct.id} value={ct.id}>{ct.description}</option>
            ))}
          </select>
        </div>

        <div>
          <label htmlFor="clientStatus" className="block text-sm font-medium text-gray-700">Estado del Cliente</label>
           <select
            name="clientStatus" value={formData.clientStatus} onChange={handleFormChange}
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          >
            <option value="A">Activo</option>
            <option value="C">Cesado</option>
          </select>
        </div>

        <div>
          <label htmlFor="entryDate" className="block text-sm font-medium text-gray-700">Fecha de Ingreso</label>
          <input
            type="date" name="entryDate" value={formData.entryDate || ''} onChange={handleFormChange}
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          />
        </div>
        <div>
          <label htmlFor="terminationDate" className="block text-sm font-medium text-gray-700">Fecha de Cese</label>
          <input
            type="date" name="terminationDate" value={formData.terminationDate || ''} onChange={handleFormChange}
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          />
        </div>

        <div>
          <label htmlFor="status" className="block text-sm font-medium text-gray-700">Estado del Registro</label>
          <select
            name="status" value={formData.status} onChange={handleFormChange}
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          >
            <option value="A">Activo</option>
            <option value="I">Inactivo</option>
            {isEditing && formData.status === '*' && <option value="*" disabled>Eliminado</option>}
          </select>
        </div>
      </div>
    </form>
  );
};

export default ClientForm;
