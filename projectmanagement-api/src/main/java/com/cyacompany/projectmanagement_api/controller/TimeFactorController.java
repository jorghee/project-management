package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.model.TimeFactor;
import com.cyacompany.projectmanagement_api.service.TimeFactorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-factors")
public class TimeFactorController {

  private final TimeFactorService service;

  public TimeFactorController(TimeFactorService service) {
    this.service = service;
  }

  @GetMapping
  public List<TimeFactor> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public TimeFactor getById(@PathVariable Integer id) {
    return service.getById(id);
  }

  @PostMapping
  public TimeFactor create(@RequestBody TimeFactor timeFactor) {
    return service.save(timeFactor);
  }

  @PutMapping("/{id}")
  public TimeFactor update(@PathVariable Integer id, @RequestBody TimeFactor updated) {
    updated.setId(id);
    return service.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    service.deleteById(id);
  }
}
