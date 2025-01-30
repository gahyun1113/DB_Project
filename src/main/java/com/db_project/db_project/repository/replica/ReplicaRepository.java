package com.db_project.db_project.repository.replica;

import com.db_project.db_project.entity.ReplicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplicaRepository extends JpaRepository<ReplicaEntity, Long> {
}
