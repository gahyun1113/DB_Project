package com.db_project.db_project.batch.service;

import com.db_project.db_project.repository.TestRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BatchInsertServiceTest2 {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job insertJob;

    @Autowired
    private TestRepository testRepository;

    @Test
    @DisplayName("스프링 배치를 이용한 데이터 삽입")
    public void testBatchInsertJob() throws Exception {

        // 배치 작업 실행
        JobExecution jobExecution = jobLauncher.run(insertJob, new org.springframework.batch.core.JobParameters());

        // 작업이 성공적으로 완료되었는지 확인
        assertEquals("COMPLETED", jobExecution.getStatus().toString());

    }
}

