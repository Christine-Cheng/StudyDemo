package com.variousdemo.annotations.encrypt_decrypt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-07 17:59:52
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EncryptDecrypt {
    String value() default "";  // 可以添加其他属性，例如加解密算法类型、密钥等
    
    String algorithm() default "AES";
    
    String encryptKey() default "";
    
    String decryptKey() default "";
}
