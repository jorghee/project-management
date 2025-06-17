package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.model.ClientType;
import com.cyacompany.projectmanagement_api.service.ClientTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client-types")
public class ClientTypeController {

  private final ClientTypeService service;

  public ClientTypeController(ClientTypeService service) {
      this.service = service;
  }

  @GetMapping
  public List<ClientType> getAll() {
      return service.getAll();
  }

  @GetMapping("/{id}")
  public ClientType getById(@PathVariable Integer id) {
      return service.getById(id);
  }

  @PostMapping
  public ClientType create(@RequestBody ClientType clientType) {
      return service.save(clientType);
  }

  @PutMapping("/{id}")
  public ClientType update(@PathVariable Integer id, @RequestBody ClientType updated) {
      updated.setId(id);
      return service.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
      service.deleteById(id);
  }
}
