package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.TimeFactor;
import com.cyacompany.projectmanagement_api.repository.TimeFactorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
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
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TimeFactor not found with id: " + id));
  }

  @Transactional
  public TimeFactor save(TimeFactor timeFactor) {
    return repository.save(timeFactor);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("TimeFactor not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
