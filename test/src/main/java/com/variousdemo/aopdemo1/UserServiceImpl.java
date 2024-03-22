package com.variousdemo.aopdemo1;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/5/7-23:17
 **/
public class UserServiceImpl implements UserService {
    
    @Override
    public void insert() {
        System.out.println("调用了dao层的insert()");
    }
    
}
