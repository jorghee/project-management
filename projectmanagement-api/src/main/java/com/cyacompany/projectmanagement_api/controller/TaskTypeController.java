package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.model.TaskType;
import com.cyacompany.projectmanagement_api.service.TaskTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    updated.setId(id);
    return service.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    service.deleteById(id);
  }
}
