package com.example.backend.repository;

import com.example.backend.model.TimeRegistration;
import com.example.backend.model.Timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimerRepository extends JpaRepository<Timer, Integer> {
   // TimeRegistration deleteByRegistrationId(int registrationId);
}
