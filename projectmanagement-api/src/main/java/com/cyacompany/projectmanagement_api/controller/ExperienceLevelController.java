package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.model.ExperienceLevel;
import com.cyacompany.projectmanagement_api.service.ExperienceLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience-levels")
public class ExperienceLevelController {

  private final ExperienceLevelService service;

  public ExperienceLevelController(ExperienceLevelService service) {
    this.service = service;
  }

  @GetMapping
  public List<ExperienceLevel> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public ExperienceLevel getById(@PathVariable Integer id) {
    return service.getById(id);
  }

  @PostMapping
  public ExperienceLevel create(@RequestBody ExperienceLevel experienceLevel) {
    return service.save(experienceLevel);
  }

  @PutMapping("/{id}")
  public ExperienceLevel update(@PathVariable Integer id, @RequestBody ExperienceLevel updated) {
    updated.setId(id);
    return service.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    service.deleteById(id);
  }
}
