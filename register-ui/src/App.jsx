import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import HomePage from './pages/HomePage';

import SimpleCatalogManager from './pages/simple-catalogs/SimpleCatalogManager';

import PositionPage from './pages/position/PositionPage';
import ExperienceLevelPage from './pages/experience-level/ExperienceLevelPage';
import TimeFactorPage from './pages/time-factor/TimeFactorPage';
import UtilityFactorPage from './pages/utility-factor/UtilityFactorPage';

import ClientPage from './pages/client/ClientPage';
import ProjectPage from './pages/project/ProjectPage';
import StagePage from './pages/stage/StagePage';
import ActivityPage from './pages/activity/ActivityPage';
import EmployeePage from './pages/employee/EmployeePage';
import TaskPage from './pages/task/TaskPage';
import TaskAssignmentPage from './pages/task-assignment/TaskAssignmentPage';
import TaskExecutionPage from './pages/task-execution/TaskExecutionPage';
import ProjectUtilityPage from './pages/project-utility/ProjectUtilityPage';
import AvailabilityPage from './pages/availability/AvailabilityPage';

import ComplexityPage from './pages/complexity/ComplexityPage';

function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />

         {/* G1: GESTIÓN DE PROYECTOS */}
        <Route path="/clients" element={<ClientPage />} />
        <Route path="/projects" element={<ProjectPage />} />
        <Route path="/stages" element={<StagePage />} />
        <Route path="/activities" element={<ActivityPage />} />
        
        {/* G2: RECURSOS HUMANOS */}
        <Route path="/employees" element={<EmployeePage />} />
        <Route path="/positions" element={<PositionPage />} />
        <Route path="/experience-levels" element={<ExperienceLevelPage />} />
        <Route path="/availabilities" element={<AvailabilityPage />} />

        {/* G3: GESTIÓN OPERATIVA */}
        <Route path="/tasks" element={<TaskPage />} />
        <Route path="/task-assignments" element={<TaskAssignmentPage />} />
        <Route path="/task-executions" element={<TaskExecutionPage />} />
        <Route path="/project-utilities" element={<ProjectUtilityPage />} />
        
        {/* GZ/G4: CONFIGURACIÓN Y CATÁLOGOS */}
        <Route path="/simple-catalogs/client-types" element={<SimpleCatalogManager title="Gestión de Tipos de Cliente" apiEndpoint="/client-types" />} />
        <Route path="/simple-catalogs/project-status" element={<SimpleCatalogManager title="Gestión de Estados de Proyecto" apiEndpoint="/project-status" />} />
        <Route path="/simple-catalogs/task-types" element={<SimpleCatalogManager title="Gestión de Tipos de Tarea" apiEndpoint="/task-types" />} />
        <Route path="/simple-catalogs/priorities" element={<SimpleCatalogManager title="Gestión de Prioridades" apiEndpoint="/priorities" />} />
        <Route path="/time-factors" element={<TimeFactorPage />} />
        <Route path="/utility-factors" element={<UtilityFactorPage />} />
        <Route path="/complexities" element={<ComplexityPage />} />       

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
