package com.db_project.db_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 생성 전략 설정
    private Long id;
    private String name;
    private int age;
    private String address;
    private String phone;
    private String email;
    private String description;

}

