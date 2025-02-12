package com.db_project.db_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class PoolController {
    private final DataSource dataSource;

    @GetMapping("/pool")
    public String poolTest() {
        long startTime = System.nanoTime();
        try (Connection conn = dataSource.getConnection()) {
            long elapsedTime = System.nanoTime() - startTime;
            return "With Pool - Connection time: " + (elapsedTime / 1_000_000) + " ms";
        } catch (SQLException e) {
            return "Connection failed: " + e.getMessage();
        }
    }
}

