package com.variousdemo.annotations.encrypt_decrypt.dto;

import com.variousdemo.annotations.encrypt_decrypt.EncryptDecrypt;
import lombok.Data;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-07 18:04:55
 **/
@Data
public class TestEncryptDecryptDTO {
    @EncryptDecrypt(encryptKey = "")
    private String name;
}
