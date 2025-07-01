package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Client;
import com.cyacompany.projectmanagement_api.model.Project;
import com.cyacompany.projectmanagement_api.model.ProjectStatus;
import com.cyacompany.projectmanagement_api.repository.ClientRepository;
import com.cyacompany.projectmanagement_api.repository.ProjectRepository;
import com.cyacompany.projectmanagement_api.repository.ProjectStatusRepository;
import com.cyacompany.projectmanagement_api.repository.StageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

  private final ProjectRepository projectRepository;
  private final ClientRepository clientRepository;
  private final ProjectStatusRepository projectStatusRepository;
  private final StageRepository stageRepository; 

  public ProjectService(ProjectRepository projectRepository, ClientRepository clientRepository, 
      ProjectStatusRepository projectStatusRepository, StageRepository stageRepository) {
    this.projectRepository = projectRepository;
    this.clientRepository = clientRepository;
    this.projectStatusRepository = projectStatusRepository;
    this.stageRepository = stageRepository;
  }

  /**
   * Obtiene una lista paginada de proyectos.
   * @param pageable Configuración de paginación y ordenamiento.
   * @return Una página de proyectos.
   */
  public Page<Project> getAll(Pageable pageable) {
    return projectRepository.findAll(pageable);
  }

  public Project getById(Integer id) {
    return projectRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
  }

  @Transactional
  public Project create(Project project, Integer clientId, Integer statusId) {
    if (projectRepository.existsById(project.getId())) {
      throw new BusinessLogicException("Cannot create Project. ID " + project.getId() + " already exists.");
    }

    Client client = clientRepository.findById(clientId)
      .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
    ProjectStatus status = projectStatusRepository.findById(statusId)
      .orElseThrow(() -> new ResourceNotFoundException("ProjectStatus not found with id: " + statusId));
      
    project.setClient(client);
    project.setProjectStatus(status);
    return projectRepository.save(project);
  }

  @Transactional
  public Project update(Integer id, Project projectDetails, Integer clientId, Integer statusId) {
    if (!projectRepository.existsById(id)) {
      throw new ResourceNotFoundException("Project not found with id: " + id);
    }

    Client client = clientRepository.findById(clientId)
      .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
    ProjectStatus status = projectStatusRepository.findById(statusId)
      .orElseThrow(() -> new ResourceNotFoundException("ProjectStatus not found with id: " + statusId));
    
    projectDetails.setId(id);
    projectDetails.setClient(client);
    projectDetails.setProjectStatus(status);

    return projectRepository.save(projectDetails);
  }

  /**
   * Elimina un proyecto solo si no tiene etapas asociadas.
   * @param id El ID del proyecto a eliminar.
   */
  @Transactional
  public void delete(Integer id) {
    if (!projectRepository.existsById(id)) {
      throw new ResourceNotFoundException("Project not found with id: " + id);
    }

    if (stageRepository.existsByProjectId(id)) {
      throw new BusinessLogicException("Cannot delete project with id: " + id + " because it has associated stages.");
    }

    projectRepository.deleteById(id);
  }
}
