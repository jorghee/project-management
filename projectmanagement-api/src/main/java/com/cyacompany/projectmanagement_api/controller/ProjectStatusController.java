package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.model.ProjectStatus;
import com.cyacompany.projectmanagement_api.service.ProjectStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-status")
public class ProjectStatusController {

  private final ProjectStatusService service;

  public ProjectStatusController(ProjectStatusService service) {
    this.service = service;
  }

  @GetMapping
  public List<ProjectStatus> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public ProjectStatus getById(@PathVariable Integer id) {
    return service.getById(id);
  }

  @PostMapping
  public ProjectStatus create(@RequestBody ProjectStatus status) {
    return service.save(status);
  }

  @PutMapping("/{id}")
  public ProjectStatus update(@PathVariable Integer id, @RequestBody ProjectStatus updated) {
    updated.setId(id);
    return service.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    service.deleteById(id);
  }
}
