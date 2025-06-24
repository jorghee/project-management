package com.cyacompany.projectmanagement_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyacompany.projectmanagement_api.model.TaskType;

public interface TaskTypeRepository extends JpaRepository<TaskType, Integer> {
    List<TaskType> findAllByOrderByIdAsc();
}