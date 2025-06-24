package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.model.UtilityFactor;
import com.cyacompany.projectmanagement_api.service.UtilityFactorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utility-factors")
public class UtilityFactorController {

  private final UtilityFactorService service;

  public UtilityFactorController(UtilityFactorService service) {
    this.service = service;
  }

  @GetMapping
  public List<UtilityFactor> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public UtilityFactor getById(@PathVariable Integer id) {
    return service.getById(id);
  }

  @PostMapping
  public UtilityFactor create(@RequestBody UtilityFactor utilityFactor) {
    return service.save(utilityFactor);
  }

  @PutMapping("/{id}")
  public UtilityFactor update(@PathVariable Integer id, @RequestBody UtilityFactor updated) {
    updated.setId(id);
    return service.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    service.deleteById(id);
  }
}
