package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.model.Position;
import com.cyacompany.projectmanagement_api.repository.PositionRepository;
import org.springframework.stereotype.Service;

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
    return repository.findById(id).orElse(null);
  }

  public Position save(Position position) {
    return repository.save(position);
  }

  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
