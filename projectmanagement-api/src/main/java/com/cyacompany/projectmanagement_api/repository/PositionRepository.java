package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
