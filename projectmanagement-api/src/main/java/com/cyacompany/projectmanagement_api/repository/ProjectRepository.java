package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

  /**
   * Verifica si existe al menos un proyecto asociado a un ID de cliente.
   * Es más eficiente que traer todos los proyectos de un cliente solo para ver si la lista no está vacía.
   * @param clientId El ID del cliente.
   * @return true si existe al menos un proyecto, false en caso contrario.
   */
  boolean existsByClientId(Integer clientId);
}
