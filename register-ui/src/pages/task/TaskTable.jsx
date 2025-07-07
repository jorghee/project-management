import React from 'react';
import StatusBadge from '../../components/common/StatusBadge';

const TaskTable = ({ records, handleSelectRecord, selectedRecordId, isLoading }) => {
  
  const getTaskStatusBadge = (status) => {
    const styles = {
      P: 'bg-gray-200 text-gray-800',
      E: 'bg-blue-100 text-blue-800',
      F: 'bg-green-100 text-green-800',
      C: 'bg-orange-100 text-orange-800',
    };
    const text = { P: 'Pendiente', E: 'En Ejecución', F: 'Finalizada', C: 'Cancelada' };
    return <span className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full ${styles[status] || ''}`}>{text[status] || status}</span>;
  };

  return (
    <div className="overflow-x-auto border rounded-lg">
      <table className="min-w-full divide-y divide-gray-200">
        <thead className="bg-gray-50">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Código</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Descripción</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Actividad</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Tipo Tarea</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Prioridad</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Tiempo Estimado</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Tiempo Real</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Estado Tarea</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Estado Registro</th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {isLoading && <tr><td colSpan="6" className="text-center p-4">Cargando...</td></tr>}
          {!isLoading && records.map((record) => (
            <tr
              key={record.id}
              onClick={() => handleSelectRecord(record)}
              className={`cursor-pointer transition-colors 
                ${record.status === '*' ? 'bg-red-50 text-gray-500' : ''}
                ${selectedRecordId === record.id ? 'bg-blue-200' : 'hover:bg-gray-50'}`}
            >
              <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">{record.id}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.description}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.activityName}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.taskTypeDescription}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.priorityDescription}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.estimatedTime} h</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.realTime} h</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{getTaskStatusBadge(record.taskStatus)}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">
                <StatusBadge status={record.status} />
              </td>
            </tr>
          ))}
          {!isLoading && records.length === 0 && (
            <tr><td colSpan="10" className="text-center p-4 text-gray-500">No hay registros para mostrar.</td></tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default TaskTable;
