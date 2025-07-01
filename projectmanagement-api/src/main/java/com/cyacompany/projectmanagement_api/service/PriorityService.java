package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
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
  public Priority create(Priority priority) {
    if (repository.existsById(priority.getId())) {
      throw new BusinessLogicException("Cannot create Priority. ID " + priority.getId() + " already exists.");
    }
    return repository.save(priority);
  }

  @Transactional
  public Priority update(Integer id, Priority priorityDetails) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Priority not found with id: " + id);
    }
    priorityDetails.setId(id);
    return repository.save(priorityDetails);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Priority not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
