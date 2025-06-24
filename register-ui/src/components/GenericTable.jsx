import React from 'react'

export default function GenericTable({ records, fieldsConfig, onSelectRecord, selectedRecordId }) {

  const getStatusBadge = (status) => {
    switch (status) {
      case 'A':
        return <span className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">Activo</span>
      case 'I':
        return <span className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-yellow-100 text-yellow-800">Inactivo</span>
      case '*':
        return <span className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-red-100 text-red-800">Eliminado</span>
      default:
        return status
    }
  }

  return (
    <div className="overflow-x-auto border border-gray-200 rounded-lg shadow-sm">
      <table className="min-w-full divide-y divide-gray-200">
        <thead className="bg-gray-100">
          <tr>
            {fieldsConfig.map((field) => (
              <th key={field.name} scope="col" className="px-6 py-3 text-left text-xs font-bold text-gray-600 uppercase tracking-wider">
                {field.label}
              </th>
            ))}
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {records.map((record) => (
            <tr
              key={record.id}
              onClick={() => onSelectRecord(record)}
              className={`cursor-pointer transition-colors ${
                selectedRecordId === record.id
                  ? 'bg-blue-100'
                  : 'hover:bg-gray-50'
              }`}
            >
              {fieldsConfig.map((field) => (
                <td key={`${record.id}-${field.name}`} className="px-6 py-4 whitespace-nowrap text-sm text-gray-800">
                  {field.name === 'status' ? getStatusBadge(record.status) : record[field.name]}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
