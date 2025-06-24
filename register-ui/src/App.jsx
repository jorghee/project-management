import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import CatalogManager from './components/CatalogManager'

function App() {
  return (
    <Router>
      <div className="p-6 space-y-6">
        <h1 className="text-3xl font-bold">Tablas referenciales</h1>

        <nav className="flex gap-4 mt-4">
          <Link
            to="/task-types"
            className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm"
          >
            Tipo Tarea
          </Link>
          <Link
            to="/priorities"
            className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm"
          >
            Prioridad Tarea
          </Link>
          <Link
            to="/client-types"
            className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm"
          >
            Tipo de Clientes
          </Link>
          <Link
            to="/positions"
            className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm"
          >
            Gestión de Cargos
          </Link>
          <Link
            to="/project-status"
            className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm"
          >
            Gestión Estados de Proyectos
          </Link>
          <Link
            to="/experience-levels"
            className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm"
          >
            Gestión de Niveles de Experiencia
          </Link>
          <Link
            to="/time-factors"
            className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm"
          >
            Gestión de Factores de Tiempo
          </Link>
          <Link
            to="/utilities-factors"
            className="px-3 py-1 rounded bg-blue-100 text-blue-700 hover:bg-blue-200 transition font-medium shadow-sm"
          >
            Gestión de Factores de Utilidad
          </Link>
          
        
        </nav>

        <Routes>
          <Route path="/task-types" element={
            <CatalogManager
              title="Tipo Tarea"
              endpoint="http://localhost:8080/api/task-types"
              idKey="id"
              descriptionKey="description"
            />
          } />
          <Route path="/priorities" element={
            <CatalogManager
              title="Prioridad Tarea"
              endpoint="http://localhost:8080/api/priorities"
              idKey="id"
              descriptionKey="description"
            />
          } />
          <Route path="/client-types" element={
            <CatalogManager
              title="Tipos de Cliente"
              endpoint="http://localhost:8080/api/client-types"
              idKey="id"
              descriptionKey="description"
            />
          } />
          <Route path="/positions" element={
            <CatalogManager
              title="Tipos de Cliente"
              endpoint="http://localhost:8080/api/positions"
              idKey="id"
              descriptionKey="description"
            />
          } />
          <Route path="/project-status" element={
            <CatalogManager
              title="Tipos de Cliente"
              endpoint="http://localhost:8080/api/project-status"
              idKey="id"
              descriptionKey="description"
            />
          } />
          <Route path="/experience-levels" element={
            <CatalogManager
              title="Tipos de Cliente"
              endpoint="http://localhost:8080/api/experience-levels"
              idKey="id"
              descriptionKey="description"
            />
          } />
          <Route path="/time-factors" element={
            <CatalogManager
              title="Tipos de Cliente"
              endpoint="http://localhost:8080/api/time-factors"
              idKey="id"
              descriptionKey="description"
            />
          } />
          <Route path="/utility-factors" element={
            <CatalogManager
              title="Tipos de Cliente"
              endpoint="http://localhost:8080/api/utility-factors"
              idKey="id"
              descriptionKey="description"
            />
          } />
          
          
          <Route path="/" element={<p>Compañia A & C</p>} />
        </Routes>
      </div>
    </Router>
  )
}

export default App
