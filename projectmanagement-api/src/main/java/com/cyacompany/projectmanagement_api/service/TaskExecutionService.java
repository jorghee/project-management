package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.TaskAssignment;
import com.cyacompany.projectmanagement_api.model.TaskExecution;
import com.cyacompany.projectmanagement_api.repository.TaskAssignmentRepository;
import com.cyacompany.projectmanagement_api.repository.TaskExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskExecutionService {

  private final TaskExecutionRepository executionRepository;
  private final TaskAssignmentRepository assignmentRepository;

  public TaskExecutionService(TaskExecutionRepository executionRepository, TaskAssignmentRepository assignmentRepository) {
    this.executionRepository = executionRepository;
    this.assignmentRepository = assignmentRepository;
  }

  public List<TaskExecution> getAll() {
    return executionRepository.findAll();
  }

  public TaskExecution getById(Integer id) {
    return executionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TaskExecution not found with id: " + id));
  }

  @Transactional
  public TaskExecution create(TaskExecution execution, Integer assignmentId) {
    TaskAssignment assignment = assignmentRepository.findById(assignmentId)
      .orElseThrow(() -> new ResourceNotFoundException("TaskAssignment not found with id: " + assignmentId));

    execution.setAssignment(assignment);
    return executionRepository.save(execution);
  }

  @Transactional
  public TaskExecution update(Integer id, TaskExecution executionDetails, Integer assignmentId) {
    TaskExecution existingExecution = getById(id);
    TaskAssignment assignment = assignmentRepository.findById(assignmentId)
      .orElseThrow(() -> new ResourceNotFoundException("TaskAssignment not found with id: " + assignmentId));

    existingExecution.setExecutionDate(executionDetails.getExecutionDate());
    existingExecution.setHours(executionDetails.getHours());
    existingExecution.setMinutes(executionDetails.getMinutes());
    existingExecution.setStatus(executionDetails.getStatus());
    existingExecution.setAssignment(assignment);
    
    return executionRepository.save(existingExecution);
  }

  @Transactional
  public void delete(Integer id) {
    if (!executionRepository.existsById(id)) {
      throw new ResourceNotFoundException("TaskExecution not found with id: " + id);
    }
    executionRepository.deleteById(id);
  }
}
