package com.anunnaki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyWebApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(StudyWebApplication.class, args);
        System.out.println("study-web-service启动成功");
    }
    
}
