package org.example.springlock.myLock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


//@Component
@Slf4j
@RestController
public class FairLockTestController {

    private final RedissonClient redissonClient;

    public FairLockTestController(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void applyFairLock()  {
        log.info("applyFairLock");
//        String lockKey = redisDistributedLock.key(); // 애노테이션에 지정한 키
        RLock lock = redissonClient.getFairLock("lockKey zz"); // 공정락 생성

        try {
            // 락 대기 시간 10초, 락 유지 시간 5초 설정
            if (lock.tryLock(100, 100, TimeUnit.SECONDS)) {
                try {
                    log.info("fair lock ok ");
                } finally {
                    log.info("fair lock finally");
//                    if (lock.isHeldByCurrentThread()) { // 현재 스레드가 락을 보유한 경우에만 해제
//                        lock.unlock();
//                    }
                }
            } else {
                log.info("fair lock fail");
                throw new RuntimeException("Failed to acquire lock: " + "lockKey zz");
            }
        } catch (InterruptedException e) {
            log.info("fair lock interrupted");
            Thread.currentThread().interrupt();
            throw new RuntimeException("Fair Lock execution interrupted", e);
        }

    }


    @GetMapping("/fair-lock")
    public void testLock(){
        applyFairLock();
    }

    @GetMapping("/fair-lock2")
    @RedisDistributedLock(key = "#reqDto.getName + ':' + #reqDto.getId")
    public void testLock2(@RequestParam String a, @RequestBody  FairLockDto reqDto){
        log.info("test lock 2 clear");
        log.info("reqDto.getName() = " + reqDto.getName());
        String b = reqDto.getName();

    }



}