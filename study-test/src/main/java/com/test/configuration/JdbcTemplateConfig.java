package com.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-18 17:51:57
 **/
@Configuration
public class JdbcTemplateConfig {
    
    private final DataSource dataSource;
    
    public JdbcTemplateConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
}
