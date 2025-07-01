package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
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
    if (executionRepository.existsById(execution.getId())) {
      throw new BusinessLogicException("Cannot create TaskExecution. ID " + execution.getId() + " already exists.");
    }

    TaskAssignment assignment = assignmentRepository.findById(assignmentId)
      .orElseThrow(() -> new ResourceNotFoundException("TaskAssignment not found with id: " + assignmentId));

    execution.setAssignment(assignment);
    return executionRepository.save(execution);
  }

  @Transactional
  public TaskExecution update(Integer id, TaskExecution executionDetails, Integer assignmentId) {
    if (!executionRepository.existsById(id)) {
      throw new ResourceNotFoundException("TaskExecution not found with id: " + id);
    }

    TaskAssignment assignment = assignmentRepository.findById(assignmentId)
      .orElseThrow(() -> new ResourceNotFoundException("TaskAssignment not found with id: " + assignmentId));

    executionDetails.setId(id);
    executionDetails.setAssignment(assignment);
   
    return executionRepository.save(executionDetails);
  }

  @Transactional
  public void delete(Integer id) {
    if (!executionRepository.existsById(id)) {
      throw new ResourceNotFoundException("TaskExecution not found with id: " + id);
    }
    executionRepository.deleteById(id);
  }
}
