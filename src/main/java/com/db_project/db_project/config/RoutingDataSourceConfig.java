package com.db_project.db_project.config;

import com.db_project.db_project.enums.DataSourceType;
import com.db_project.db_project.router.ReadWriteRoutingDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RoutingDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        ReadWriteRoutingDataSource routingDataSource = new ReadWriteRoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(masterDataSource());
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER, masterDataSource());
        targetDataSources.put(DataSourceType.REPLICA, replicaDataSource());
        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }

    @Bean
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/master")
                .username("root")
                .password("1234")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    @Bean
    public DataSource replicaDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/replica")
                .username("root")
                .password("1234")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }
}

