package com.cyacompany.projectmanagement_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyacompany.projectmanagement_api.model.ProjectStatus;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
    List<ProjectStatus> findAllByOrderByIdAsc();
}
