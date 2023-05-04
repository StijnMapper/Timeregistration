package com.example.backend.repository;

import com.example.backend.model.TimeRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRegistrationRepository extends JpaRepository<TimeRegistration, Integer> {
    List<TimeRegistration> findByProjectProjectId(int projectId);

}
