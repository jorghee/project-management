import React from 'react';
import StatusBadge from '../../components/common/StatusBadge';

const ProjectTable = ({ records, handleSelectRecord, selectedRecordId, isLoading }) => {
  return (
    <div className="overflow-x-auto border rounded-lg">
      <table className="min-w-full divide-y divide-gray-200">
        <thead className="bg-gray-50">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Código</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Nombre del Proyecto</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Cliente</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Estado Proyecto</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Fecha Inicio</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Fecha Fin</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Monto Estimado</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Monto Real</th>
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
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.name}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.clientName}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.projectStatusDescription}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.startDate}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.endDate}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">
                {typeof record.estimatedAmount === 'number' ? `$${record.estimatedAmount.toFixed(2)}` : 'N/A'}
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">
                {typeof record.realAmount === 'number' ? `$${record.realAmount.toFixed(2)}` : 'N/A'}
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">
                <StatusBadge status={record.status} />
              </td>
            </tr>
          ))}
          {!isLoading && records.length === 0 && (
            <tr><td colSpan="6" className="text-center p-4 text-gray-500">No hay registros para mostrar.</td></tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ProjectTable;
