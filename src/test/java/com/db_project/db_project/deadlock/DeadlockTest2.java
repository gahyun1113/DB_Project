package com.db_project.db_project.deadlock;

import com.db_project.db_project.entity.DeadlockEntity;
import com.db_project.db_project.repository.DeadlockRepository;
import com.db_project.db_project.service.DeadlockService;
import com.db_project.db_project.service.DeadlockService2;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class DeadlockTest2 {

    @Autowired
    private DeadlockService2 deadlockService2;

    @Autowired
    private DeadlockRepository repository;

    private Faker faker = new Faker();

    @Test
    public void testDeadlockScenario() throws InterruptedException {
        // 초기 데이터 설정
        DeadlockEntity resource1 = new DeadlockEntity();
        resource1.setName(faker.name().firstName() + "-초기값");
        repository.save(resource1);

        DeadlockEntity resource2 = new DeadlockEntity();
        resource2.setName(faker.name().firstName() + "-초기값");
        repository.save(resource2);

        //id 가져오기
        Long firstId = resource1.getId();
        Long secondId = resource2.getId();

        Thread thread1 = new Thread(() -> {
            deadlockService2.transferFromAccount1ToAccount2(firstId, secondId);
        });

        Thread thread2 = new Thread(() -> {
            deadlockService2.transferFromAccount2ToAccount1(secondId, firstId);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}



