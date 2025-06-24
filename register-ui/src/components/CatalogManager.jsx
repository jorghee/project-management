import { useEffect, useState } from 'react';
import CatalogForm from './CatalogForm';
import CatalogTable from './CatalogTable';

export default function CatalogManager({ title, endpoint, columns }) {
  
  const createInitialFormData = () => {
    const initialData = {};
    columns.forEach(col => {
      initialData[col.key] = col.key === 'status' ? 'A' : '';
    });
    return initialData;
  };

  const [records, setRecords] = useState([]);
  const [formData, setFormData] = useState(createInitialFormData());
  const [flag, setFlag] = useState(0);
  const [action, setAction] = useState(null);

  useEffect(() => {
    fetch(endpoint)
      .then(res => res.json())
      .then(data => setRecords(data))
      .catch(err => console.error(`Error fetching data from ${endpoint}:`, err));
  }, [endpoint]);

  const resetForm = () => {
    setFormData(createInitialFormData());
    setFlag(0);
    setAction(null);
  };

  const handleSelect = (record) => {
    setFormData(record);
  };

  const handleCommand = (cmd) => {
    if (cmd === 'add') {
      resetForm();
      setFlag(1);
      setAction('add');
    } else if (['edit', 'delete', 'inactivate', 'reactivate'].includes(cmd)) {
      setFlag(1);
      setAction(cmd);
      const updated = { ...formData };
      if (cmd === 'delete') updated.status = '*';
      if (cmd === 'inactivate') updated.status = 'I';
      if (cmd === 'reactivate') updated.status = 'A';
      setFormData(updated);
    } else if (cmd === 'cancel') {
      resetForm();
    } else if (cmd === 'update' && flag === 1) {
      const idKey = columns.length > 0 ? columns[0].key : 'id';
      const method = action === 'add' ? 'POST' : 'PUT';
      const url = action === 'add' ? endpoint : `${endpoint}/${formData[idKey]}`;
      
      fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData)
      }).then(() => {
        fetch(endpoint)
          .then(res => res.json())
          .then(data => setRecords(data));
        resetForm();
      });
    }
    // --- LÓGICA AÑADIDA PARA EL BOTÓN SALIR ---
    else if (cmd === 'exit') {
      // Intenta cerrar la ventana/pestaña actual.
      // Esto funcionará porque la pestaña fue abierta por un script.
      window.close();
    }
  };

  return (
    <div className="p-6 space-y-4">
      <h1 className="text-2xl font-bold">{title}</h1>

      <CatalogForm
        formData={formData}
        setFormData={setFormData}
        action={action}
        columns={columns}
      />

      <div className="flex flex-wrap gap-2 mt-4">
        {/* Tus botones de comando actuales */}
        <button className="px-4 py-2 rounded bg-green-600 text-white hover:bg-green-700 transition" onClick={() => handleCommand('add')}>Adicionar</button>
        <button className="px-4 py-2 rounded bg-yellow-500 text-white hover:bg-yellow-600 transition" onClick={() => handleCommand('edit')}>Modificar</button>
        <button className="px-4 py-2 rounded bg-red-600 text-white hover:bg-red-700 transition" onClick={() => handleCommand('delete')}>Eliminar</button>
        <button className="px-4 py-2 rounded bg-gray-500 text-white hover:bg-gray-600 transition" onClick={() => handleCommand('inactivate')}>Inactivar</button>
        <button className="px-4 py-2 rounded bg-blue-500 text-white hover:bg-blue-600 transition" onClick={() => handleCommand('reactivate')}>Reactivar</button>
        <button className="px-4 py-2 rounded bg-indigo-600 text-white hover:bg-indigo-700 transition" onClick={() => handleCommand('update')}>Actualizar</button>
        <button className="px-4 py-2 rounded bg-neutral-600 text-white hover:bg-neutral-700 transition" onClick={() => handleCommand('cancel')}>Cancelar</button>
        
        {/* --- NUEVO BOTÓN DE SALIR --- */}
        <button 
          className="px-4 py-2 rounded bg-pink-600 text-white hover:bg-pink-700 transition" 
          onClick={() => handleCommand('exit')}
        >
          Salir
        </button>
      </div>

      <CatalogTable
        records={records}
        onSelect={handleSelect}
        columns={columns}
      />
    </div>
  );
}