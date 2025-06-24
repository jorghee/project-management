package com.cyacompany.projectmanagement_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyacompany.projectmanagement_api.model.ExperienceLevel;

import java.util.List;

public interface ExperienceLevelRepository extends JpaRepository<ExperienceLevel, Integer> {
  List<ExperienceLevel> findAllByOrderByIdAsc();
}
