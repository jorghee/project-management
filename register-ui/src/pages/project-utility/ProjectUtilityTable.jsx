import React from 'react';
import StatusBadge from '../../components/common/StatusBadge';

const ProjectUtilityTable = ({ records, handleSelectRecord, selectedRecordId, isLoading }) => {
  return (
    <div className="overflow-x-auto border rounded-lg">
      <table className="min-w-full divide-y divide-gray-200">
        <thead className="bg-gray-50">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Proyecto</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Factor Tiempo</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Factor Experiencia</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Utilidad Estimada</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Utilidad real</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Estado</th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {isLoading && <tr><td colSpan="6" className="text-center p-4">Cargando...</td></tr>}
          {!isLoading && records.map((record) => (
            <tr
              key={record.projectId}
              onClick={() => handleSelectRecord(record)}
              className={`cursor-pointer transition-colors 
                ${record.status === '*' ? 'bg-red-50 text-gray-500' : ''}
                ${selectedRecordId === record.projectId ? 'bg-blue-200' : 'hover:bg-gray-50'}`}
            >
              <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">{record.projectName}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.timeFactorDescription}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.experienceFactor?.toFixed(2)}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.basePercentage?.toFixed(2)}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">{record.finalPercentage?.toFixed(2)}</td>
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

export default ProjectUtilityTable;
