package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.TaskExecutionRequest;
import com.cyacompany.projectmanagement_api.dto.TaskExecutionResponse;
import com.cyacompany.projectmanagement_api.mapper.TaskExecutionMapper;
import com.cyacompany.projectmanagement_api.model.TaskExecution;
import com.cyacompany.projectmanagement_api.service.TaskExecutionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task-executions")
public class TaskExecutionController {

  private final TaskExecutionService service;
  private final TaskExecutionMapper mapper;

  public TaskExecutionController(TaskExecutionService service, TaskExecutionMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<TaskExecutionResponse>> getAll() {
    List<TaskExecutionResponse> dtoList = service.getAll().stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskExecutionResponse> getById(@PathVariable Integer id) {
    TaskExecution execution = service.getById(id);
    return ResponseEntity.ok(mapper.toResponse(execution));
  }

  @PostMapping
  public ResponseEntity<TaskExecutionResponse> create(@Valid @RequestBody TaskExecutionRequest requestDto) {
    TaskExecution executionToCreate = mapper.toEntity(requestDto);
    TaskExecution newExecution = service.create(executionToCreate, requestDto.getAssignmentId());
    return new ResponseEntity<>(mapper.toResponse(newExecution), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskExecutionResponse> update(@PathVariable Integer id, @Valid @RequestBody TaskExecutionRequest requestDto) {
    TaskExecution executionDetails = mapper.toEntity(requestDto);
    TaskExecution updatedExecution = service.update(id, executionDetails, requestDto.getAssignmentId());
    return ResponseEntity.ok(mapper.toResponse(updatedExecution));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
