package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.model.TaskType;
import com.cyacompany.projectmanagement_api.repository.TaskTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTypeService {

  private final TaskTypeRepository repository;

  public TaskTypeService(TaskTypeRepository repository) {
    this.repository = repository;
  }

  public List<TaskType> getAll() {
    return repository.findAll();
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
