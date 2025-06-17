package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.model.Position;
import com.cyacompany.projectmanagement_api.service.PositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

  private final PositionService service;

  public PositionController(PositionService service) {
    this.service = service;
  }

  @GetMapping
  public List<Position> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Position getById(@PathVariable Integer id) {
    return service.getById(id);
  }

  @PostMapping
  public Position create(@RequestBody Position position) {
    return service.save(position);
  }

  @PutMapping("/{id}")
  public Position update(@PathVariable Integer id, @RequestBody Position updated) {
    updated.setId(id);
    return service.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    service.deleteById(id);
  }
}
