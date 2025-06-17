package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.model.Priority;
import com.cyacompany.projectmanagement_api.service.PriorityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/priorities")
public class PriorityController {

  private final PriorityService service;

  public PriorityController(PriorityService service) {
    this.service = service;
  }

  @GetMapping
  public List<Priority> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Priority getById(@PathVariable Integer id) {
    return service.getById(id);
  }

  @PostMapping
  public Priority create(@RequestBody Priority priority) {
    return service.save(priority);
  }

  @PutMapping("/{id}")
  public Priority update(@PathVariable Integer id, @RequestBody Priority updated) {
    updated.setId(id);
    return service.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    service.deleteById(id);
  }
}
