package com.cyacompany.projectmanagement_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyacompany.projectmanagement_api.model.UtilityFactor;

public interface UtilityFactorRepository extends JpaRepository<UtilityFactor, Integer> {
    List<UtilityFactor> findAllByOrderByIdAsc();
}
