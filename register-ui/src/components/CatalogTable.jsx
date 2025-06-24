import React from 'react'

export default function CatalogTable({ records, columns, onSelect }) {
  const idKey = columns.length > 0 ? columns[0].key : 'id';

  return (
    <div className="mt-4 border rounded overflow-x-auto">
      <table className="min-w-full divide-y divide-gray-200 text-sm text-left">
        <thead className="bg-gray-100">
          <tr>
            {columns.map((col) => (
              <th key={col.key} className="px-4 py-2 font-medium">
                {col.header}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {records.map((item) => (
            <tr
              key={item[idKey]}
              className="cursor-pointer hover:bg-gray-50"
              onClick={() => onSelect(item)}
            >
              {columns.map((col) => (
                <td key={`${item[idKey]}-${col.key}`} className="px-4 py-2">
                  {item[col.key]}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}