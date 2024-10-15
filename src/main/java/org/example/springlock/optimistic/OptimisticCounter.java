package org.example.springlock.optimistic;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OptimisticCounter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int count;
    @Version
    private int version;
}
