package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.ClientType;
import com.cyacompany.projectmanagement_api.repository.ClientTypeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientTypeService {

  private final ClientTypeRepository repository;

  public ClientTypeService(ClientTypeRepository repository) {
    this.repository = repository;
  }

  public List<ClientType> getAll() {
    return repository.findAllByOrderByIdAsc();
  }

  public ClientType getById(Integer id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ClientType not found with id: " + id));
  }

  @Transactional
  public ClientType save(ClientType clientType) {
    return repository.save(clientType);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("ClientType not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
