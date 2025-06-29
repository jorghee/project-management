package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Position;
import com.cyacompany.projectmanagement_api.repository.PositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PositionService {

  private final PositionRepository repository;

  public PositionService(PositionRepository repository) {
    this.repository = repository;
  }

  public List<Position> getAll() {
    return repository.findAllByOrderByIdAsc();
  }

  public Position getById(Integer id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id));
  }

  @Transactional
  public Position save(Position position) {
    return repository.save(position);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Position not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
