package com.cyacompany.projectmanagement_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyacompany.projectmanagement_api.model.TimeFactor;

import java.util.List;

public interface TimeFactorRepository extends JpaRepository<TimeFactor, Integer> {
  List<TimeFactor> findAllByOrderByIdAsc();
}
