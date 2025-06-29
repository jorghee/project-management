package com.cyacompany.projectmanagement_api.repository;

import com.cyacompany.projectmanagement_api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {}
