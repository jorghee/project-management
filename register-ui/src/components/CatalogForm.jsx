import React from 'react'

export default function CatalogForm({ formData, setFormData, action, idKey, descriptionKey }) {
  const isReadOnly = (field) => {
    if (action === 'edit' && field === idKey) return true
    if (['delete', 'inactivate', 'reactivate'].includes(action)) return true
    if (field === 'status') return true
    return false
  }

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prev) => ({ ...prev, [name]: value }))
  }

  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-4 border p-4 rounded bg-white shadow">
      <div>
        <label className="block text-sm font-medium text-gray-700">Código</label>
        <input
          type="text"
          name={idKey}
          value={formData[idKey]}
          onChange={handleChange}
          disabled={isReadOnly(idKey)}
          className="mt-1 block w-full border px-3 py-2 rounded shadow-sm"
        />
      </div>
      <div>
        <label className="block text-sm font-medium text-gray-700">Descripción</label>
        <input
          type="text"
          name={descriptionKey}
          value={formData[descriptionKey]}
          onChange={handleChange}
          disabled={isReadOnly(descriptionKey)}
          className="mt-1 block w-full border px-3 py-2 rounded shadow-sm"
        />
      </div>
      <div>
        <label className="block text-sm font-medium text-gray-700">Estado</label>
        <input
          type="text"
          name="status"
          value={formData.status}
          disabled
          className="mt-1 block w-full border px-3 py-2 rounded shadow-sm bg-gray-100"
        />
      </div>
    </div>
  )
}
