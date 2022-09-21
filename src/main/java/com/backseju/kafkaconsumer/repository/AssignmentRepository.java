package com.backseju.kafkaconsumer.repository;

import com.backseju.kafkaconsumer.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
