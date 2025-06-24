import React from 'react'

export default function CatalogForm({ formData, setFormData, action, columns }) {
  const isReadOnly = (fieldKey) => {
    const idKey = columns.length > 0 ? columns[0].key : 'id';

    if (action === 'edit' && fieldKey === idKey) return true;
    if (['delete', 'inactivate', 'reactivate'].includes(action)) return true;
    return false;
  };

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prev) => ({ ...prev, [name]: value }))
  }

  const formColumns = columns.filter(col => col.key !== 'status');

  return (
    <div className={`grid grid-cols-1 md:grid-cols-${formColumns.length + 1} gap-4 border p-4 rounded bg-white shadow`}>
      {formColumns.map((col) => (
        <div key={col.key}>
          <label className="block text-sm font-medium text-gray-700">{col.header}</label>
          <input
            type="text"
            name={col.key}
            value={formData[col.key] || ''}
            onChange={handleChange}
            disabled={isReadOnly(col.key)}
            className="mt-1 block w-full border px-3 py-2 rounded shadow-sm"
          />
        </div>
      ))}
      
      <div>
        <label className="block text-sm font-medium text-gray-700">Estado</label>
        <input
          type="text"
          name="status"
          value={formData.status || 'A'}
          disabled
          className="mt-1 block w-full border px-3 py-2 rounded shadow-sm bg-gray-100"
        />
      </div>
    </div>
  )
}