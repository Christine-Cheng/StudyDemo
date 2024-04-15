package com.variousdemo.annotations.encrypt_decrypt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Describe: 需要加解密的方法
 * @Author Happy
 * @Create 2024-04-08 10:43:36
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface EncryptDecryptMethod {
    String value() default "";
    
}
