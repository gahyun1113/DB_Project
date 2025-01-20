package com.db_project.db_project.service;

import com.db_project.db_project.entity.TestEntity;
import com.db_project.db_project.repository.TestRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchInsertService {

    private static final int BATCH_SIZE = 10000 ; // Batch 크기
    private static final int TOTAL_COUNT = 500000; // 총 데이터 개수
    private final Faker faker = new Faker();
    private final TestRepository repository;


    @Transactional
    public void insertTestEntities() {
        List<TestEntity> batch = new ArrayList<>();
        for (int i = 1; i <= TOTAL_COUNT; i++) {
            batch.add(createFakeEntity());
            if (i % BATCH_SIZE == 0) {
                repository.saveAll(batch); // Batch 크기만큼 저장

                batch.clear(); // 리스트 초기화
                System.out.println(i + "개 데이터 삽입");
            }
        }

        // 나머지 데이터 저장
        if (!batch.isEmpty()) {
            repository.saveAll(batch);
            System.out.println("나머지 데이터 " + batch.size() + " 개 삽입");
        }

        System.out.println("Batch insert complete!");
    }

    private TestEntity createFakeEntity() {
        TestEntity entity = new TestEntity();
        entity.setName(faker.name().fullName());
        entity.setAge(faker.number().numberBetween(18, 80));
        entity.setAddress(faker.address().fullAddress());
        entity.setPhone(faker.phoneNumber().phoneNumber());
        entity.setEmail(faker.internet().emailAddress());
        entity.setDescription(faker.lorem().sentence());
        return entity;
    }
}
