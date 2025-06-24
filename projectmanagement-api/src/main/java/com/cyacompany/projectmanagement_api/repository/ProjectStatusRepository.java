package com.cyacompany.projectmanagement_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyacompany.projectmanagement_api.model.ProjectStatus;

import java.util.List;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
  List<ProjectStatus> findAllByOrderByIdAsc();
}
