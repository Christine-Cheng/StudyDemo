package com.test.variousdemo.annotations.encrypt_decrypt;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Describe: 通过注解controller层进行切入，对加了注解的请求参数解密、响应参数进行解密
 * @Author Happy
 * @Create 2024-04-07 18:02:36
 **/
@Slf4j
@Aspect
@Component
public class EncryptDecryptAspect {
    
    // 假设存在一个用于加密和解密的服务EncryptDecryptService
    @Autowired
    private EncryptDecryptService encryptDecryptService;
    
    @Pointcut("@annotation(encryptDecryptMethod)")
    public void encryptDecryptPointCut(EncryptDecryptMethod encryptDecryptMethod) {
    }
    
    
    /**
     * 请求参数进行解密
     *
     * @param joinPoint
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Before("@annotation(EncryptDecryptMethod) && execution(* *(..))")
    public void encryptField(JoinPoint joinPoint) throws InvocationTargetException {
        //获取切点所有的参数
        //joinPoint.getArgs()返回方法参数值的数组，适用于获取参数值。
        Object[] args = joinPoint.getArgs();
        //获取切点的签名信息
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 获取方法名称
        String methodName = method.getName();
        
        //获取切点方法的参数名的数组
        //methodSignature.getParameterNames()返回方法参数名的数组，适用于获取参数名称。
        String[] parameterNames = methodSignature.getParameterNames();
        
        //获取切点方法的参数类型的数组
        Class[] parameterTypes = methodSignature.getParameterTypes();
        
        
        for (Object arg : args) {
            try {
                Field[] fields = arg.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(EncryptDecryptFields.class)) {
                        Method getter = findGetter(arg.getClass(), field);
                        Object value = getter.invoke(arg);
                        if (value instanceof String) {
                            String encryptedValue = encryptDecryptService.decrypt((String) value); //解密操作
                            Method setter = findSetter(arg.getClass(), field);
                            setter.invoke(arg, encryptedValue);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.error("EncryptDecryptAspect.encryptField 解密出错 methodName:[{}]", methodName);
                log.error(e.getMessage());
            }
        }
    }
    
    
    /**
     *
     * 请求到来之前进行解密
     * @editor luoxinxin
     * @param joinPoint
     */
    // // @Before("execution(* com.example.ApiController.postData(..)) && @annotation(DecryptField)")
    //  @Before("execution(* com.lxx.spb2hotdev.controller.TestController.*(..))")
    // // @Before("@annotation(PostMapping)")
    //  public void decryptRequest(JoinPoint joinPoint) {
    //      Object[] args = joinPoint.getArgs();
    //      for (Object arg : args) {
    //          try {
    //              for (Field field : arg.getClass().getDeclaredFields()) {
    //                  if (field.isAnnotationPresent(DecryptField.class)) {
    //                      field.setAccessible(true);
    //                      String fieldValue = (String) field.get(arg);
    //                      //解密
    //                     // field.set(arg, EncryptionUtils.decrypt(fieldValue));
    //                      field.set(arg, fieldValue + "hello");
    //                  }
    //              }
    //          } catch (IllegalAccessException e) {
    //              e.printStackTrace();
    //          }
    //      }
    //  }
    
    
    /**
     * 响应参数进行加密
     *
     * @param result
     */
    // @AfterReturning(pointcut = "@annotation(EncryptDecryptFields)", returning = "result")
    // public void encryptFields(Object result) {
    //     // 在响应时进行字段加密逻辑
    //     // 执行加密操作
    //     encryptDecryptService.encrypt(result);
    // }
    //
    
    /**
     * 查找给定类中与字段对应的getter方法。
     *
     * @param clazz 要查找getter方法的类
     * @param field 目标字段
     *
     * @return 找到的getter方法，如果未找到则返回null
     */
    private Method findGetter(Class<?> clazz, Field field) {
        String fieldName = field.getName(); // 获取字段名
        // 生成getter方法名
        String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            // 尝试获取clazz中名为getterName的方法
            return clazz.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            // 处理未找到方法的异常
            return null;
        }
    }
    
    
    /**
     * 查找给定类中与字段对应的setter方法。
     *
     * @param clazz 要查找的类
     * @param field 需要查找setter方法的字段
     *
     * @return 如果找到对应的setter方法则返回该方法，否则返回null
     */
    private Method findSetter(Class<?> clazz, Field field) {
        // 获取字段名，并根据字段名生成对应的setter方法名
        String fieldName = field.getName();
        String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            // 尝试获取clazz类中名为setterName，且参数类型与field字段类型相匹配的方法
            return clazz.getMethod(setterName, field.getType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            // 没有找到对应的setter方法，打印异常信息并返回null
            return null;
        }
    }
    
    
    // 同样可以实现对字段的解密操作
}
