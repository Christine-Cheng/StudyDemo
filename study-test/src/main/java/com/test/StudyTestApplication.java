package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// @SpringBootApplication()
@EnableAspectJAutoProxy
public class StudyTestApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(StudyTestApplication.class, args);
        System.out.println("Hello World! The Fucking Spring Boot application is running.");
    }
    
}
