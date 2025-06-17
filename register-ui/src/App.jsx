import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import CatalogManager from './components/CatalogManager'

function App() {
  return (
    <Router>
      <div className="p-6 space-y-6">
        <h1 className="text-3xl font-bold">Catálogo Manager</h1>

        <nav className="space-x-4">
          <Link className="text-blue-600 hover:underline" to="/task-types">Task Types</Link>
          <Link className="text-blue-600 hover:underline" to="/priorities">Priorities</Link>
        </nav>

        <Routes>
          <Route path="/task-types" element={
            <CatalogManager
              title="Task Types"
              endpoint="http://localhost:8080/api/task-types"
              idKey="id"
              descriptionKey="description"
            />
          } />
          <Route path="/priorities" element={
            <CatalogManager
              title="Priorities"
              endpoint="http://localhost:8080/api/priorities"
              idKey="id"
              descriptionKey="description"
            />
          } />
          <Route path="/" element={<p>Bienvenido, selecciona un catálogo para mantener.</p>} />
        </Routes>
      </div>
    </Router>
  )
}

export default App
