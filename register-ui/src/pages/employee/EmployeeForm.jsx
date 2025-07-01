import React from 'react';

const EmployeeForm = ({ formData, handleFormChange, handleSubmit, isLoading, isEditing, positions, experienceLevels }) => {
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
          <label htmlFor="name" className="block text-sm font-medium text-gray-700">Nombre Completo</label>
          <input
            type="text" name="name" value={formData.name} onChange={handleFormChange}
            required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          />
        </div>

        <div>
          <label htmlFor="positionId" className="block text-sm font-medium text-gray-700">Cargo</label>
          <select
            name="positionId" value={formData.positionId} onChange={handleFormChange}
            required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          >
            <option value="">Seleccione un cargo...</option>
            {positions.map(p => (
              <option key={p.id} value={p.id}>{p.description}</option>
            ))}
          </select>
        </div>

        <div>
          <label htmlFor="experienceLevelId" className="block text-sm font-medium text-gray-700">Nivel de Experiencia</label>
          <select
            name="experienceLevelId" value={formData.experienceLevelId} onChange={handleFormChange}
            required
            className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          >
            <option value="">Seleccione un nivel...</option>
            {experienceLevels.map(e => (
              <option key={e.id} value={e.id}>{e.description}</option>
            ))}
          </select>
        </div>

        <div>
          <label htmlFor="status" className="block text-sm font-medium text-gray-700">Estado</label>
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

export default EmployeeForm;
