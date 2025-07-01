import React from 'react';
import StatusBadge from '../../components/common/StatusBadge';

const ComplexityTable = ({ records, isLoading }) => {
  return (
    <div className="overflow-x-auto border rounded-lg">
      <table className="min-w-full divide-y divide-gray-200">
        <thead className="bg-gray-50">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Proyecto</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Factor de Utilidad Aplicado</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Estado del Vínculo</th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {isLoading && <tr><td colSpan="3" className="text-center p-4">Cargando...</td></tr>}
          {!isLoading && records.map((record, index) => (
            // Usamos un índice para la clave porque la PK es compuesta
            <tr key={`${record.projectUtilityId}-${record.utilityFactorId}-${index}`}
                className={`${record.status === '*' ? 'bg-red-50 text-gray-500' : ''}`}
            >
              <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                <span className="font-bold text-gray-900">{`#${record.projectUtilityId}`}</span> {record.projectName}
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{record.utilityFactorDescription}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">
                <StatusBadge status={record.status} />
              </td>
            </tr>
          ))}
          {!isLoading && records.length === 0 && (
            <tr><td colSpan="3" className="text-center p-4 text-gray-500">No hay relaciones de complejidad para mostrar con los filtros actuales.</td></tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ComplexityTable;
