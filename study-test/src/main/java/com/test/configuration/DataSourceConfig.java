package com.test.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-18 17:54:30
 **/
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/db_ruoyi");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setMaximumPoolSize(10);
        // 可以根据需要设置其他属性
        
        return dataSource;
    }
}
