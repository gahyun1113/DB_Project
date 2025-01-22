package com.db_project.db_project.deadlock;

import com.db_project.db_project.entity.DeadlockEntity;
import com.db_project.db_project.repository.DeadlockRepository;
import com.db_project.db_project.service.DeadlockService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class DeadlockTest {

    @Autowired
    private DeadlockService deadlockService;

    @Test
    @Timeout(value = 30, unit = TimeUnit.SECONDS) // 30초 타임아웃
    @DisplayName("쓰레드 자원 경쟁 데드락 : 테스트 실패가 데드락 상황 발생한것.")
    public void testDeadlock() throws InterruptedException {
        Thread thread1 = new Thread(() -> deadlockService.methodA(), "Thread-1");
        Thread thread2 = new Thread(() -> deadlockService.methodB(), "Thread-2");

        thread1.start();
        thread2.start();

        //현재 스레드가 다른 스레드의 완료를 기다리도록 설정
        thread1.join();
        thread2.join();

        System.out.println("Test completed.");
    }
}



