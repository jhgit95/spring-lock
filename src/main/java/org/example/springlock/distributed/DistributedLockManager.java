package org.example.springlock.distributed;

public interface DistributedLockManager {
    void executeWithLock(Long key, Runnable task) throws InterruptedException;
}
