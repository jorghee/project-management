package com.cyacompany.projectmanagement_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyacompany.projectmanagement_api.model.TaskType;
import com.cyacompany.projectmanagement_api.repository.TaskTypeRepository;

@Service
public class TaskTypeService {

  private final TaskTypeRepository repository;

  public TaskTypeService(TaskTypeRepository repository) {
    this.repository = repository;
  }

  public List<TaskType> getAll() {
    return repository.findAllByOrderByIdAsc();
  }

  public TaskType getById(Integer id) {
    return repository.findById(id).orElse(null);
  }

  public TaskType save(TaskType taskType) {
    return repository.save(taskType);
  }

  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}