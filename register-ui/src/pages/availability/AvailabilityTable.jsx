import React from 'react';
import StatusBadge from '../../components/common/StatusBadge';

const AvailabilityTable = ({ records, handleSelectRecord, selectedRecordId, isLoading }) => {
  
  const getAvailabilityStatusBadge = (status) => {
    const styles = {
      D: 'bg-green-100 text-green-800',
      O: 'bg-yellow-100 text-yellow-800',
      V: 'bg-blue-100 text-blue-800',
    };
    const text = { D: 'Disponible', O: 'Ocupado', V: 'Vacaciones' };
    return <span className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full ${styles[status] || ''}`}>{text[status] || status}</span>;
  };
  
  return (
    <div className="overflow-x-auto border rounded-lg">
      <table className="min-w-full divide-y divide-gray-200">
        <thead className="bg-gray-50">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">ID Empleado</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Nombre Empleado</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Estado Disponibilidad</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Carga Semanal</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Carga Actual</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Estado Registro</th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {isLoading && <tr><td colSpan="5" className="text-center p-4">Cargando...</td></tr>}
          {!isLoading && records.map((record) => (
            <tr
              key={record.employeeId}
              onClick={() => handleSelectRecord(record)}
              className={`cursor-pointer transition-colors 
                ${record.status === '*' ? 'bg-red-50 text-gray-500' : ''}
                ${selectedRecordId === record.employeeId ? 'bg-blue-200' : 'hover:bg-gray-50'}`}
            >
              <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">{record.employeeId}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.employeeName}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{getAvailabilityStatusBadge(record.availabilityStatus)}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.weeklyCapacity} h</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.currentLoad} h</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">
                <StatusBadge status={record.status} />
              </td>
            </tr>
          ))}
          {!isLoading && records.length === 0 && (
            <tr><td colSpan="5" className="text-center p-4 text-gray-500">No hay registros para mostrar.</td></tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default AvailabilityTable;
