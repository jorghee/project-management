package com.cyacompany.projectmanagement_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyacompany.projectmanagement_api.model.Priority;


public interface PriorityRepository extends JpaRepository<Priority, Integer> {
        List<Priority> findAllByOrderByIdAsc();
        
}
