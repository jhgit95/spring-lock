package org.example.springlock.optimistic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OptimisticCounterRepository extends JpaRepository<OptimisticCounter, Long> {
}

