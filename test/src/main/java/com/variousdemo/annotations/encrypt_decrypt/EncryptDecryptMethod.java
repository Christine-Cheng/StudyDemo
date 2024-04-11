package com.variousdemo.annotations.encrypt_decrypt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Describe: 需要加解密的变量(属性)切点
 * @Author Happy
 * @Create 2024-04-07 17:59:52
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface EncryptDecryptMethod {
    String value() default "";
   
}
