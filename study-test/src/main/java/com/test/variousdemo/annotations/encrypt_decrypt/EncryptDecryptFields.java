package com.test.variousdemo.annotations.encrypt_decrypt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Describe: 需要加解密的变量
 * @Author Happy
 * @Create 2024-04-08 10:43:36
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EncryptDecryptFields {
    String value() default "";
    
}
