package com.cyacompany.projectmanagement_api.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyacompany.projectmanagement_api.model.TimeFactor;

public interface TimeFactorRepository extends JpaRepository<TimeFactor, Integer> {
    List<TimeFactor> findAllByOrderByIdAsc();
}
