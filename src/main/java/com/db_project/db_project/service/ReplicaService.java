package com.db_project.db_project.service;


import com.db_project.db_project.entity.ReplicaEntity;
import com.db_project.db_project.enums.DataSourceType;
import com.db_project.db_project.repository.replica.ReplicaRepository;
import com.db_project.db_project.router.DataSourceContextHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplicaService {

    private final ReplicaRepository repository;

    @Transactional
    public void saveReplica(ReplicaEntity replicaEntity) {
        DataSourceContextHolder.setDataSourceType(DataSourceType.MASTER);
        repository.save(replicaEntity);
    }

    public List<ReplicaEntity> getReplicas() {
        DataSourceContextHolder.setDataSourceType(DataSourceType.REPLICA);
        return repository.findAll();
    }
}
