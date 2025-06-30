package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.ProjectRequest;
import com.cyacompany.projectmanagement_api.dto.ProjectResponse;
import com.cyacompany.projectmanagement_api.mapper.ProjectMapper;
import com.cyacompany.projectmanagement_api.model.Project;
import com.cyacompany.projectmanagement_api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

  private final ProjectService service;
  private final ProjectMapper mapper;

  public ProjectController(ProjectService service, ProjectMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<Page<ProjectResponse>> getAll(Pageable pageable) {
    Page<Project> projectPage = service.getAll(pageable);
    Page<ProjectResponse> dtoPage = projectPage.map(mapper::toResponse);
    return ResponseEntity.ok(dtoPage);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProjectResponse> getById(@PathVariable Integer id) {
    Project project = service.getById(id);
    return ResponseEntity.ok(mapper.toResponse(project));
  }

  @PostMapping
  public ResponseEntity<ProjectResponse> create(@Valid @RequestBody ProjectRequest requestDto) {
    Project projectToCreate = mapper.toEntity(requestDto);
    Project newProject = service.create(projectToCreate, requestDto.getClientId(), requestDto.getProjectStatusId());
    return new ResponseEntity<>(mapper.toResponse(newProject), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProjectResponse> update(@PathVariable Integer id, @Valid @RequestBody ProjectRequest requestDto) {
    Project projectDetails = mapper.toEntity(requestDto);
    Project updatedProject = service.update(id, projectDetails, requestDto.getClientId(), requestDto.getProjectStatusId());
    return ResponseEntity.ok(mapper.toResponse(updatedProject));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
