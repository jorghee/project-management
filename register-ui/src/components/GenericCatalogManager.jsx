import { useEffect, useState } from 'react'
import GenericForm from './GenericForm.jsx'
import GenericTable from './GenericTable.jsx'
import apiService from '../services/apiService.js'

export default function GenericCatalogManager({ title, endpoint, fieldsConfig }) {
  const [records, setRecords] = useState([])
  const [selectedRecord, setSelectedRecord] = useState(null)
  const [formData, setFormData] = useState({})
  const [mode, setMode] = useState('view') // 'view', 'add', 'edit'
  const [error, setError] = useState('')

  const getInitialFormData = () => {
    const initialState = fieldsConfig.reduce((acc, field) => {
      acc[field.name] = field.type === 'number' ? '' : ''
      return acc
    }, {})
    initialState.status = 'A'
    return initialState
  }

  useEffect(() => {
    fetchRecords()
  }, [endpoint])

  useEffect(() => {
    if (selectedRecord && mode === 'view') {
      setFormData(selectedRecord)
    }
  }, [selectedRecord, mode])

  const fetchRecords = async () => {
    try {
      const data = await apiService.getAll(endpoint)
      setRecords(data)
      setError('')
    } catch (err) {
      setError(`Error al cargar datos: ${err.message}`)
    }
  }

  const handleAdd = () => {
    setMode('add')
    setSelectedRecord(null)
    setFormData(getInitialFormData())
  }

  const handleEdit = () => {
    if (!selectedRecord) {
      alert('Por favor, seleccione un registro para modificar.')
      return
    }
    setMode('edit')
    setFormData(selectedRecord)
  }

  const handleChangeStatus = async (newStatus) => {
    if (!selectedRecord) {
      alert(`Por favor, seleccione un registro.`)
      return
    }
    
    const statusText = {
      '*': 'eliminar',
      'I': 'inactivar',
      'A': 'reactivar'
    };
    
    if (window.confirm(`¿Está seguro de que desea ${statusText[newStatus]} este registro?`)) {
      try {
        const updatedRecord = { ...selectedRecord, status: newStatus }
        await apiService.update(endpoint, selectedRecord.id, updatedRecord)
        await fetchRecords()
        resetState()
      } catch (err) {
        setError(`Error al ${statusText[newStatus]}: ${err.message}`)
      }
    }
  }

  const handleCancel = () => {
    resetState()
  }
  
  const handleUpdate = async () => {
    try {
      if (mode === 'add') {
        await apiService.create(endpoint, formData)
      } else if (mode === 'edit') {
        await apiService.update(endpoint, formData.id, formData)
      }
      await fetchRecords()
      resetState()
    } catch (err) {
      setError(`Error al guardar: ${err.message}`)
    }
  }
  
  const resetState = () => {
    setMode('view')
    setSelectedRecord(null)
    setFormData({})
    setError('')
  }
  
  const isAddingOrEditing = mode === 'add' || mode === 'edit'
  
  return (
    <div className="space-y-6">
      <h2 className="text-2xl font-bold text-gray-700">{title}</h2>

      {error && <p className="text-red-500 bg-red-100 p-3 rounded-md">{error}</p>}
      
      <GenericForm
        formData={formData}
        setFormData={setFormData}
        fieldsConfig={fieldsConfig}
        mode={mode}
      />

      <div className="flex flex-wrap gap-2 border-t pt-4">
        <button onClick={handleAdd} disabled={isAddingOrEditing} className="px-4 py-2 rounded-md font-semibold text-white bg-blue-600 hover:bg-blue-700 disabled:bg-gray-400 disabled:cursor-not-allowed">Adicionar</button>
        <button onClick={handleEdit} disabled={!selectedRecord || isAddingOrEditing} className="px-4 py-2 rounded-md font-semibold text-white bg-yellow-500 hover:bg-yellow-600 disabled:bg-gray-400 disabled:cursor-not-allowed">Modificar</button>
        <button onClick={() => handleChangeStatus('*')} disabled={!selectedRecord || isAddingOrEditing} className="px-4 py-2 rounded-md font-semibold text-white bg-red-600 hover:bg-red-700 disabled:bg-gray-400 disabled:cursor-not-allowed">Eliminar</button>
        <button onClick={() => handleChangeStatus('I')} disabled={!selectedRecord || isAddingOrEditing} className="px-4 py-2 rounded-md font-semibold text-white bg-gray-500 hover:bg-gray-600 disabled:bg-gray-400 disabled:cursor-not-allowed">Inactivar</button>
        <button onClick={() => handleChangeStatus('A')} disabled={!selectedRecord || isAddingOrEditing} className="px-4 py-2 rounded-md font-semibold text-white bg-green-500 hover:bg-green-600 disabled:bg-gray-400 disabled:cursor-not-allowed">Reactivar</button>
        <button onClick={handleUpdate} disabled={!isAddingOrEditing} className="px-4 py-2 rounded-md font-semibold text-white bg-indigo-600 hover:bg-indigo-700 disabled:bg-gray-400 disabled:cursor-not-allowed">Actualizar</button>
        <button onClick={handleCancel} disabled={!isAddingOrEditing} className="px-4 py-2 rounded-md font-semibold text-gray-800 bg-gray-200 hover:bg-gray-300 disabled:bg-gray-400 disabled:cursor-not-allowed">Cancelar</button>
      </div>

      <GenericTable
        records={records}
        fieldsConfig={fieldsConfig}
        onSelectRecord={setSelectedRecord}
        selectedRecordId={selectedRecord?.id}
      />
    </div>
  )
}
