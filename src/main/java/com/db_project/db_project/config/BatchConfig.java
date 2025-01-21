package com.db_project.db_project.config;


import com.db_project.db_project.entity.TestEntity;
import com.db_project.db_project.repository.TestRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {


    private final org.springframework.batch.core.repository.JobRepository jobRepository;
    private final org.springframework.transaction.PlatformTransactionManager transactionManager;
    private final TestRepository repository;
    private final Faker faker = new Faker();

    @Bean
    public Job insertJob() {
        return new JobBuilder("insertJob", jobRepository)
                .start(insertStep())
                .build();
    }

    @Bean
    public Step insertStep() {
        return new StepBuilder("insertStep", jobRepository)
                .<TestEntity, TestEntity>chunk(10000, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer(repository))
                .build();
    }

    @Bean
    public ItemReader<TestEntity> reader() {
        return new ItemReader<>() {
            private int count = 0;
            private final int limit = 1_000_000;

            @Override
            public TestEntity read() {
                if (count < limit) {
                    count++;
                    TestEntity entity = new TestEntity();
                    entity.setName(faker.name().fullName());
                    entity.setAge(faker.number().numberBetween(18, 80));
                    entity.setAddress(faker.address().fullAddress());
                    entity.setPhone(faker.phoneNumber().phoneNumber());
                    entity.setEmail(faker.internet().emailAddress());
                    entity.setDescription(faker.lorem().sentence());
                    return entity;
                }
                return null;
            }
        };
    }

    @Bean
    public ItemProcessor<TestEntity, TestEntity> processor() {
        return item -> item;
    }

    @Bean
    public ItemWriter<TestEntity> writer(TestRepository repository) {
        return repository::saveAll;
    }


}