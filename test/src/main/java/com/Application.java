package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/14-10:06
 **/
@SpringBootApplication
@Configuration
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}
