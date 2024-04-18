package com.test.variousdemo.aopdemo1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/5/8-0:15
 **/
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.aop")
public class Config {
}
