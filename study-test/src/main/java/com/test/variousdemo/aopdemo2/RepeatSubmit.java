package com.test.variousdemo.aopdemo2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-01 16:47:14
 **/
@Target(ElementType.METHOD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {
    String value();
}
