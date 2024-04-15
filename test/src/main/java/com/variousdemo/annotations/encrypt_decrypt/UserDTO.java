package com.variousdemo.annotations.encrypt_decrypt;

import lombok.Data;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-07 18:04:55
 **/
@Data
public class UserDTO {
    @EncryptDecryptFields()
    private String name;
}
