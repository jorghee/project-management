package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.TaskExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskExecutionRepository extends JpaRepository<TaskExecution, Integer> {
  boolean existsByAssignment_TaskId(Integer taskId);
}
