import React from 'react';

const TaskExecutionForm = ({ formData, handleFormChange, handleSubmit, isLoading, isEditing, assignments }) => {
  return (
    <form onSubmit={handleSubmit} className="mb-6 p-4 border rounded-md bg-gray-50 space-y-4">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <div>
          <label htmlFor="id" className="block text-sm font-medium text-gray-700">Código Ejecución (ID)</label>
          <input type="number" name="id" value={formData.id} onChange={handleFormChange} disabled={isEditing} required className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm ${isEditing ? 'bg-gray-200' : 'bg-white'}`} />
        </div>

        <div>
          <label htmlFor="assignmentId" className="block text-sm font-medium text-gray-700">Asignación</label>
          <select name="assignmentId" value={formData.assignmentId} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="">Seleccione una asignación...</option>
            {assignments.map(a => (
              <option key={a.id} value={a.id}>
                {`ID: ${a.id} (Empleado: ${a.employeeName}, Tarea: ${a.taskDescription})`}
              </option>
            ))}
          </select>
        </div>
        
        <div>
          <label htmlFor="executionDate" className="block text-sm font-medium text-gray-700">Fecha de Ejecución</label>
          <input type="date" name="executionDate" value={formData.executionDate || ''} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm" />
        </div>
        
        <div>
          <label htmlFor="hours" className="block text-sm font-medium text-gray-700">Horas</label>
          <input type="number" name="hours" value={formData.hours} onChange={handleFormChange} required min="0" max="12" className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm" />
        </div>

        <div>
          <label htmlFor="minutes" className="block text-sm font-medium text-gray-700">Minutos</label>
          <select name="minutes" value={formData.minutes} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="0">00</option>
            <option value="15">15</option>
            <option value="30">30</option>
            <option value="45">45</option>
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

export default TaskExecutionForm;
