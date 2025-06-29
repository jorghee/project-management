package com.cyacompany.projectmanagement_api.service;

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
  public ProjectStatus save(ProjectStatus status) {
    return repository.save(status);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("ProjectStatus not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
