package com.db_project.db_project.redis;

import com.db_project.db_project.service.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @BeforeEach
    public void setUp() {
        // 테스트를 시작하기 전에 Redis에 저장된 값을 삭제합니다.
        redisTemplate.opsForValue().getOperations().delete("testKey");
    }

    @Test
    public void testSetAndGet() {
        // Redis에 데이터 저장
        redisService.set("testKey", "testValue");

        // Redis에서 데이터 조회
        String value = redisService.get("testKey");

        // 값이 제대로 저장되었는지 확인
        assertEquals("testValue", value);
    }

    @Test
    public void testGetNonExistingKey() {
        // 존재하지 않는 키에 대해 조회 시 null이 반환되어야 함
        String value = redisService.get("nonExistingKey");
        assertEquals(null, value);
    }
}

