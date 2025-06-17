package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {
}
