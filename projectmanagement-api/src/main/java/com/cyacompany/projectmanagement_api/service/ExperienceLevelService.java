package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.ExperienceLevel;
import com.cyacompany.projectmanagement_api.repository.ExperienceLevelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExperienceLevelService {

  private final ExperienceLevelRepository repository;

  public ExperienceLevelService(ExperienceLevelRepository repository) {
    this.repository = repository;
  }

  public List<ExperienceLevel> getAll() {
    return repository.findAllByOrderByIdAsc();
  }

  public ExperienceLevel getById(Integer id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ExperienceLevel not found with id: " + id));
  }

  @Transactional
  public ExperienceLevel save(ExperienceLevel experienceLevel) {
    return repository.save(experienceLevel);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("ExperienceLevel not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
