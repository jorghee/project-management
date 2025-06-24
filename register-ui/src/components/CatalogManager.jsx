import { useEffect, useState } from 'react';
import CatalogForm from './CatalogForm';
import CatalogTable from './CatalogTable';

export default function CatalogManager({ title, endpoint, idKey, descriptionKey }) {
  const [records, setRecords] = useState([]);
  const [formData, setFormData] = useState({ [idKey]: '', [descriptionKey]: '', status: 'A' });
  const [flag, setFlag] = useState(0);
  const [action, setAction] = useState(null);

  useEffect(() => {
    fetch(endpoint)
      .then(res => res.json())
      .then(data => setRecords(data));
  }, [endpoint]);

  const resetForm = () => {
    setFormData({ [idKey]: '', [descriptionKey]: '', status: 'A' });
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
  };

  return (
    <div className="p-6 space-y-4">
      <h1 className="text-2xl font-bold">{title}</h1>

      <CatalogForm
        formData={formData}
        setFormData={setFormData}
        action={action}
        idKey={idKey}
        descriptionKey={descriptionKey}
      />

      <div className="flex flex-wrap gap-2 mt-4">
        <button
          className="px-4 py-2 rounded bg-green-600 text-white hover:bg-green-700 transition"
          onClick={() => handleCommand('add')}
        >
          Adicionar
        </button>
        <button
          className="px-4 py-2 rounded bg-yellow-500 text-white hover:bg-yellow-600 transition"
          onClick={() => handleCommand('edit')}
        >
          Modificar
        </button>
        <button
          className="px-4 py-2 rounded bg-red-600 text-white hover:bg-red-700 transition"
          onClick={() => handleCommand('delete')}
        >
          Eliminar
        </button>
        <button
          className="px-4 py-2 rounded bg-gray-500 text-white hover:bg-gray-600 transition"
          onClick={() => handleCommand('inactivate')}
        >
          Inactivar
        </button>
        <button
          className="px-4 py-2 rounded bg-blue-500 text-white hover:bg-blue-600 transition"
          onClick={() => handleCommand('reactivate')}
        >
          Reactivar
        </button>
        <button
          className="px-4 py-2 rounded bg-indigo-600 text-white hover:bg-indigo-700 transition"
          onClick={() => handleCommand('update')}
        >
          Actualizar
        </button>
        <button
          className="px-4 py-2 rounded bg-neutral-600 text-white hover:bg-neutral-700 transition"
          onClick={() => handleCommand('cancel')}
        >
          Cancelar
        </button>
      </div>
      <CatalogTable
        records={records}
        idKey={idKey}
        descriptionKey={descriptionKey}
        onSelect={handleSelect}
      />
    </div>
  );
}
