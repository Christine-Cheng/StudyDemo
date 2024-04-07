package com.variousdemo.annotations.encrypt_decrypt;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-07 18:02:36
 **/
@Aspect
@Component
public class EncryptDecryptAspect {
    
    // 假设存在一个用于加密和解密的服务EncryptDecryptService
    @Autowired
    private EncryptDecryptService encryptDecryptService;
    
    
    @Before("@annotation(com.variousdemo.annotations.encrypt_decrypt.EncryptDecrypt) && execution(* *(..)) && args(..,dto)")
    public void encryptField(JoinPoint joinPoint) throws IllegalAccessException, InvocationTargetException {
        Object[] args = joinPoint.getArgs();
        for (Object dto : args) {
            Field[] fields = dto.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(EncryptDecrypt.class)) {
                    Method getter = findGetter(dto.getClass(), field);
                    Object value = getter.invoke(dto);
                    if (value instanceof String) {
                        String encryptedValue = encryptDecryptService.encrypt((String) value); // 进行加密操作
                        Method setter = findSetter(dto.getClass(), field);
                        setter.invoke(dto, encryptedValue);
                    }
                }
            }
        }
    }
    
    
    private Method findGetter(Class<?> clazz, Field field) {
        String fieldName = field.getName();
        String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            return clazz.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            // 处理异常
            return null;
        }
    }
    
    private Method findSetter(Class<?> clazz, Field field) {
        String fieldName = field.getName();
        String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            return clazz.getMethod(setterName, field.getType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            // 处理异常
            return null;
        }
    }
    
    // 同样可以实现对字段的解密操作
}
