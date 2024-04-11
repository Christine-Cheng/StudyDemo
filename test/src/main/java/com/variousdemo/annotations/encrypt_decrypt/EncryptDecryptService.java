package com.variousdemo.annotations.encrypt_decrypt;

import org.springframework.stereotype.Service;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-07 18:18:35
 **/
@Service
public class EncryptDecryptService {
    // 加密key
    private static final String ENCRYPT_KEY = "123456";
    // 解密key
    private static final String DECRYPT_KEY = "123456";
    
    public String encrypt(String value) {
        // todo 加密
        return value;
        
    }
    
    public String decrypt(String value) {
        // todo 解密
        return value;
    }
}
