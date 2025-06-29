package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Priority;
import com.cyacompany.projectmanagement_api.repository.PriorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PriorityService {

  private final PriorityRepository repository;

  public PriorityService(PriorityRepository repository) {
    this.repository = repository;
  }

  public List<Priority> getAll() {
    return repository.findAllByOrderByIdAsc();
  }

  public Priority getById(Integer id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Priority not found with id: " + id));
  }

  @Transactional
  public Priority save(Priority priority) {
    return repository.save(priority);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Priority not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
