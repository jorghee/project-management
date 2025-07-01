package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
  boolean existsByStageId(Integer stageId);
}
