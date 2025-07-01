package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.ProjectUtilityRequest;
import com.cyacompany.projectmanagement_api.dto.ProjectUtilityResponse;
import com.cyacompany.projectmanagement_api.mapper.ProjectUtilityMapper;
import com.cyacompany.projectmanagement_api.model.ProjectUtility;
import com.cyacompany.projectmanagement_api.service.ProjectUtilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project-utilities")
public class ProjectUtilityController {

  private final ProjectUtilityService service;
  private final ProjectUtilityMapper mapper;

  public ProjectUtilityController(ProjectUtilityService service, ProjectUtilityMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }
  
  @GetMapping
  public ResponseEntity<List<ProjectUtilityResponse>> getAll() {
    List<ProjectUtilityResponse> dtoList = service.getAll().stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<ProjectUtilityResponse> getByProjectId(@PathVariable Integer projectId) {
    ProjectUtility utility = service.getByProjectId(projectId);
    return ResponseEntity.ok(mapper.toResponse(utility));
  }

  @PutMapping("/{projectId}")
  public ResponseEntity<ProjectUtilityResponse> createOrUpdate(
      @PathVariable Integer projectId, 
      @Valid @RequestBody ProjectUtilityRequest requestDto) {
    ProjectUtility utilityDetails = mapper.toEntity(requestDto);
    ProjectUtility savedUtility = service.createOrUpdate(projectId, utilityDetails, requestDto.getTimeFactorId());
    return ResponseEntity.ok(mapper.toResponse(savedUtility));
  }
  
  @DeleteMapping("/{projectId}")
  public ResponseEntity<Void> delete(@PathVariable Integer projectId) {
    service.delete(projectId);
    return ResponseEntity.noContent().build();
  }
}
