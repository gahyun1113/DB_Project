package com.db_project.db_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
@RequestMapping("/test")
public class NoPoolController {
    @GetMapping("/nopool")
    public String noPoolTest() {
        long startTime = System.currentTimeMillis();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/db_project", "root", "1234")) {
            long endTime = System.currentTimeMillis();
            return "No Pool - Connection time: " + (endTime-startTime) + " ms";
        } catch (SQLException e) {
            return "Connection failed: " + e.getMessage();
        }
    }
}
