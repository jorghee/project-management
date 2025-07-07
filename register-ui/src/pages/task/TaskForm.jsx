import React from 'react';

const TaskForm = ({ formData, handleFormChange, handleSubmit, isLoading, isEditing, activities, taskTypes, priorities }) => {
  return (
    <form onSubmit={handleSubmit} className="mb-6 p-4 border rounded-md bg-gray-50 space-y-4">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <div>
          <label htmlFor="id" className="block text-sm font-medium text-gray-700">Código (ID)</label>
          <input type="number" name="id" value={formData.id} onChange={handleFormChange} disabled={isEditing} required className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm ${isEditing ? 'bg-gray-200' : 'bg-white'}`} />
        </div>
        
        <div className="col-span-1 md:col-span-2 lg:col-span-3">
          <label htmlFor="description" className="block text-sm font-medium text-gray-700">Descripción de la Tarea</label>
          <textarea name="description" value={formData.description} onChange={handleFormChange} required rows="1" className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm" />
        </div>
        
        <div>
          <label htmlFor="activityId" className="block text-sm font-medium text-gray-700">Actividad</label>
          <select name="activityId" value={formData.activityId} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="">Seleccione una actividad...</option>
            {activities.map(a => (
              <option key={a.id} value={a.id}>{a.name}</option>
            ))}
          </select>
        </div>

        <div>
          <label htmlFor="taskTypeId" className="block text-sm font-medium text-gray-700">Tipo de Tarea</label>
          <select name="taskTypeId" value={formData.taskTypeId} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="">Seleccione un tipo...</option>
            {taskTypes.map(t => (
              <option key={t.id} value={t.id}>{t.description}</option>
            ))}
          </select>
        </div>

        <div>
          <label htmlFor="priorityId" className="block text-sm font-medium text-gray-700">Prioridad</label>
          <select name="priorityId" value={formData.priorityId} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="">Seleccione una prioridad...</option>
            {priorities.map(p => (
              <option key={p.id} value={p.id}>{p.description}</option>
            ))}
          </select>
        </div>
        
        <div>
          <label htmlFor="taskStatus" className="block text-sm font-medium text-gray-700">Estado de la Tarea</label>
          <select name="taskStatus" value={formData.taskStatus} onChange={handleFormChange} className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="P">Pendiente</option>
            <option value="E">En Ejecución</option>
            <option value="F">Finalizada</option>
            <option value="C">Cancelada</option>
          </select>
        </div>

        <div>
          <label htmlFor="estimatedTime" className="block text-sm font-medium text-gray-700">Tiempo Estimado (h)</label>
          <input type="number" name="estimatedTime" value={formData.estimatedTime} onChange={handleFormChange} min="0" className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm" />
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

export default TaskForm;
