package org.example.springlock.pessimistic;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessimisticCounterRepository extends JpaRepository<PessimisticCounter, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PessimisticCounter> findById(Long id);
}
