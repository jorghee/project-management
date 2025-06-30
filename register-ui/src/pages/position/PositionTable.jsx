import React from 'react';

const PositionTable = ({ records, handleSelectRecord, selectedRecordId, isLoading }) => {
  return (
    <div className="overflow-x-auto border rounded-lg">
      <table className="min-w-full divide-y divide-gray-200">
        <thead className="bg-gray-50">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Código</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Descripción</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Costo/Hora</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">Estado</th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {isLoading && <tr><td colSpan="4" className="text-center p-4">Cargando...</td></tr>}
          {!isLoading && records.map((record) => (
            <tr
              key={record.id}
              onClick={() => handleSelectRecord(record)}
              className={`cursor-pointer transition-colors ${selectedRecordId === record.id ? 'bg-blue-100' : 'hover:bg-gray-50'}`}
            >
              <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{record.id}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{record.description}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">${record.costPerHour.toFixed(2)}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">
                {record.status === 'A'
                  ? <span className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">Activo</span>
                  : <span className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-yellow-100 text-yellow-800">Inactivo</span>}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PositionTable;
