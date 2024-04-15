package com.variousdemo.annotations.encrypt_decrypt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-15 15:47:18
 **/
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping(value = "/addUser", name = "新增用户")
    @EncryptDecryptMethod
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }
}
