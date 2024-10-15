package org.example.springlock.distributed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributedCounterRepository extends JpaRepository<DistributedCounter, Long> {
}
