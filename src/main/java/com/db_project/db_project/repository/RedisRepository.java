package com.db_project.db_project.repository;

import com.db_project.db_project.entity.RedisEntity;
import com.db_project.db_project.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends JpaRepository<RedisEntity, Long> {
}
