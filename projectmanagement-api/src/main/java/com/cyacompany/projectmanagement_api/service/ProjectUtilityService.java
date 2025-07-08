package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Project;
import com.cyacompany.projectmanagement_api.model.ProjectUtility;
import com.cyacompany.projectmanagement_api.model.TimeFactor;
import com.cyacompany.projectmanagement_api.repository.ProjectRepository;
import com.cyacompany.projectmanagement_api.repository.ProjectUtilityRepository;
import com.cyacompany.projectmanagement_api.repository.TimeFactorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectUtilityService {

  private final ProjectUtilityRepository utilityRepository;
  private final ProjectRepository projectRepository;
  private final TimeFactorRepository timeFactorRepository;

  public ProjectUtilityService(ProjectUtilityRepository utilityRepository,
      ProjectRepository projectRepository, TimeFactorRepository timeFactorRepository) {
    this.utilityRepository = utilityRepository;
    this.projectRepository = projectRepository;
    this.timeFactorRepository = timeFactorRepository;
  }

  /**
   * Obtiene todos los registros de utilidad de proyecto.
   * @return Una lista de todas las utilidades.
   */
  public List<ProjectUtility> getAll() {
    return utilityRepository.findAll();
  }

  public ProjectUtility getByProjectId(Integer projectId) {
    return utilityRepository.findById(projectId)
      .orElseThrow(() -> new ResourceNotFoundException("ProjectUtility not found for project id: " + projectId));
  }

  @Transactional
  public ProjectUtility createOrUpdate(Integer projectId, ProjectUtility utilityDetails, Integer timeFactorId) {
    Project project = projectRepository.findById(projectId)
      .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
    TimeFactor timeFactor = timeFactorRepository.findById(timeFactorId)
      .orElseThrow(() -> new ResourceNotFoundException("TimeFactor not found with id: " + timeFactorId));
    
    return utilityRepository.findById(projectId)
      .map(existingUtility -> {
        existingUtility.setTimeFactor(timeFactor);
        existingUtility.setExperienceFactor(utilityDetails.getExperienceFactor());
        existingUtility.setBasePercentage(utilityDetails.getBasePercentage());
        existingUtility.setFinalPercentage(utilityDetails.getFinalPercentage());
        existingUtility.setStatus(utilityDetails.getStatus());

        return utilityRepository.save(existingUtility);
      })
      .orElseGet(() -> {
        utilityDetails.setProject(project);
        utilityDetails.setProjectId(projectId);
        utilityDetails.setTimeFactor(timeFactor);

        return utilityRepository.save(utilityDetails);
      });
  }

  @Transactional
  public void delete(Integer projectId) {
    if (!utilityRepository.existsById(projectId)) {
      throw new ResourceNotFoundException("ProjectUtility not found for project id: " + projectId);
    }
    utilityRepository.deleteById(projectId);
  }
}
