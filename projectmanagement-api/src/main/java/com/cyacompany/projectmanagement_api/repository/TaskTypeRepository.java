package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTypeRepository extends JpaRepository<TaskType, Integer> {
}
