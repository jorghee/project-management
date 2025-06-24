package com.cyacompany.projectmanagement_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyacompany.projectmanagement_api.model.ExperienceLevel;

public interface ExperienceLevelRepository extends JpaRepository<ExperienceLevel, Integer> {
    List<ExperienceLevel> findAllByOrderByIdAsc();
}
