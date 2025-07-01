package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Project;
import com.cyacompany.projectmanagement_api.model.Stage;
import com.cyacompany.projectmanagement_api.repository.ActivityRepository;
import com.cyacompany.projectmanagement_api.repository.ProjectRepository;
import com.cyacompany.projectmanagement_api.repository.StageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class StageService {

  private final StageRepository stageRepository;
  private final ProjectRepository projectRepository;
  private final ActivityRepository activityRepository;

  public StageService(StageRepository stageRepository, ProjectRepository projectRepository, ActivityRepository activityRepository) {
    this.stageRepository = stageRepository;
    this.projectRepository = projectRepository;
    this.activityRepository = activityRepository;
  }

  /**
   * Obtiene todas las etapas sin paginación, ordenadas por ID.
   * Este método es ideal para poblar la grilla principal en el frontend.
   * @return Una lista de todas las entidades Stage.
   */
  public List<Stage> getAllUnpaged() {
    return stageRepository.findAllByOrderByIdAsc();
  }

  public List<Stage> getAll() {
    // No es una operación común, pero se mantiene por completitud del CRUD.
    return stageRepository.findAll();
  }

  public Stage getById(Integer id) {
    return stageRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Stage not found with id: " + id));
  }
  
  /**
   * Obtiene todas las etapas de un proyecto específico usando el método optimizado del repositorio.
   * @param projectId El ID del proyecto.
   * @return Lista de etapas.
   */
  public List<Stage> getByProjectId(Integer projectId) {
    if (!projectRepository.existsById(projectId)) {
        throw new ResourceNotFoundException("Project not found with id: " + projectId);
    }
    // Implementación usando el método optimizado, no el stream.
    return stageRepository.findByProjectIdOrderByIdAsc(projectId);
  }

  @Transactional
  public Stage create(Stage stage, Integer projectId) {
    Project project = projectRepository.findById(projectId)
      .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
    
    stage.setProject(project);
    return stageRepository.save(stage);
  }

  @Transactional
  public Stage update(Integer id, Stage stageDetails, Integer projectId) {
    Stage existingStage = getById(id);
    Project project = projectRepository.findById(projectId)
      .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

    existingStage.setName(stageDetails.getName());
    existingStage.setEstimatedTime(stageDetails.getEstimatedTime());
    existingStage.setStatus(stageDetails.getStatus());
    existingStage.setProject(project);
    
    return stageRepository.save(existingStage);
  }


  /**
   * Elimina una etapa solo si no tiene actividades asociadas.
   * @param id El ID de la etapa a eliminar.
   */
  @Transactional
  public void delete(Integer id) {
    if (!stageRepository.existsById(id)) {
      throw new ResourceNotFoundException("Stage not found with id: " + id);
    }
    
    if (activityRepository.existsByStageId(id)) {
      throw new BusinessLogicException("Cannot delete stage with id: " + id + " because it has associated activities.");
    }

    stageRepository.deleteById(id);
  }
}
