package com.cyacompany.projectmanagement_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyacompany.projectmanagement_api.model.TaskType;
import com.cyacompany.projectmanagement_api.service.TaskTypeService;

@RestController
@RequestMapping("/api/task-types")
public class TaskTypeController {

  private final TaskTypeService service;

  public TaskTypeController(TaskTypeService service) {
    this.service = service;
  }

  @GetMapping
  public List<TaskType> getAll() { 
    return service.getAll();
  }

  @GetMapping("/{id}")
  public TaskType getById(@PathVariable Integer id) {
    return service.getById(id);
  }

  @PostMapping
  public TaskType create(@RequestBody TaskType taskType) {
    return service.save(taskType);
  }

  @PutMapping("/{id}")
  public TaskType update(@PathVariable Integer id, @RequestBody TaskType updated) {
    updated.setId(id); // Asegúrate de que tu entidad TaskType tenga un método setId()
    return service.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    service.deleteById(id);
  }
}