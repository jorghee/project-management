package com.cyacompany.projectmanagement_api.repository;
import com.cyacompany.projectmanagement_api.model.TimeFactor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeFactorRepository extends JpaRepository<TimeFactor, Integer> {
}
