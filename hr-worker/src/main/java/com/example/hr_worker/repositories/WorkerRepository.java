package com.example.hr_worker.repositories;

import com.example.hr_worker.domain.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
