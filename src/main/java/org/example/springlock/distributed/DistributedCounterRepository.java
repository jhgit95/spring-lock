package org.example.springlock.distributed;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DistributedCounterRepository extends JpaRepository<DistributedCounter, Long> {
}
