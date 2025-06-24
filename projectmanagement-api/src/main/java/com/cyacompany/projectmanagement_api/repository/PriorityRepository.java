package com.cyacompany.projectmanagement_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyacompany.projectmanagement_api.model.Priority;

import java.util.List;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
  List<Priority> findAllByOrderByIdAsc();
}
