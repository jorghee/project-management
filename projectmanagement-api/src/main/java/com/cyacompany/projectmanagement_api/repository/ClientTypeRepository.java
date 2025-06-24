package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {
  List<ClientType> findAllByOrderByIdAsc();
}
