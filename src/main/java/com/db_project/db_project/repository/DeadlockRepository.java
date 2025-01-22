package com.db_project.db_project.repository;

import com.db_project.db_project.entity.DeadlockEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeadlockRepository extends JpaRepository<DeadlockEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) // 비관적 쓰기 락
    Optional<DeadlockEntity> findById(Long id);
}
