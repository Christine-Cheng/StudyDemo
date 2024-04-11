// package com.variousdemo.annotations.encrypt_decrypt;
//
// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.Signature;
// import org.aspectj.lang.annotation.AfterReturning;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.annotation.Pointcut;
// import org.aspectj.lang.reflect.MethodSignature;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
//
// import java.lang.reflect.Field;
// import java.lang.reflect.InvocationTargetException;
// import java.lang.reflect.Method;
// import java.lang.reflect.Parameter;
//
// /**
//  * @Describe: 通过注解controller层进行切入，对加了注解的请求参数解密、响应参数进行解密
//  * @Author Happy
//  * @Create 2024-04-07 18:02:36
//  **/
// @Aspect
// @Component
// public class EncryptDecryptAspect {
//
//     // 假设存在一个用于加密和解密的服务EncryptDecryptService
//     @Autowired
//     private EncryptDecryptService encryptDecryptService;
//
//     @Pointcut("@annotation(encryptDecryptMethod)")
//     public void encryptDecryptPointCut(EncryptDecryptMethod encryptDecryptMethod) {
//     }
//
//
//     /**
//      * 请求参数进行解密
//      *
//      * @param joinPoint
//      *
//      * @throws IllegalAccessException
//      * @throws InvocationTargetException
//      */
//     @Before("@annotation(com.variousdemo.annotations.encrypt_decrypt.EncryptDecryptMethod) && execution(* *(..)) && args(..,dto)")
//     public void encryptField(JoinPoint joinPoint) throws IllegalAccessException, InvocationTargetException {
//         Object[] args = joinPoint.getArgs();
//         Signature signature = joinPoint.getSignature();
//         MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//         Method method = methodSignature.getMethod();
//         // 获取方法名称
//         String methodName = method.getName();
//         // 获取方法的参数名
//         String[] parameterNames = methodSignature.getParameterNames();
//         //获取方法的参数类型
//         Class[] parameterTypes = methodSignature.getParameterTypes();
//
//
//
//
//         for (Object dto : args) {
//             Field[] fields = dto.getClass().getDeclaredFields();
//             for (Field field : fields) {
//                 if (field.isAnnotationPresent(EncryptDecryptMethod.class)) {
//                     Method getter = findGetter(dto.getClass(), field);
//                     Object value = getter.invoke(dto);
//                     if (value instanceof String) {
//                         String encryptedValue = encryptDecryptService.decrypt((String) value); //解密操作
//                         Method setter = findSetter(dto.getClass(), field);
//                         setter.invoke(dto, encryptedValue);
//                     }
//                 }
//             }
//         }
//     }
//
//     /**
//      * 响应参数进行加密
//      *
//      * @param result
//      */
//     @AfterReturning(pointcut = "@annotation(com.variousdemo.annotations.encrypt_decrypt.EncryptDecryptMethod)", returning = "result")
//     public void encryptFields(Object result) {
//         // 在响应时进行字段加密逻辑
//         // 执行加密操作
//         encryptDecryptService.encrypt(result);
//     }
//
//
//     private Method findGetter(Class<?> clazz, Field field) {
//         String fieldName = field.getName();
//         String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//         try {
//             return clazz.getMethod(getterName);
//         } catch (NoSuchMethodException e) {
//             e.printStackTrace();
//             // 处理异常
//             return null;
//         }
//     }
//
//     private Method findSetter(Class<?> clazz, Field field) {
//         String fieldName = field.getName();
//         String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//         try {
//             return clazz.getMethod(setterName, field.getType());
//         } catch (NoSuchMethodException e) {
//             e.printStackTrace();
//             // 处理异常
//             return null;
//         }
//     }
//
//     // 同样可以实现对字段的解密操作
// }
