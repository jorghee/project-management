package com.cyacompany.projectmanagement_api.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyacompany.projectmanagement_api.model.Position;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    List<Position> findAllByOrderByIdAsc();
}
