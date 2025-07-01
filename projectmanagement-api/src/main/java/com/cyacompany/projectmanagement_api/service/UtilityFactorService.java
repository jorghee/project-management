package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.UtilityFactor;
import com.cyacompany.projectmanagement_api.repository.UtilityFactorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class UtilityFactorService {

  private final UtilityFactorRepository repository;

  public UtilityFactorService(UtilityFactorRepository repository) {
    this.repository = repository;
  }

  public List<UtilityFactor> getAll() {
    return repository.findAllByOrderByIdAsc();
  }

  public UtilityFactor getById(Integer id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UtilityFactor not found with id: " + id));
  }

  @Transactional
  public UtilityFactor create(UtilityFactor utilityFactor) {
    if (repository.existsById(utilityFactor.getId())) {
      throw new BusinessLogicException("Cannot create UtilityFactor. ID " + utilityFactor.getId() + " already exists.");
    }
    return repository.save(utilityFactor);
  }
  
  @Transactional
  public UtilityFactor update(Integer id, UtilityFactor utilityFactorDetails) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("UtilityFactor not found with id: " + id);
    }
    utilityFactorDetails.setId(id);
    return repository.save(utilityFactorDetails);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("UtilityFactor not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
