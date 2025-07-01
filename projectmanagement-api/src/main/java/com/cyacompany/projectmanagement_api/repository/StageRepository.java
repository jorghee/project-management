package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StageRepository extends JpaRepository<Stage, Integer> {

  /**
   * Encuentra todas las etapas de un proyecto específico, ordenadas por su ID.
   * @param projectId El ID del proyecto.
   * @return Una lista de etapas.
   */
  List<Stage> findByProjectIdOrderByIdAsc(Integer projectId);

  boolean existsByProjectId(Integer projectId); 

  List<Stage> findAllByOrderByIdAsc();
}
