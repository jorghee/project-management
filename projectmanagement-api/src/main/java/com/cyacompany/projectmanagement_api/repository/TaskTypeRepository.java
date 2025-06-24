package com.cyacompany.projectmanagement_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyacompany.projectmanagement_api.model.TaskType;

import java.util.List;

public interface TaskTypeRepository extends JpaRepository<TaskType, Integer> {
  List<TaskType> findAllByOrderByIdAsc();
}
