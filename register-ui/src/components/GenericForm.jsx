import React from 'react'

export default function GenericForm({ formData, setFormData, fieldsConfig, mode }) {
  const isFormDisabled = mode === 'view'

  const handleChange = (e) => {
    const { name, value, type } = e.target
    const finalValue = type === 'number' ? (value === '' ? '' : parseFloat(value)) : value
    setFormData((prev) => ({ ...prev, [name]: finalValue }))
  }

  return (
    <div className={`transition-all duration-300 ${isFormDisabled ? 'opacity-60 pointer-events-none' : 'opacity-100'}`}>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 p-4 border rounded-md bg-white shadow-sm">
        {fieldsConfig.map((field) => {
          const isReadOnly = isFormDisabled || field.readOnly || (mode === 'edit' && field.readOnlyOnEdit)
          return (
            <div key={field.name}>
              <label htmlFor={field.name} className="block text-sm font-medium text-gray-700 mb-1">
                {field.label}
              </label>
              <input
                type={field.type || 'text'}
                id={field.name}
                name={field.name}
                value={formData[field.name] ?? ''}
                onChange={handleChange}
                step={field.step || 'any'}
                disabled={isReadOnly}
                className={`mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm
                          focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm
                          ${isReadOnly ? 'bg-gray-100 cursor-not-allowed' : 'bg-white'}`}
              />
            </div>
          )
        })}
      </div>
    </div>
  )
}
