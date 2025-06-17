package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.model.ClientType;
import com.cyacompany.projectmanagement_api.repository.ClientTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientTypeService {

  private final ClientTypeRepository repository;

  public ClientTypeService(ClientTypeRepository repository) {
    this.repository = repository;
  }

  public List<ClientType> getAll() {
    return repository.findAll();
  }

  public ClientType getById(Integer id) {
    return repository.findById(id).orElse(null);
  }

  public ClientType save(ClientType clientType) {
    return repository.save(clientType);
  }

  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
