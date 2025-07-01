package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Employee;
import com.cyacompany.projectmanagement_api.model.Task;
import com.cyacompany.projectmanagement_api.model.TaskAssignment;
import com.cyacompany.projectmanagement_api.repository.EmployeeRepository;
import com.cyacompany.projectmanagement_api.repository.TaskAssignmentRepository;
import com.cyacompany.projectmanagement_api.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskAssignmentService {

  private final TaskAssignmentRepository assignmentRepository;
  private final EmployeeRepository employeeRepository;
  private final TaskRepository taskRepository;

  public TaskAssignmentService(TaskAssignmentRepository assignmentRepository, EmployeeRepository employeeRepository, TaskRepository taskRepository) {
    this.assignmentRepository = assignmentRepository;
    this.employeeRepository = employeeRepository;
    this.taskRepository = taskRepository;
  }

  public List<TaskAssignment> getAll() {
    return assignmentRepository.findAll();
  }

  public TaskAssignment getById(Integer id) {
    return assignmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TaskAssignment not found with id: " + id));
  }

  @Transactional
  public TaskAssignment create(TaskAssignment assignment, Integer employeeId, Integer taskId) {
    Employee employee = employeeRepository.findById(employeeId)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
    Task task = taskRepository.findById(taskId)
      .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

    assignment.setEmployee(employee);
    assignment.setTask(task);
    
    return assignmentRepository.save(assignment);
  }

  @Transactional
  public TaskAssignment update(Integer id, TaskAssignment assignmentDetails, Integer employeeId, Integer taskId) {
    TaskAssignment existingAssignment = getById(id);
    Employee employee = employeeRepository.findById(employeeId)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
    Task task = taskRepository.findById(taskId)
      .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

    existingAssignment.setEmployee(employee);
    existingAssignment.setTask(task);
    existingAssignment.setStatus(assignmentDetails.getStatus());

    return assignmentRepository.save(existingAssignment);
  }

  @Transactional
  public void deleteById(Integer id) {
    if (!assignmentRepository.existsById(id)) {
      throw new ResourceNotFoundException("TaskAssignment not found with id: " + id);
    }
    assignmentRepository.deleteById(id);
  }
}
