package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Availability;
import com.cyacompany.projectmanagement_api.model.Employee;
import com.cyacompany.projectmanagement_api.repository.AvailabilityRepository;
import com.cyacompany.projectmanagement_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvailabilityService {
  
  private final AvailabilityRepository availabilityRepository;
  private final EmployeeRepository employeeRepository;

  public AvailabilityService(AvailabilityRepository availabilityRepository, EmployeeRepository employeeRepository) {
    this.availabilityRepository = availabilityRepository;
    this.employeeRepository = employeeRepository;
  }

  public Availability getByEmployeeId(Integer employeeId) {
    return availabilityRepository.findById(employeeId)
      .orElseThrow(() -> new ResourceNotFoundException("Availability not found for employee id: " + employeeId));
  }

  @Transactional
  public Availability createOrUpdate(Integer employeeId, Availability availabilityDetails) {
    Employee employee = employeeRepository.findById(employeeId)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
    
    // El ID de Availability es el mismo que el de Employee
    Availability availability = availabilityRepository.findById(employeeId)
      .orElse(new Availability());
    
    availability.setEmployeeId(employee.getId());
    availability.setEmployee(employee);
    availability.setAvailabilityStatus(availabilityDetails.getAvailabilityStatus());
    availability.setWeeklyCapacity(availabilityDetails.getWeeklyCapacity());
    availability.setCurrentLoad(availabilityDetails.getCurrentLoad());
    availability.setStatus(availabilityDetails.getStatus());
    
    return availabilityRepository.save(availability);
  }

  @Transactional
  public void delete(Integer employeeId) {
    if (!availabilityRepository.existsById(employeeId)) {
      throw new ResourceNotFoundException("Availability not found for employee id: " + employeeId);
    }
    availabilityRepository.deleteById(employeeId);
  }
}
