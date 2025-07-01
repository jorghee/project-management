package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Employee;
import com.cyacompany.projectmanagement_api.model.ExperienceLevel;
import com.cyacompany.projectmanagement_api.model.Position;
import com.cyacompany.projectmanagement_api.repository.EmployeeRepository;
import com.cyacompany.projectmanagement_api.repository.ExperienceLevelRepository;
import com.cyacompany.projectmanagement_api.repository.PositionRepository;
import com.cyacompany.projectmanagement_api.repository.TaskAssignmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final PositionRepository positionRepository;
  private final ExperienceLevelRepository experienceLevelRepository;
  private final TaskAssignmentRepository taskAssignmentRepository;

  public EmployeeService(EmployeeRepository employeeRepository, PositionRepository positionRepository,
      ExperienceLevelRepository experienceLevelRepository, TaskAssignmentRepository taskAssignmentRepository) {
    this.employeeRepository = employeeRepository;
    this.positionRepository = positionRepository;
    this.experienceLevelRepository = experienceLevelRepository;
    this.taskAssignmentRepository = taskAssignmentRepository;
  }

  /**
   * Obtiene una lista paginada de empleados.
   * @param pageable Configuración de paginación y ordenamiento.
   * @return Una página de empleados.
   */
  public Page<Employee> getAll(Pageable pageable) {
    return employeeRepository.findAll(pageable);
  }

  public Employee getById(Integer id) {
    return employeeRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
  }

  @Transactional
  public Employee create(Employee employee, Integer positionId, Integer experienceId) {
    Position position = positionRepository.findById(positionId)
      .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + positionId));
    ExperienceLevel experienceLevel = experienceLevelRepository.findById(experienceId)
      .orElseThrow(() -> new ResourceNotFoundException("ExperienceLevel not found with id: " + experienceId));
    
    employee.setPosition(position);
    employee.setExperienceLevel(experienceLevel);
    
    return employeeRepository.save(employee);
  }

  @Transactional
  public Employee update(Integer id, Employee employeeDetails, Integer positionId, Integer experienceId) {
    Employee employee = getById(id);
    Position position = positionRepository.findById(positionId)
      .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + positionId));
    ExperienceLevel experienceLevel = experienceLevelRepository.findById(experienceId)
      .orElseThrow(() -> new ResourceNotFoundException("ExperienceLevel not found with id: " + experienceId));

    employee.setName(employeeDetails.getName());
    employee.setStatus(employeeDetails.getStatus());
    employee.setPosition(position);
    employee.setExperienceLevel(experienceLevel);
    
    return employeeRepository.save(employee);
  }

  /**
   * Elimina un empleado solo si no tiene tareas asignadas.
   * La validación de si las tareas son "activas" se simplifica a si existe alguna asignación.
   * @param id El ID del empleado a eliminar.
   */
  @Transactional
  public void delete(Integer id) {
    if (!employeeRepository.existsById(id)) {
      throw new ResourceNotFoundException("Employee not found with id: " + id);
    }

    if (taskAssignmentRepository.existsByEmployeeId(id)) {
      throw new BusinessLogicException("Cannot delete employee with id: " + id + " because they have associated tasks.");
    }

    employeeRepository.deleteById(id);
  }
}
