package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
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
  public ExperienceLevel create(ExperienceLevel experienceLevel) {
    if (repository.existsById(experienceLevel.getId())) {
      throw new BusinessLogicException("Cannot create ExperienceLevel. ID " + experienceLevel.getId() + " already exists.");
    }
    return repository.save(experienceLevel);
  }

  @Transactional
  public ExperienceLevel update(Integer id, ExperienceLevel experienceLevelDetails) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("ExperienceLevel not found with id: " + id);
    }
    experienceLevelDetails.setId(id);
    return repository.save(experienceLevelDetails);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("ExperienceLevel not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
