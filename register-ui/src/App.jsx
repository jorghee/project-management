import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import GenericCatalogManager from './components/GenericCatalogManager.jsx'

const experienceLevelsFields = [
  { name: 'id', label: 'Código', type: 'number', readOnlyOnEdit: true },
  { name: 'description', label: 'Descripción', type: 'text' },
  { name: 'value', label: 'Valor', type: 'number' },
  { name: 'status', label: 'Estado', type: 'text', readOnly: true },
]

const timeFactorsFields = [
  { name: 'id', label: 'Código', type: 'number', readOnlyOnEdit: true },
  { name: 'description', label: 'Descripción', type: 'text' },
  { name: 'value', label: 'Valor (Decimal)', type: 'number', step: '0.1' },
  { name: 'status', label: 'Estado', type: 'text', readOnly: true },
]

const utilityFactorsFields = [
  { name: 'id', label: 'Código', type: 'number', readOnlyOnEdit: true },
  { name: 'description', label: 'Descripción', type: 'text' },
  { name: 'value', label: 'Valor (Decimal)', type: 'number', step: '0.1' },
  { name: 'status', label: 'Estado', type: 'text', readOnly: true },
]

const simpleCatalogFields = [
  { name: 'id', label: 'Código', type: 'number', readOnlyOnEdit: true },
  { name: 'description', label: 'Descripción', type: 'text' },
  { name: 'status', label: 'Estado', type: 'text', readOnly: true },
]

function App() {
  return (
    <Router>
      <div className="bg-gray-50 min-h-screen">
        <header className="bg-white shadow-sm p-4">
          <div className="container mx-auto">
            <h1 className="text-3xl font-bold text-gray-800">Panel de Administración de Catálogos</h1>
          </div>
        </header>

        <main className="container mx-auto p-4 md:p-6 space-y-6">
          <nav className="flex flex-wrap gap-3">
            <Link to="/task-types" className="px-4 py-2 rounded-md bg-white text-blue-700 hover:bg-blue-100 transition font-semibold shadow-sm border border-blue-200">Tipo Tarea</Link>
            <Link to="/priorities" className="px-4 py-2 rounded-md bg-white text-blue-700 hover:bg-blue-100 transition font-semibold shadow-sm border border-blue-200">Prioridad</Link>
            <Link to="/client-types" className="px-4 py-2 rounded-md bg-white text-blue-700 hover:bg-blue-100 transition font-semibold shadow-sm border border-blue-200">Tipo Cliente</Link>
            <Link to="/positions" className="px-4 py-2 rounded-md bg-white text-blue-700 hover:bg-blue-100 transition font-semibold shadow-sm border border-blue-200">Cargos</Link>
            <Link to="/project-status" className="px-4 py-2 rounded-md bg-white text-blue-700 hover:bg-blue-100 transition font-semibold shadow-sm border border-blue-200">Estado Proyecto</Link>
            <Link to="/experience-levels" className="px-4 py-2 rounded-md bg-white text-green-700 hover:bg-green-100 transition font-semibold shadow-sm border border-green-200">Niveles Experiencia</Link>
            <Link to="/time-factors" className="px-4 py-2 rounded-md bg-white text-green-700 hover:bg-green-100 transition font-semibold shadow-sm border border-green-200">Factores Tiempo</Link>
            <Link to="/utility-factors" className="px-4 py-2 rounded-md bg-white text-green-700 hover:bg-green-100 transition font-semibold shadow-sm border border-green-200">Factores Utilidad</Link>
          </nav>

          <div className="bg-white p-6 rounded-lg shadow-md">
            <Routes>
              <Route path="/task-types" element={<GenericCatalogManager title="Gestión de Tipos de Tarea" endpoint="http://localhost:8080/api/task-types" fieldsConfig={simpleCatalogFields} />} />
              <Route path="/priorities" element={<GenericCatalogManager title="Gestión de Prioridades" endpoint="http://localhost:8080/api/priorities" fieldsConfig={simpleCatalogFields} />} />
              <Route path="/client-types" element={<GenericCatalogManager title="Gestión de Tipos de Cliente" endpoint="http://localhost:8080/api/client-types" fieldsConfig={simpleCatalogFields} />} />
              <Route path="/positions" element={<GenericCatalogManager title="Gestión de Cargos" endpoint="http://localhost:8080/api/positions" fieldsConfig={simpleCatalogFields} />} />
              <Route path="/project-status" element={<GenericCatalogManager title="Gestión de Estados de Proyecto" endpoint="http://localhost:8080/api/project-status" fieldsConfig={simpleCatalogFields} />} />

              <Route path="/experience-levels" element={<GenericCatalogManager title="Gestión de Niveles de Experiencia" endpoint="http://localhost:8080/api/experience-levels" fieldsConfig={experienceLevelsFields} />} />
              <Route path="/time-factors" element={<GenericCatalogManager title="Gestión de Factores de Tiempo" endpoint="http://localhost:8080/api/time-factors" fieldsConfig={timeFactorsFields} />} />
              <Route path="/utility-factors" element={<GenericCatalogManager title="Gestión de Factores de Utilidad" endpoint="http://localhost:8080/api/utility-factors" fieldsConfig={utilityFactorsFields} />} />
              
              <Route path="/" element={<div className="text-center py-10"><h2 className="text-2xl font-light text-gray-600">Bienvenido. Seleccione un catálogo para comenzar.</h2></div>} />
            </Routes>
          </div>
        </main>
      </div>
    </Router>
  )
}

export default App
