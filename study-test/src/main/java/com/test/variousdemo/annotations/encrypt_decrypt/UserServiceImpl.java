package com.test.variousdemo.annotations.encrypt_decrypt;

import org.springframework.stereotype.Service;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-15 15:50:56
 **/
@Service
public class UserServiceImpl implements UserService {
    
    @Override
    public void addUser(UserDTO userDTO) {
        System.out.println("测试AOP解密");
        System.out.println(userDTO.getName());
    }
}
