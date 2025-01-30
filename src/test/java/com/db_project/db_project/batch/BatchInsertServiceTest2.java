package com.db_project.db_project.batch;

import com.db_project.db_project.repository.TestRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
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

    @Test
    @DisplayName("스프링 배치를 이용한 데이터 삽입")
    public void testBatchInsertJob() throws Exception {

        // JobParameters 생성
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis()) // 고유한 timestamp를 추가
                .toJobParameters();

        // 배치 작업 실행
        JobExecution jobExecution = jobLauncher.run(insertJob, jobParameters);

        // 작업이 성공적으로 완료되었는지 확인
        assertEquals("COMPLETED", jobExecution.getStatus().toString());
        assertEquals("COMPLETED", jobExecution.getExitStatus().toString());

    }
}

