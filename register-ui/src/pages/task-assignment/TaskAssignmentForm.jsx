import React from 'react';

const TaskAssignmentForm = ({ formData, handleFormChange, handleSubmit, isLoading, isEditing, employees, tasks }) => {
  return (
    <form onSubmit={handleSubmit} className="mb-6 p-4 border rounded-md bg-gray-50 space-y-4">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <div>
          <label htmlFor="id" className="block text-sm font-medium text-gray-700">Código Asignación (ID)</label>
          <input type="number" name="id" value={formData.id} onChange={handleFormChange} disabled={isEditing} required className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm ${isEditing ? 'bg-gray-200' : 'bg-white'}`} />
        </div>

        <div>
          <label htmlFor="employeeId" className="block text-sm font-medium text-gray-700">Empleado</label>
          <select name="employeeId" value={formData.employeeId} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="">Seleccione un empleado...</option>
            {employees.map(e => (
              <option key={e.id} value={e.id}>{e.name}</option>
            ))}
          </select>
        </div>
        
        <div>
          <label htmlFor="taskId" className="block text-sm font-medium text-gray-700">Tarea</label>
          <select name="taskId" value={formData.taskId} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="">Seleccione una tarea...</option>
            {tasks.map(t => (
              <option key={t.id} value={t.id}>{t.id} - {t.description}</option>
            ))}
          </select>
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

export default TaskAssignmentForm;
