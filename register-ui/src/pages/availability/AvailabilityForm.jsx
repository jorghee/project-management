import React from 'react';

const AvailabilityForm = ({ formData, handleFormChange, handleSubmit, isLoading, isEditing, employees, existingAvailabilityIds }) => {
  
  // Filtramos los empleados para mostrar solo aquellos que aún no tienen un registro de disponibilidad.
  const availableEmployees = isEditing 
    ? employees 
    : employees.filter(e => !existingAvailabilityIds.includes(e.id));

  return (
    <form onSubmit={handleSubmit} className="mb-6 p-4 border rounded-md bg-gray-50 space-y-4">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <div>
          <label htmlFor="employeeId" className="block text-sm font-medium text-gray-700">Empleado (ID)</label>
          <select 
            name="employeeId" value={formData.employeeId} onChange={handleFormChange}
            disabled={isEditing} required
            className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm ${isEditing ? 'bg-gray-200' : 'bg-white'}`}
          >
            <option value="">Seleccione un empleado...</option>
            {/* Si estamos editando, mostramos solo el empleado actual para no poder cambiarlo */}
            {isEditing ? (
              <option value={formData.employeeId}>{employees.find(e => e.id === formData.employeeId)?.name || `Empleado ${formData.employeeId}`}</option>
            ) : (
              availableEmployees.map(e => (
                <option key={e.id} value={e.id}>{e.name}</option>
              ))
            )}
          </select>
        </div>

        <div>
          <label htmlFor="availabilityStatus" className="block text-sm font-medium text-gray-700">Estado de Disponibilidad</label>
          <select name="availabilityStatus" value={formData.availabilityStatus} onChange={handleFormChange} required className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="D">Disponible</option>
            <option value="O">Ocupado</option>
            <option value="V">Vacaciones</option>
          </select>
        </div>

        <div>
          <label htmlFor="weeklyCapacity" className="block text-sm font-medium text-gray-700">Capacidad Semanal (h)</label>
          <input type="number" name="weeklyCapacity" value={formData.weeklyCapacity} onChange={handleFormChange} min="0" className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm" />
        </div>
        
        <div>
          <label htmlFor="currentLoad" className="block text-sm font-medium text-gray-700">Carga Actual (h)</label>
          <input type="number" name="currentLoad" value={formData.currentLoad} onChange={handleFormChange} min="0" className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm" />
        </div>
        
        <div>
          <label htmlFor="status" className="block text-sm font-medium text-gray-700">Estado del Registro</label>
          <select name="status" value={formData.status} onChange={handleFormChange} className="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm">
            <option value="A">Activo</option>
            <option value="I">Inactivo</option>
            {isEditing && formData.status === '*' && <option value="*" disabled>Eliminado</option>}
          </select>
        </div>
      </div>
    </form>
  );
};

export default AvailabilityForm;
