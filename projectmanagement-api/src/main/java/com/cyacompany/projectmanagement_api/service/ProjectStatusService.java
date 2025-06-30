package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.ProjectStatus;
import com.cyacompany.projectmanagement_api.repository.ProjectStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectStatusService {

  private final ProjectStatusRepository repository;

  public ProjectStatusService(ProjectStatusRepository repository) {
    this.repository = repository;
  }

  public List<ProjectStatus> getAll() {
    return repository.findAllByOrderByIdAsc();
  }

  public ProjectStatus getById(Integer id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProjectStatus not found with id: " + id));
  }

  @Transactional
  public ProjectStatus create(ProjectStatus projectStatus) {
    if (repository.existsById(projectStatus.getId())) {
      throw new BusinessLogicException("Cannot create ProjectStatus. ID " + projectStatus.getId() + " already exists.");
    }
    return repository.save(projectStatus);
  }

  @Transactional
  public ProjectStatus update(Integer id, ProjectStatus projectStatusDetails) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("ProjectStatus not found with id: " + id);
    }
    projectStatusDetails.setId(id);
    return repository.save(projectStatusDetails);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("ProjectStatus not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
