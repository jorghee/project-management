package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.TaskRequest;
import com.cyacompany.projectmanagement_api.dto.TaskResponse;
import com.cyacompany.projectmanagement_api.mapper.TaskMapper;
import com.cyacompany.projectmanagement_api.model.Task;
import com.cyacompany.projectmanagement_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService service;
  private final TaskMapper mapper;

  public TaskController(TaskService service, TaskMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<Page<TaskResponse>> getAll(Pageable pageable) {
    Page<Task> taskPage = service.getAll(pageable);
    Page<TaskResponse> dtoPage = taskPage.map(mapper::toResponse);
    return ResponseEntity.ok(dtoPage);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskResponse> getById(@PathVariable Integer id) {
    Task task = service.getById(id);
    return ResponseEntity.ok(mapper.toResponse(task));
  }

  @PostMapping
  public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest requestDto) {
    Task taskToCreate = mapper.toEntity(requestDto);
    Task newTask = service.create(taskToCreate, requestDto.getActivityId(), requestDto.getTaskTypeId(), requestDto.getPriorityId());
    return new ResponseEntity<>(mapper.toResponse(newTask), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskResponse> update(@PathVariable Integer id, @Valid @RequestBody TaskRequest requestDto) {
    Task taskDetails = mapper.toEntity(requestDto);
    Task updatedTask = service.update(id, taskDetails, requestDto.getActivityId(), requestDto.getTaskTypeId(), requestDto.getPriorityId());
    return ResponseEntity.ok(mapper.toResponse(updatedTask));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
