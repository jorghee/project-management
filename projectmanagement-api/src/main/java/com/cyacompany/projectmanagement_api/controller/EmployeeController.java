package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.EmployeeRequest;
import com.cyacompany.projectmanagement_api.dto.EmployeeResponse;
import com.cyacompany.projectmanagement_api.mapper.EmployeeMapper;
import com.cyacompany.projectmanagement_api.model.Employee;
import com.cyacompany.projectmanagement_api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private final EmployeeService service;
  private final EmployeeMapper mapper;

  public EmployeeController(EmployeeService service, EmployeeMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }
  
  @GetMapping
  public ResponseEntity<Page<EmployeeResponse>> getAll(Pageable pageable) {
    Page<Employee> employeePage = service.getAll(pageable);
    Page<EmployeeResponse> dtoPage = employeePage.map(mapper::toResponse);
    return ResponseEntity.ok(dtoPage);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeResponse> getById(@PathVariable Integer id) {
    Employee employee = service.getById(id);
    return ResponseEntity.ok(mapper.toResponse(employee));
  }

  @PostMapping
  public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest requestDto) {
    Employee employeeToCreate = mapper.toEntity(requestDto);
    Employee newEmployee = service.create(employeeToCreate, requestDto.getPositionId(), requestDto.getExperienceLevelId());
    return new ResponseEntity<>(mapper.toResponse(newEmployee), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeResponse> update(@PathVariable Integer id, @Valid @RequestBody EmployeeRequest requestDto) {
    Employee employeeDetails = mapper.toEntity(requestDto);
    Employee updatedEmployee = service.update(id, employeeDetails, requestDto.getPositionId(), requestDto.getExperienceLevelId());
    return ResponseEntity.ok(mapper.toResponse(updatedEmployee));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
