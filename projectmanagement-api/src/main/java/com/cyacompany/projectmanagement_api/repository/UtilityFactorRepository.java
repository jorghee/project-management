package com.cyacompany.projectmanagement_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyacompany.projectmanagement_api.model.UtilityFactor;

import java.util.List;

public interface UtilityFactorRepository extends JpaRepository<UtilityFactor, Integer> {
  List<UtilityFactor> findAllByOrderByIdAsc();
}
