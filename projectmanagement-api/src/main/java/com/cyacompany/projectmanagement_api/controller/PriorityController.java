package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.PriorityDto;
import com.cyacompany.projectmanagement_api.mapper.PriorityMapper;
import com.cyacompany.projectmanagement_api.model.Priority;
import com.cyacompany.projectmanagement_api.service.PriorityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/priorities")
public class PriorityController {

  private final PriorityService service;
  private final PriorityMapper mapper;

  public PriorityController(PriorityService service, PriorityMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<PriorityDto>> getAll() {
    List<PriorityDto> dtoList = service.getAll().stream()
      .map(mapper::toDto)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PriorityDto> getById(@PathVariable Integer id) {
    Priority priority = service.getById(id);
    return ResponseEntity.ok(mapper.toDto(priority));
  }

  @PostMapping
  public ResponseEntity<PriorityDto> create(@Valid @RequestBody PriorityDto dto) {
    Priority newPriority = service.create(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(newPriority), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PriorityDto> update(@PathVariable Integer id, @Valid @RequestBody PriorityDto dto) {
    Priority updatedPriority = service.update(id, mapper.toEntity(dto));
    return ResponseEntity.ok(mapper.toDto(updatedPriority));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
