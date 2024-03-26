package com.configuration.dynamicdatasource;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-03-26 10:13:44
 **/
@Service
@DS("master")
public class DynamicDataSource {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @DS("slave_1")
    public void insertIntoUser() {
        jdbcTemplate.execute(" insert into th_userinfo () values ()");
    }
    
    
}
