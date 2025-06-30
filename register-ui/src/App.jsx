import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
// Importaremos las páginas de gestión a medida que las creemos
// import ProjectPage from './pages/project/ProjectPage'; 
// import SimpleCatalogManager from './pages/simple-catalogs/SimpleCatalogManager';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        
        {/* Aquí irán las rutas para cada página de gestión */}
        {/*
        <Route path="/projects" element={<ProjectPage />} />
        <Route 
          path="/simple-catalogs/client-types" 
          element={<SimpleCatalogManager title="Tipos de Cliente" apiEndpoint="/api/client-types" />} 
        />
        */}
        
        {/* Placeholder para las demás rutas */}
        <Route path="*" element={
          <div className="h-screen flex flex-col items-center justify-center bg-gray-100">
            <h1 className="text-3xl font-bold mb-4">Página en Construcción</h1>
            <a href="/" className="text-blue-600 hover:underline">Volver al Menú Principal</a>
          </div>
        } />
      </Routes>
    </Router>
  );
}

export default App;
