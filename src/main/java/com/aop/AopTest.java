package com.aop;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/5/7-23:25
 **/
public class AopTest {
    
    @Test
    public void testInsert(){
        //ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //UserService userService=context.getBean(UserService.class);
        //userService.insert();
    }
    
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = context.getBean(UserService.class);
        userService.insert();
    }
   
}
