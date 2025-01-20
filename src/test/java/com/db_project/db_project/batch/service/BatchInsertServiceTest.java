package com.db_project.db_project.batch.service;

import com.db_project.db_project.repository.TestRepository;
import com.db_project.db_project.service.BatchInsertService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BatchInsertServiceTest {

    @Autowired
    private BatchInsertService batchInsertService;

    @Test
    void testBatchInsert() {
        // When: BatchInsertService 실행
        batchInsertService.insertTestEntities();

    }
}
