import React from 'react'

export default function CatalogTable({ records, idKey, descriptionKey, onSelect }) {
  return (
    <div className="mt-4 border rounded overflow-x-auto">
      <table className="min-w-full divide-y divide-gray-200 text-sm text-left">
        <thead className="bg-gray-100">
          <tr>
            <th className="px-4 py-2 font-medium">Código</th>
            <th className="px-4 py-2 font-medium">Descripción</th>
            <th className="px-4 py-2 font-medium">Estado</th>
          </tr>
        </thead>
        <tbody>
          {records.map((item) => (
            <tr
              key={item[idKey]}
              className="cursor-pointer hover:bg-gray-50"
              onClick={() => onSelect(item)}
            >
              <td className="px-4 py-2">{item[idKey]}</td>
              <td className="px-4 py-2">{item[descriptionKey]}</td>
              <td className="px-4 py-2">{item.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
