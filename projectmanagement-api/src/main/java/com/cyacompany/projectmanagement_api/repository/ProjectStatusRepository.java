package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
}
