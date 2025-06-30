import React from 'react';

const StatusBadge = ({ status }) => {
  const styles = {
    A: 'bg-green-100 text-green-800',
    I: 'bg-yellow-100 text-yellow-800',
    '*': 'bg-red-100 text-red-800',
  };
  const text = { A: 'Activo', I: 'Inactivo', '*': 'Eliminado' };
  
  return (
    <span className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full ${styles[status] || 'bg-gray-100 text-gray-800'}`}>
      {text[status] || status}
    </span>
  );
};

export default StatusBadge;
