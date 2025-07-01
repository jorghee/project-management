package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Activity;
import com.cyacompany.projectmanagement_api.model.Priority;
import com.cyacompany.projectmanagement_api.model.Task;
import com.cyacompany.projectmanagement_api.model.TaskType;
import com.cyacompany.projectmanagement_api.repository.ActivityRepository;
import com.cyacompany.projectmanagement_api.repository.PriorityRepository;
import com.cyacompany.projectmanagement_api.repository.TaskRepository;
import com.cyacompany.projectmanagement_api.repository.TaskExecutionRepository;
import com.cyacompany.projectmanagement_api.repository.TaskTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final ActivityRepository activityRepository;
  private final TaskTypeRepository taskTypeRepository;
  private final PriorityRepository priorityRepository;
  private final TaskExecutionRepository taskExecutionRepository;

  public TaskService(TaskRepository taskRepository, ActivityRepository activityRepository, TaskTypeRepository taskTypeRepository,
      PriorityRepository priorityRepository, TaskExecutionRepository taskExecutionRepository) {
    this.taskRepository = taskRepository;
    this.activityRepository = activityRepository;
    this.taskTypeRepository = taskTypeRepository;
    this.priorityRepository = priorityRepository;
    this.taskExecutionRepository = taskExecutionRepository;
  }
  
  /**
   * Obtiene una lista paginada de tareas.
   * @param pageable Configuración de paginación y ordenamiento.
   * @return Una página de tareas.
   */
  public Page<Task> getAll(Pageable pageable) {
    return taskRepository.findAll(pageable);
  }
  
  public Task getById(Integer id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
  }
  
  @Transactional
  public Task create(Task task, Integer activityId, Integer taskTypeId, Integer priorityId) {
    if (taskRepository.existsById(task.getId())) {
      throw new BusinessLogicException("Cannot create Task. ID " + task.getId() + " already exists.");
    }

    Activity activity = activityRepository.findById(activityId)
      .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + activityId));
    TaskType taskType = taskTypeRepository.findById(taskTypeId)
      .orElseThrow(() -> new ResourceNotFoundException("TaskType not found with id: " + taskTypeId));
    Priority priority = priorityRepository.findById(priorityId)
      .orElseThrow(() -> new ResourceNotFoundException("Priority not found with id: " + priorityId));

    task.setActivity(activity);
    task.setTaskType(taskType);
    task.setPriority(priority);

    return taskRepository.save(task);
  }
  
  @Transactional
  public Task update(Integer id, Task taskDetails, Integer activityId, Integer taskTypeId, Integer priorityId) {
    if (!taskRepository.existsById(id)) {
      throw new ResourceNotFoundException("Task not found with id: " + id);
    }

    Activity activity = activityRepository.findById(activityId)
      .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + activityId));
    TaskType taskType = taskTypeRepository.findById(taskTypeId)
      .orElseThrow(() -> new ResourceNotFoundException("TaskType not found with id: " + taskTypeId));
    Priority priority = priorityRepository.findById(priorityId)
      .orElseThrow(() -> new ResourceNotFoundException("Priority not found with id: " + priorityId));

    taskDetails.setId(id);
    taskDetails.setActivity(activity);
    taskDetails.setTaskType(taskType);
    taskDetails.setPriority(priority);

    return taskRepository.save(taskDetails);
  }

  /**
   * Elimina una tarea solo si no tiene ejecuciones de tiempo registradas.
   * @param id El ID de la tarea a eliminar.
   */
  @Transactional
  public void delete(Integer id) {
    if (!taskRepository.existsById(id)) {
      throw new ResourceNotFoundException("Task not found with id: " + id);
    }
    
    if (taskExecutionRepository.existsByAssignment_TaskId(id)) {
      throw new BusinessLogicException("Cannot delete task with id: " + id + " because it has registered executions.");
    }
    
    taskRepository.deleteById(id);
  }
}
