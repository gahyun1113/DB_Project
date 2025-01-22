package com.db_project.db_project.service;

import com.db_project.db_project.entity.DeadlockEntity;
import com.db_project.db_project.repository.DeadlockRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeadlockService2 {

    private final DeadlockRepository repository;
    private Faker faker = new Faker();

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transferFromAccount1ToAccount2(Long firstId, Long secondId) {
        DeadlockEntity resource1 = repository.findById(firstId).orElseThrow();
        DeadlockEntity resource2 = repository.findById(secondId).orElseThrow();

        // 자원 1 잠금
        System.out.println("첫번째 메소드에서 자원 1을 잠금");
        resource1.setName(resource1.getName()+"-자원 1 업데이트");
        repository.save(resource1);

        // 2초 지연을 통해 데드락을 유발
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 자원 2 잠금
        System.out.println("첫번째 메소드에서 자원 2을 잠금");
        resource2.setName(resource2.getName()+"-자원 2 업데이트");
        repository.save(resource2);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transferFromAccount2ToAccount1(Long secondId, Long firstId) {
        DeadlockEntity resource1 = repository.findById(firstId).orElseThrow();
        DeadlockEntity resource2 = repository.findById(secondId).orElseThrow();

        // 자원 2 잠금
        System.out.println("두번째 메소드에서 자원 2을 잠금");
        resource2.setName(resource2.getName()+"-자원 2 업데이트");
        repository.save(resource2);

        // 2초 지연을 통해 데드락을 유발
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 자원 1 잠금
        System.out.println("두번째 메소드에서 자원 1을 잠금");
        resource1.setName(resource1.getName()+"-자원 1 업데이트");
        repository.save(resource1);
    }
}
