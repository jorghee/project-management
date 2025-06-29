package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.Complexity;
import com.cyacompany.projectmanagement_api.model.ComplexityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplexityRepository extends JpaRepository<Complexity, ComplexityId> {}
