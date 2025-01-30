package com.db_project.db_project.repository;

import com.db_project.db_project.entity.DeadlockEntity;
import com.db_project.db_project.entity.KafkaEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KafkaRepository extends JpaRepository<KafkaEntity, Long> {
}