package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
import com.cyacompany.projectmanagement_api.model.Client;
import com.cyacompany.projectmanagement_api.model.ClientType;
import com.cyacompany.projectmanagement_api.repository.ClientRepository;
import com.cyacompany.projectmanagement_api.repository.ClientTypeRepository;
import com.cyacompany.projectmanagement_api.repository.ProjectRepository;
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

  public List<Client> getAll() {
    // Para una entidad maestra como Cliente, es mejor paginar o filtrar.
    // Devolvemos todos por ahora, pero en un caso real se necesitaría PagingAndSortingRepository.
    return clientRepository.findAll();
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

    existingClient.setName(clientDetails.getName());
    existingClient.setEntryDate(clientDetails.getEntryDate());
    existingClient.setTerminationDate(clientDetails.getTerminationDate());
    existingClient.setClientStatus(clientDetails.getClientStatus());
    existingClient.setStatus(clientDetails.getStatus());
    existingClient.setClientType(clientType);
    
    return clientRepository.save(existingClient);
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
    
    // Lógica de negocio implementada: No borrar si tiene proyectos.
    if (projectRepository.existsByClientId(id)) {
      throw new BusinessLogicException("Cannot delete client with id: " + id + " because it has associated projects.");
    }
    
    clientRepository.deleteById(id);
  }
}
