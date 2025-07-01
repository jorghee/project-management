package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
import com.cyacompany.projectmanagement_api.model.Client;
import com.cyacompany.projectmanagement_api.model.ClientType;
import com.cyacompany.projectmanagement_api.repository.ClientRepository;
import com.cyacompany.projectmanagement_api.repository.ClientTypeRepository;
import com.cyacompany.projectmanagement_api.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClientService {

  private final ClientRepository clientRepository;
  private final ClientTypeRepository clientTypeRepository;
  private final ProjectRepository projectRepository;

  public ClientService(ClientRepository clientRepository, ClientTypeRepository clientTypeRepository, ProjectRepository projectRepository) {
    this.clientRepository = clientRepository;
    this.clientTypeRepository = clientTypeRepository;
    this.projectRepository = projectRepository;
  }

  /**
   * Obtiene una lista paginada de clientes.
   * @param pageable Configuración de paginación y ordenamiento.
   * @return Una página de clientes.
   */
  public Page<Client> getAll(Pageable pageable) {
    return clientRepository.findAll(pageable);
  }

  public Client getById(Integer id) {
    return clientRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
  }

  @Transactional
  public Client create(Client client, Integer clientTypeId) {
    ClientType clientType = clientTypeRepository.findById(clientTypeId)
      .orElseThrow(() -> new ResourceNotFoundException("ClientType not found with id: " + clientTypeId));
    
    client.setClientType(clientType);
    return clientRepository.save(client);
  }

  @Transactional
  public Client update(Integer id, Client clientDetails, Integer clientTypeId) {
    Client existingClient = getById(id);
    ClientType clientType = clientTypeRepository.findById(clientTypeId)
      .orElseThrow(() -> new ResourceNotFoundException("ClientType not found with id: " + clientTypeId));

    clientDetails.setId(id);
    clientDetails.setClientType(clientType);
    
    return clientRepository.save(clientDetails);
  }

  /**
   * Elimina un cliente solo si no tiene proyectos asociados.
   * Lanza una excepción de lógica de negocio en caso contrario.
   * @param id El ID del cliente a eliminar.
   */
  @Transactional
  public void delete(Integer id) {
    if (!clientRepository.existsById(id)) {
      throw new ResourceNotFoundException("Client not found with id: " + id);
    }
    
    if (projectRepository.existsByClientId(id)) {
      throw new BusinessLogicException("Cannot delete client with id: " + id + " because it has associated projects.");
    }
    
    clientRepository.deleteById(id);
  }
}
