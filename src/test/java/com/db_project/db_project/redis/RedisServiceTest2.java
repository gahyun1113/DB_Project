package com.db_project.db_project.redis;

import com.db_project.db_project.entity.TestEntity;
import com.db_project.db_project.repository.TestRepository;
import com.db_project.db_project.service.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RedisServiceTest2 {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TestRepository repository;

    @BeforeEach
    public void setUp() {
        // 테스트를 시작하기 전에 Redis에 저장된 값을 삭제합니다.
        redisTemplate.getConnectionFactory().getConnection().flushAll();

        //초기값 할당
        for (int i = 0; i < 1500000; i++) {
            redisService.set("testKey: "+i, "testValue: "+i);
        }


    }

    @Test
    @DisplayName("레디스 조회시간과 디비 조회 시간 비교")
    public void testSetAndGet() {

        // Redis에서 데이터 조회
        Long redisStart = System.currentTimeMillis();
        String value = redisService.get("testKey: 7");
        Long redisEnd = System.currentTimeMillis();

        System.out.println("레디스 조회 시간 :" + (redisEnd-redisStart) + "ms");
        System.out.println("레디스 조회 데이터 : "+ value);

        //디비에서 값 조회하기
        Long start = System.currentTimeMillis();
        Optional<TestEntity> testEntity = repository.findById(3597914L);
        Long end = System.currentTimeMillis();

        System.out.println("디비 데이터 조회 시간 : " + (end-start) + "ms");
        System.out.println("디비 조회 데이터 : " + testEntity.get().getName());


    }

}

