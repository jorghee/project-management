package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Integer> {}
