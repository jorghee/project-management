package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Availability;
import com.cyacompany.projectmanagement_api.model.Employee;
import com.cyacompany.projectmanagement_api.repository.AvailabilityRepository;
import com.cyacompany.projectmanagement_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvailabilityService {
  
  private final AvailabilityRepository availabilityRepository;
  private final EmployeeRepository employeeRepository;

  public AvailabilityService(AvailabilityRepository availabilityRepository, EmployeeRepository employeeRepository) {
    this.availabilityRepository = availabilityRepository;
    this.employeeRepository = employeeRepository;
  }

  /**
   * Obtiene todos los registros de disponibilidad.
   * @return Una lista de todas las disponibilidades.
   */
  public List<Availability> getAll() {
    return availabilityRepository.findAll();
  }

  public Availability getByEmployeeId(Integer employeeId) {
    return availabilityRepository.findById(employeeId)
      .orElseThrow(() -> new ResourceNotFoundException("Availability not found for employee id: " + employeeId));
  }

  @Transactional
  public Availability createOrUpdate(Integer employeeId, Availability availabilityDetails) {
    Employee employee = employeeRepository.findById(employeeId)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
    
    return availabilityRepository.findById(employeeId)
      .map(existingAvailability -> {
        existingAvailability.setAvailabilityStatus(availabilityDetails.getAvailabilityStatus());
        existingAvailability.setWeeklyCapacity(availabilityDetails.getWeeklyCapacity());
        existingAvailability.setCurrentLoad(availabilityDetails.getCurrentLoad());
        existingAvailability.setStatus(availabilityDetails.getStatus());

        return availabilityRepository.save(existingAvailability);
      })
      .orElseGet(() -> {
        availabilityDetails.setEmployee(employee);
        availabilityDetails.setEmployeeId(employeeId);

        return availabilityRepository.save(availabilityDetails);
      });
  }

  @Transactional
  public void delete(Integer employeeId) {
    if (!availabilityRepository.existsById(employeeId)) {
      throw new ResourceNotFoundException("Availability not found for employee id: " + employeeId);
    }
    availabilityRepository.deleteById(employeeId);
  }
}
