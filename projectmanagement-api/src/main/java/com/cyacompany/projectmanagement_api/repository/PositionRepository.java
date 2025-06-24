package com.cyacompany.projectmanagement_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyacompany.projectmanagement_api.model.Position;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {
  List<Position> findAllByOrderByIdAsc();
}
