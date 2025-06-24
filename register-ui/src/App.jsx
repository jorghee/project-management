import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import CatalogManager from './components/CatalogManager'

function App() {
  return (
    <Router>
      <div className="p-6 space-y-6">
        <h1 className="text-3xl font-bold">Tablas referenciales</h1>
        <h2 style={{ color: 'red', fontSize: '32px', fontWeight: 'bold' }}>
          ACTUALIZACIÓN CONFIRMADA
        </h2>

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
          
          <Route path="/" element={<p>Compañia A & C</p>} />
        </Routes>
      </div>
    </Router>
  )
}

export default App
