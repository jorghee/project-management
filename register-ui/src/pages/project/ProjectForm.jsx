import React from 'react';

const ProjectForm = ({ formData, handleFormChange, handleSubmit, isLoading, isEditing, clients, projectStatuses }) => {
  return (
    <form onSubmit={handleSubmit} className="mb-6 p-4 border rounded-md bg-gray-50 space-y-4">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <div>
          <label htmlFor="id" className="block text-sm font-medium text-gray-700">Código (ID)</label>
          <input
            type="number" name="id" value={formData.id} onChange={handleFormChange}
            disabled={isEditing} required
            className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm ${isEditing ? 'bg-gray-200' : 'bg-white'}`}
          />
        </div>
        <div>
          <label htmlFor="name" className="block text-sm font-medium text-gray-700">Nombre del Proyecto</label>
          <input
            type="text" name="name" value={formData.name} onChange={handleFormChange}
            required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
          />
        </div>
        <div>
          <label htmlFor="clientId" className="block text-sm font-medium text-gray-700">Cliente</label>
          <select
            name="clientId" value={formData.clientId} onChange={handleFormChange}
            required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
          >
            <option value="">Seleccione un cliente...</option>
            {clients.map(c => (
              <option key={c.id} value={c.id}>{c.name}</option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="projectStatusId" className="block text-sm font-medium text-gray-700">Estado del Proyecto</label>
          <select
            name="projectStatusId" value={formData.projectStatusId} onChange={handleFormChange}
            required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
          >
            <option value="">Seleccione un estado...</option>
            {projectStatuses.map(s => (
              <option key={s.id} value={s.id}>{s.description}</option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="startDate" className="block text-sm font-medium text-gray-700">Fecha de Inicio</label>
          <input
            type="date" name="startDate" value={formData.startDate || ''} onChange={handleFormChange} required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
          />
        </div>
        <div>
          <label htmlFor="endDate" className="block text-sm font-medium text-gray-700">Fecha de Fin</label>
          <input
            type="date" name="endDate" value={formData.endDate || ''} onChange={handleFormChange}
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
          />
        </div>
        <div>
          <label htmlFor="estimatedAmount" className="block text-sm font-medium text-gray-700">Monto Estimado</label>
          <input
            type="number" name="estimatedAmount" value={formData.estimatedAmount} onChange={handleFormChange}
            min="0" step="0.01"
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
          />
        </div>
        <div>
          <label htmlFor="status" className="block text-sm font-medium text-gray-700">Estado del Registro</label>
          <select
            name="status" value={formData.status} onChange={handleFormChange}
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
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

export default ProjectForm;
