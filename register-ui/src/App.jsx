import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import CatalogManager from './components/CatalogManager';

function App() {

  const genericThreeColumns = [
    { header: 'Código', key: 'id' },
    { header: 'Descripción', key: 'description' },
    { header: 'Estado', key: 'status' }
  ];
  const genericFourColumns = [
    { header: 'Código', key: 'id' },
    { header: 'Descripción', key: 'description' },
    { header: 'Valor', key: 'value' },
    { header: 'Estado', key: 'status' }
  ];

  const openCatalogInNewTab = (path) => {
    const url = window.location.origin + path;
    window.open(url, '_blank');
  };

  return (
    <Router>
      <div className="p-6 space-y-6">
        <h1 className="text-3xl font-bold">Tablas referenciales</h1>
        <nav className="flex flex-wrap gap-4 mt-4">

          <button onClick={() => openCatalogInNewTab('/task-types')} className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm">Tipo Tarea</button>
          <button onClick={() => openCatalogInNewTab('/priorities')} className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm">Prioridad Tarea</button>
          <button onClick={() => openCatalogInNewTab('/client-types')} className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm">Tipo de Clientes</button>
          <button onClick={() => openCatalogInNewTab('/positions')} className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm">Gestión de Cargos</button>
          <button onClick={() => openCatalogInNewTab('/project-status')} className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm">Gestión Estados de Proyectos</button>
          <button onClick={() => openCatalogInNewTab('/experience-levels')} className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm">Gestión de Niveles de Experiencia</button>
          <button onClick={() => openCatalogInNewTab('/time-factors')} className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm">Gestión de Factores de Tiempo</button>
          <button onClick={() => openCatalogInNewTab('/utilities-factors')} className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm">Gestión de Factores de Utilidad</button>
        </nav>
        <Routes>
          
          <Route path="/" element={<p>Bienvenido. Selecciona un catálogo para abrirlo en una nueva pestaña.</p>} />

          <Route path="/task-types" element={<CatalogManager title="Tipo Tarea" endpoint="http://localhost:8080/api/task-types" columns={genericThreeColumns} />} />
          <Route path="/priorities" element={<CatalogManager title="Prioridad Tarea" endpoint="http://localhost:8080/api/priorities" columns={genericThreeColumns} />} />
          <Route path="/client-types" element={<CatalogManager title="Tipos de Cliente" endpoint="http://localhost:8080/api/client-types" columns={genericThreeColumns} />} />
          <Route path="/positions" element={<CatalogManager title="Gestión de Cargos" endpoint="http://localhost:8080/api/positions" columns={genericThreeColumns} />} />
          <Route path="/project-status" element={<CatalogManager title="Estados de Proyectos" endpoint="http://localhost:8080/api/project-status" columns={genericThreeColumns} />} />
          <Route path="/experience-levels" element={<CatalogManager title="Niveles de Experiencia" endpoint="http://localhost:8080/api/experience-levels" columns={genericFourColumns} />} />
          <Route path="/time-factors" element={<CatalogManager title="Factores de Tiempo" endpoint="http://localhost:8080/api/time-factors" columns={genericFourColumns} />} />
          <Route path="/utilities-factors" element={<CatalogManager title="Factores de Utilidad" endpoint="http://localhost:8080/api/utility-factors" columns={genericFourColumns} />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;