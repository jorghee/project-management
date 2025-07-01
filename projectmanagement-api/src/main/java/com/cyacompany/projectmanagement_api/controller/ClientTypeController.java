package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.ClientTypeDto;
import com.cyacompany.projectmanagement_api.mapper.ClientTypeMapper;
import com.cyacompany.projectmanagement_api.model.ClientType;
import com.cyacompany.projectmanagement_api.service.ClientTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/client-types")
public class ClientTypeController {

  private final ClientTypeService service;
  private final ClientTypeMapper mapper;

  public ClientTypeController(ClientTypeService service, ClientTypeMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<ClientTypeDto>> getAll() {
    List<ClientTypeDto> dtoList = service.getAll().stream()
      .map(mapper::toDto)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientTypeDto> getById(@PathVariable Integer id) {
    ClientType clientType = service.getById(id);
    return ResponseEntity.ok(mapper.toDto(clientType));
  }

  @PostMapping
  public ResponseEntity<ClientTypeDto> create(@Valid @RequestBody ClientTypeDto dto) {
    ClientType newClientType = service.create(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(newClientType), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClientTypeDto> update(@PathVariable Integer id, @Valid @RequestBody ClientTypeDto dto) {
    ClientType updatedClientType = service.update(id, mapper.toEntity(dto));
    return ResponseEntity.ok(mapper.toDto(updatedClientType));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
