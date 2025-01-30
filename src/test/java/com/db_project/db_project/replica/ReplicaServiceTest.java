//package com.db_project.db_project.replica;
//
//import com.db_project.db_project.config.RoutingDataSourceConfig;
//import com.db_project.db_project.entity.ReplicaEntity;
//import com.db_project.db_project.repository.replica.ReplicaRepository;
//import com.db_project.db_project.service.ReplicaService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.List;
//
//@SpringBootTest
//@Import(RoutingDataSourceConfig.class) // RoutingDataSourceConfig만 로드
//@EnableJpaRepositories(basePackageClasses = ReplicaRepository.class) // 특정 리포지토리만 활성화
//class ReplicaServiceTest {
//
//    @Autowired
//    private ReplicaService replicaService;
//
//    @Autowired
//    private ReplicaRepository replicaRepository;
//
//    @BeforeEach
//    void setUp() {
//        // 초기화 데이터 설정
//        replicaRepository.deleteAll(); // 테스트 시작 전에 데이터 초기화
//    }
//
//    @Test
//    @Rollback(false) // DB에서 동작을 직접 확인하려면 롤백하지 않도록 설정
//    void saveUserTest() {
//        // Given
//        ReplicaEntity replicaEntity = new ReplicaEntity();
//        replicaEntity.setName("쓰기 작업은 마스터에 저장합니다.");
//
//        // When
//        replicaService.saveReplica(replicaEntity);
//
//
//    }
//
//    @Test
//    void getUsersTest() {
//        // Given
//        ReplicaEntity replicaEntity = new ReplicaEntity();
//        replicaEntity.setName("레플리카 테스트1");
//
//        replicaRepository.save(replicaEntity);
//
//        ReplicaEntity replicaEntity2 = new ReplicaEntity();
//        replicaEntity2.setName("레플리카 테스트2");
//
//        replicaRepository.save(replicaEntity2);
//
//        // When
//        List<ReplicaEntity> replicas = replicaService.getReplicas();
//
//        // Then
//        System.out.println("레플리카에서 값 읽어오기 : "+replicas.get(0).getName());
//
//    }
//}
//
