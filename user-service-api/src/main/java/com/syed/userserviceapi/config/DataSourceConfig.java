//package com.syed.userserviceapi.config;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
//        // Specify the database name in the URL
//        dataSourceBuilder.url("jdbc:mysql://localhost:3306/user_service_db");
//        dataSourceBuilder.username("root");
//        dataSourceBuilder.password("Syed45@sql");
//        return dataSourceBuilder.build();
//    }
//}
