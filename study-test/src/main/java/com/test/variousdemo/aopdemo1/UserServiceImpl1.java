package com.test.variousdemo.aopdemo1;

import org.springframework.stereotype.Service;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/5/7-23:17
 **/
@Service
public class UserServiceImpl1 implements UserService1 {
    
    @Override
    public void insert() {
        System.out.println("调用了dao层的insert()");
    }
    
}
