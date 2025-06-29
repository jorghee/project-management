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

@Service
public class ProjectUtilityService {

  private final ProjectUtilityRepository utilityRepository;
  private final ProjectRepository projectRepository;
  private final TimeFactorRepository timeFactorRepository;

  public ProjectUtilityService(ProjectUtilityRepository utilityRepository, ProjectRepository projectRepository, TimeFactorRepository timeFactorRepository) {
    this.utilityRepository = utilityRepository;
    this.projectRepository = projectRepository;
    this.timeFactorRepository = timeFactorRepository;
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
    
    ProjectUtility utility = utilityRepository.findById(projectId)
      .orElse(new ProjectUtility());
      
    utility.setProjectId(project.getId());
    utility.setProject(project);
    utility.setTimeFactor(timeFactor);
    utility.setExperienceFactor(utilityDetails.getExperienceFactor());
    utility.setBasePercentage(utilityDetails.getBasePercentage());
    utility.setFinalPercentage(utilityDetails.getFinalPercentage());
    utility.setStatus(utilityDetails.getStatus());

    return utilityRepository.save(utility);
  }

  @Transactional
  public void delete(Integer projectId) {
    if (!utilityRepository.existsById(projectId)) {
      throw new ResourceNotFoundException("ProjectUtility not found for project id: " + projectId);
    }
    utilityRepository.deleteById(projectId);
  }
}
