import React from 'react';

const ProjectUtilityForm = ({ formData, handleFormChange, handleSubmit, isLoading, isEditing, projects, timeFactors, existingUtilityIds }) => {
  
  // Filtramos los proyectos para mostrar solo aquellos que aún no tienen una utilidad.
  const availableProjects = isEditing 
    ? projects 
    : projects.filter(p => !existingUtilityIds.includes(p.id));

  return (
    <form onSubmit={handleSubmit} className="mb-6 p-4 border rounded-md bg-gray-50 space-y-4">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <div>
          <label htmlFor="projectId" className="block text-sm font-medium text-gray-700">Proyecto (ID)</label>
          <select 
            name="projectId" value={formData.projectId} onChange={handleFormChange}
            disabled={isEditing} required
            className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm ${isEditing ? 'bg-gray-200' : 'bg-white'}`}
          >
            <option value="">Seleccione un proyecto...</option>
            {/* Si estamos editando, mostramos solo el proyecto actual para no poder cambiarlo */}
            {isEditing ? (
              <option value={formData.projectId}>{projects.find(p => p.id === formData.projectId)?.name || formData.projectId}</option>
            ) : (
              availableProjects.map(p => (
                <option key={p.id} value={p.id}>{p.name}</option>
              ))
            )}
          </select>
        </div>

        <div>
          <label htmlFor="timeFactorId" className="block text-sm font-medium text-gray-700">Factor de Tiempo</label>
          <select name="timeFactorId" value={formData.timeFactorId} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="">Seleccione un factor...</option>
            {timeFactors.map(f => (
              <option key={f.id} value={f.id}>{f.description}</option>
            ))}
          </select>
        </div>

        <div>
          <label htmlFor="experienceFactor" className="block text-sm font-medium text-gray-700">Factor Experiencia (%)</label>
          <input type="number" name="experienceFactor" value={formData.experienceFactor} onChange={handleFormChange} min="0" step="0.01" className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm" />
        </div>
 
        <div>
          <label htmlFor="basePercentage" className="block text-sm font-medium text-gray-700">Utilidad estimada (%)</label>
          <input type="number" name="basePercentage" value={formData.basePercentage} onChange={handleFormChange} min="0" step="0.01" className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm" />
        </div>
       
        <div>
          <label htmlFor="status" className="block text-sm font-medium text-gray-700">Estado del Registro</label>
          <select name="status" value={formData.status} onChange={handleFormChange}
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

export default ProjectUtilityForm;
