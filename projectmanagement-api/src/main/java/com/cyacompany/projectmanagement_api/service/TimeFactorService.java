package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.model.TimeFactor;
import com.cyacompany.projectmanagement_api.repository.TimeFactorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeFactorService {

  private final TimeFactorRepository repository;

  public TimeFactorService(TimeFactorRepository repository) {
    this.repository = repository;
  }

  public List<TimeFactor> getAll() {
    return repository.findAllByOrderByIdAsc();
  }

  public TimeFactor getById(Integer id) {
    return repository.findById(id).orElse(null);
  }

  public TimeFactor save(TimeFactor timeFactor) {
    return repository.save(timeFactor);
  }

  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
