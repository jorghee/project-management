package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.ProjectStatusDto;
import com.cyacompany.projectmanagement_api.mapper.ProjectStatusMapper;
import com.cyacompany.projectmanagement_api.model.ProjectStatus;
import com.cyacompany.projectmanagement_api.service.ProjectStatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project-status")
public class ProjectStatusController {

  private final ProjectStatusService service;
  private final ProjectStatusMapper mapper;

  public ProjectStatusController(ProjectStatusService service, ProjectStatusMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<ProjectStatusDto>> getAll() {
    List<ProjectStatusDto> dtoList = service.getAll().stream()
      .map(mapper::toDto)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProjectStatusDto> getById(@PathVariable Integer id) {
    ProjectStatus projectStatus = service.getById(id);
    return ResponseEntity.ok(mapper.toDto(projectStatus));
  }

  @PostMapping
  public ResponseEntity<ProjectStatusDto> create(@Valid @RequestBody ProjectStatusDto dto) {
    ProjectStatus newProjectStatus = service.save(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(newProjectStatus), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProjectStatusDto> update(@PathVariable Integer id, @Valid @RequestBody ProjectStatusDto dto) {
    dto.setId(id);
    ProjectStatus updatedProjectStatus = service.save(mapper.toEntity(dto));
    return ResponseEntity.ok(mapper.toDto(updatedProjectStatus));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
