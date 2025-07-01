import React from 'react';
import { Link } from 'react-router-dom';

const subsystems = [
  {
    name: 'Gestión de Proyectos (G1)',
    items: [
      { path: '/projects', label: 'Proyectos' },
      { path: '/stages', label: 'Etapas' },
      { path: '/activities', label: 'Actividades' },
      { path: '/clients', label: 'Clientes' },
    ],
  },
  {
    name: 'Recursos Humanos (G2)',
    items: [
      { path: '/employees', label: 'Empleados' },
      { path: '/positions', label: 'Cargos (Maestro)' },
      { path: '/experience-levels', label: 'Niveles de Experiencia (Maestro)' },
      { path: '/availabilities', label: 'Disponibilidad' },
    ],
  },
  {
    name: 'Gestión Operativa (G3)',
    items: [
      { path: '/tasks', label: 'Tareas' },
      { path: '/task-assignments', label: 'Asignación de Tareas' },
      { path: '/task-executions', label: 'Ejecución de Tareas' },
      { path: '/project-utilities', label: 'Utilidad de Proyecto' },
    ],
  },
  {
    name: 'Configuración y Catálogos (GZ/G4)',
    items: [
      { path: '/simple-catalogs/client-types', label: 'Tipos de Cliente' },
      { path: '/simple-catalogs/project-status', label: 'Estados de Proyecto' },
      { path: '/simple-catalogs/task-types', label: 'Tipos de Tarea' },
      { path: '/simple-catalogs/priorities', label: 'Prioridades' },
      { path: '/utility-factors', label: 'Factores de Utilidad (Maestro)' },
      { path: '/time-factors', label: 'Factores de Tiempo (Maestro)' },
      { path: '/complexities', label: 'Rel. Complejidad (Lectura)' },
    ],
  },
];

const HomePage = () => {
  return (
    <div className="p-6 md:p-10">
      <h1 className="text-4xl font-bold text-gray-800 mb-2">Panel de Administración</h1>
      <p className="text-lg text-gray-600 mb-8">Seleccione una entidad para comenzar a gestionar.</p>
      
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {subsystems.map((subsystem) => (
          <div key={subsystem.name} className="bg-white rounded-lg shadow-md p-6">
            <h2 className="text-xl font-semibold text-gray-700 border-b pb-3 mb-4">{subsystem.name}</h2>
            <ul className="space-y-2">
              {subsystem.items.map((item) => (
                <li key={item.path}>
                  <Link
                    to={item.path}
                    className="block p-2 rounded-md text-blue-600 hover:bg-blue-50 hover:text-blue-800 font-medium transition-colors"
                  >
                    {item.label}
                  </Link>
                </li>
              ))}
            </ul>
          </div>
        ))}
      </div>
    </div>
  );
};

export default HomePage;
