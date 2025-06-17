package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.model.Priority;
import com.cyacompany.projectmanagement_api.repository.PriorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityService {

  private final PriorityRepository repository;

  public PriorityService(PriorityRepository repository) {
    this.repository = repository;
  }

  public List<Priority> getAll() {
    return repository.findAll();
  }

  public Priority getById(Integer id) {
    return repository.findById(id).orElse(null);
  }

  public Priority save(Priority priority) {
    return repository.save(priority);
  }

  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
