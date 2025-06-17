package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
