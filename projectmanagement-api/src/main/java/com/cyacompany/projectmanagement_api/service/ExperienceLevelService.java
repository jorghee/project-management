package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.model.ExperienceLevel;
import com.cyacompany.projectmanagement_api.repository.ExperienceLevelRepository;
import org.springframework.stereotype.Service;

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
    return repository.findById(id).orElse(null);
  }

  public ExperienceLevel save(ExperienceLevel experienceLevel) {
    return repository.save(experienceLevel);
  }

  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
