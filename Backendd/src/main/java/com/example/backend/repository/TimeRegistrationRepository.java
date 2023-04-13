package com.example.backend.repository;

import com.example.backend.model.TimeRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRegistrationRepository extends CrudRepository<TimeRegistration, Integer> {
}
