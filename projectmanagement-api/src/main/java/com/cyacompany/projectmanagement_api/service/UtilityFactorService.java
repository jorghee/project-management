package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.model.UtilityFactor;
import com.cyacompany.projectmanagement_api.repository.UtilityFactorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilityFactorService {

  private final UtilityFactorRepository repository;

  public UtilityFactorService(UtilityFactorRepository repository) {
    this.repository = repository;
  }

  public List<UtilityFactor> getAll() {
    return repository.findAll();
  }

  public UtilityFactor getById(Integer id) {
    return repository.findById(id).orElse(null);
  }

  public UtilityFactor save(UtilityFactor utilityFactor) {
    return repository.save(utilityFactor);
  }

  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
