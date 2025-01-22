package com.db_project.db_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "deadlock")
public class DeadlockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 생성 전략 설정
    private Long id;
    private String name;

}
