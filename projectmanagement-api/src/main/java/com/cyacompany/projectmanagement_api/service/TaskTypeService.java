package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.TaskType;
import com.cyacompany.projectmanagement_api.repository.TaskTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TaskType not found with id: " + id));
  }

  @Transactional
  public TaskType save(TaskType taskType) {
    return repository.save(taskType);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("TaskType not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
