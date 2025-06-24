package com.cyacompany.projectmanagement_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyacompany.projectmanagement_api.model.ProjectStatus;
import com.cyacompany.projectmanagement_api.repository.ProjectStatusRepository;

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
    return repository.findById(id).orElse(null);
  }

  public ProjectStatus save(ProjectStatus status) {
    return repository.save(status);
  }

  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
