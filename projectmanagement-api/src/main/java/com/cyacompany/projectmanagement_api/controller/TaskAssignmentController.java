package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.TaskAssignmentRequest;
import com.cyacompany.projectmanagement_api.dto.TaskAssignmentResponse;
import com.cyacompany.projectmanagement_api.mapper.TaskAssignmentMapper;
import com.cyacompany.projectmanagement_api.model.TaskAssignment;
import com.cyacompany.projectmanagement_api.service.TaskAssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task-assignments")
public class TaskAssignmentController {

  private final TaskAssignmentService service;
  private final TaskAssignmentMapper mapper;

  public TaskAssignmentController(TaskAssignmentService service, TaskAssignmentMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<TaskAssignmentResponse>> getAll() {
    List<TaskAssignmentResponse> dtoList = service.getAll().stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskAssignmentResponse> getById(@PathVariable Integer id) {
    TaskAssignment assignment = service.getById(id);
    return ResponseEntity.ok(mapper.toResponse(assignment));
  }

  @PostMapping
  public ResponseEntity<TaskAssignmentResponse> create(@Valid @RequestBody TaskAssignmentRequest requestDto) {
    TaskAssignment assignmentToCreate = mapper.toEntity(requestDto);
    TaskAssignment newAssignment = service.create(assignmentToCreate, requestDto.getEmployeeId(), requestDto.getTaskId());
    return new ResponseEntity<>(mapper.toResponse(newAssignment), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskAssignmentResponse> update(@PathVariable Integer id, @Valid @RequestBody TaskAssignmentRequest requestDto) {
    TaskAssignment assignmentDetails = mapper.toEntity(requestDto);
    TaskAssignment updatedAssignment = service.update(id, assignmentDetails, requestDto.getEmployeeId(), requestDto.getTaskId());
    return ResponseEntity.ok(mapper.toResponse(updatedAssignment));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
