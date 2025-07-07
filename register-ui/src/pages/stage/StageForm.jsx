import React from 'react';

const StageForm = ({ formData, handleFormChange, handleSubmit, isLoading, isEditing, projects }) => {
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
          <label htmlFor="name" className="block text-sm font-medium text-gray-700">Nombre de la Etapa</label>
          <input
            type="text" name="name" value={formData.name} onChange={handleFormChange}
            required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
          />
        </div>
        <div>
          <label htmlFor="projectId" className="block text-sm font-medium text-gray-700">Proyecto</label>
          <select
            name="projectId" value={formData.projectId} onChange={handleFormChange}
            required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
          >
            <option value="">Seleccione un proyecto...</option>
            {projects.map(p => (
              <option key={p.id} value={p.id}>{p.name}</option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="estimatedTime" className="block text-sm font-medium text-gray-700">Tiempo Estimado (h)</label>
          <input
            type="number" name="estimatedTime" value={formData.estimatedTime} onChange={handleFormChange}
            min="0"
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm"
          />
        </div>
        <div>
          <label htmlFor="status" className="block text-sm font-medium text-gray-700">Estado del Registro</label>
          <select
            name="status" value={formData.status} onChange={handleFormChange}
            disabled={!isEditing} className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 ${!isEditing ? 'bg-gray-200 cursor-not-allowed' : 'bg-white'}`}
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

export default StageForm;
