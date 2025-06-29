package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
