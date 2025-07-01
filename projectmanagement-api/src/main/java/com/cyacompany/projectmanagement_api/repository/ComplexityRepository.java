package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.Complexity;
import com.cyacompany.projectmanagement_api.model.ComplexityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplexityRepository extends JpaRepository<Complexity, ComplexityId> {
  
  /**
   * Encuentra todas las complejidades asociadas a un ProjectUtility (es decir, a un Proyecto).
   * @param projectUtilityId El ID del ProjectUtility (que es el mismo que el ID del Proyecto).
   * @return Una lista de complejidades.
   */
  List<Complexity> findByProjectUtility_ProjectId(Integer projectUtilityId);

  /**
   * Encuentra todas las complejidades asociadas a un UtilityFactor.
   * @param utilityFactorId El ID del UtilityFactor.
   * @return Una lista de complejidades.
   */
  List<Complexity> findByUtilityFactor_Id(Integer utilityFactorId);
}
