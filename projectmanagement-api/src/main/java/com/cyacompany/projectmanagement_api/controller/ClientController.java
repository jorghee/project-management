package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.ClientRequest;
import com.cyacompany.projectmanagement_api.dto.ClientResponse;
import com.cyacompany.projectmanagement_api.mapper.ClientMapper;
import com.cyacompany.projectmanagement_api.model.Client;
import com.cyacompany.projectmanagement_api.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

  private final ClientService service;
  private final ClientMapper mapper;

  public ClientController(ClientService service, ClientMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<Page<ClientResponse>> getAll(Pageable pageable) {
    Page<Client> clientPage = service.getAll(pageable);
    Page<ClientResponse> dtoPage = clientPage.map(mapper::toResponse);
    return ResponseEntity.ok(dtoPage);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientResponse> getById(@PathVariable Integer id) {
    Client client = service.getById(id);
    return ResponseEntity.ok(mapper.toResponse(client));
  }

  @PostMapping
  public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientRequest requestDto) {
    Client clientToCreate = mapper.toEntity(requestDto);
    Client newClient = service.create(clientToCreate, requestDto.getClientTypeId());
    return new ResponseEntity<>(mapper.toResponse(newClient), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClientResponse> update(@PathVariable Integer id, @Valid @RequestBody ClientRequest requestDto) {
    Client clientDetails = mapper.toEntity(requestDto);
    Client updatedClient = service.update(id, clientDetails, requestDto.getClientTypeId());
    return ResponseEntity.ok(mapper.toResponse(updatedClient));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
