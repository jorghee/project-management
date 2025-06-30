package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.TaskTypeDto;
import com.cyacompany.projectmanagement_api.mapper.TaskTypeMapper;
import com.cyacompany.projectmanagement_api.model.TaskType;
import com.cyacompany.projectmanagement_api.service.TaskTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task-types")
public class TaskTypeController {

  private final TaskTypeService service;
  private final TaskTypeMapper mapper;

  public TaskTypeController(TaskTypeService service, TaskTypeMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<TaskTypeDto>> getAll() {
    List<TaskTypeDto> dtoList = service.getAll().stream()
      .map(mapper::toDto)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskTypeDto> getById(@PathVariable Integer id) {
    TaskType taskType = service.getById(id);
    return ResponseEntity.ok(mapper.toDto(taskType));
  }

  @PostMapping
  public ResponseEntity<TaskTypeDto> create(@Valid @RequestBody TaskTypeDto dto) {
    TaskType newTaskType = service.save(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(newTaskType), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskTypeDto> update(@PathVariable Integer id, @Valid @RequestBody TaskTypeDto dto) {
    dto.setId(id);
    TaskType updatedTaskType = service.save(mapper.toEntity(dto));
    return ResponseEntity.ok(mapper.toDto(updatedTaskType));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
