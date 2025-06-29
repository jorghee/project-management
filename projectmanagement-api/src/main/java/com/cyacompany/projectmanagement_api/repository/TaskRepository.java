package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {}
