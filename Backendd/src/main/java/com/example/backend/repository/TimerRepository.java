package com.example.backend.repository;

import com.example.backend.model.Timer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimerRepository extends CrudRepository<Timer, Integer> {
}
