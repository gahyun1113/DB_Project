package com.db_project.db_project.config;


import com.db_project.db_project.entity.RedisEntity;
import com.db_project.db_project.repository.RedisRepository;
import com.db_project.db_project.repository.TestRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig2 {


    private final org.springframework.batch.core.repository.JobRepository jobRepository;
    private final org.springframework.transaction.PlatformTransactionManager transactionManager;
    private final RedisRepository repository;
    private final Faker faker = new Faker();

    @Bean
    public Job insertJob2() {
        return new JobBuilder("insertJob2", jobRepository)
                .start(insertStep2())
                .build();
    }

    @Bean
    public Step insertStep2() {
        return new StepBuilder("insertStep2", jobRepository)
                .<RedisEntity, RedisEntity>chunk(10000, transactionManager)
                .reader(reader2())
                .processor(processor2())
                .writer(writer2(repository))
                .build();
    }

    @Bean
    public ItemReader<RedisEntity> reader2() {
        return new ItemReader<>() {
            private int count = 0;
            private final int limit = 1_000_000;

            @Override
            public RedisEntity read() {
                if (count < limit) {
                    count++;
                    RedisEntity entity = new RedisEntity();
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
    public ItemProcessor<RedisEntity, RedisEntity> processor2() {
        return item -> item;
    }

    @Bean
    public ItemWriter<RedisEntity> writer2(RedisRepository repository) {
        return repository::saveAll;
    }


}