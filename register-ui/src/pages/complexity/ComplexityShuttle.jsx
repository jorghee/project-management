import React, { useState } from 'react';

const ShuttleButton = ({ onClick, children, disabled }) => (
  <button
    type="button"
    onClick={onClick}
    disabled={disabled}
    className="px-3 py-1.5 text-sm font-semibold text-gray-700 bg-gray-200 rounded-md hover:bg-gray-300 disabled:bg-gray-100 disabled:text-gray-400 disabled:cursor-not-allowed"
  >
    {children}
  </button>
);

const FactorList = ({ title, factors, selected, onSelect }) => (
  <div className="flex-1 border rounded-md p-2 bg-white">
    <h3 className="text-sm font-semibold text-gray-600 mb-2 px-2">{title}</h3>
    <ul className="h-64 overflow-y-auto space-y-1">
      {factors.map(factor => (
        <li
          key={factor.id}
          onClick={() => onSelect(factor.id)}
          className={`p-2 rounded-md cursor-pointer text-sm ${selected === factor.id ? 'bg-blue-500 text-white' : 'hover:bg-gray-100'}`}
        >
          {factor.description}
        </li>
      ))}
      {factors.length === 0 && <li className="p-2 text-sm text-gray-400 italic">No hay factores aquí.</li>}
    </ul>
  </div>
);


const ComplexityShuttle = ({ availableFactors, assignedFactors, onAssign, onRemove }) => {
  const [selectedAvailable, setSelectedAvailable] = useState(null);
  const [selectedAssigned, setSelectedAssigned] = useState(null);

  const handleSelectAvailable = (id) => {
    setSelectedAvailable(id);
    setSelectedAssigned(null);
  };

  const handleSelectAssigned = (id) => {
    setSelectedAssigned(id);
    setSelectedAvailable(null);
  };

  const handleAssign = () => {
    if (selectedAvailable) {
      onAssign(selectedAvailable);
      setSelectedAvailable(null);
    }
  };

  const handleRemove = () => {
    if (selectedAssigned) {
      onRemove(selectedAssigned);
      setSelectedAssigned(null);
    }
  };

  return (
    <div className="flex items-center gap-4">
      {/* Lista de Factores Disponibles */}
      <FactorList
        title="Factores Disponibles"
        factors={availableFactors}
        selected={selectedAvailable}
        onSelect={handleSelectAvailable}
      />

      {/* Botones de Acción */}
      <div className="flex flex-col space-y-2">
        <ShuttleButton onClick={handleAssign} disabled={!selectedAvailable}> </ShuttleButton>
        <ShuttleButton onClick={handleRemove} disabled={!selectedAssigned}> </ShuttleButton>
      </div>

      {/* Lista de Factores Asignados */}
      <FactorList
        title="Factores Asignados al Proyecto"
        factors={assignedFactors}
        selected={selectedAssigned}
        onSelect={handleSelectAssigned}
      />
    </div>
  );
};

export default ComplexityShuttle;
