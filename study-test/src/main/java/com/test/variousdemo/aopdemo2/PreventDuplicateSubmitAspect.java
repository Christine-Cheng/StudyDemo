package com.test.variousdemo.aopdemo2;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class PreventDuplicateSubmitAspect {
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    // LocalVariableTableParameterNameDiscoverer is deprecated since version 6.0.1 and marked for removal
    // private ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
    
    // DefaultParameterNameDiscoverer 是 Spring Framework 中的另一个方法参数名发现工具类。它在早期版本中被用来发现方法参数名。
    // 它通常基于 ASM（Java 字节码操作库）来查找方法参数的名称。在某些环境下,特别是在没有标准 Java 反射支持的情况下,或者对于某些特殊的字节码（如 Kotlin 生成的字节码）,可能会使用 DefaultParameterNameDiscoverer。
    // private ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
    
    // StandardReflectionParameterNameDiscoverer 使用标准的 Java 反射机制来发现方法参数名。
    // 它更适用于当前的 Java 版本,并且能够更准确地解析方法参数的名称。这对于支持 Java 8 及之后版本的字节码尤其重要。
    private ParameterNameDiscoverer discoverer = new StandardReflectionParameterNameDiscoverer();
    
    private ExpressionParser parser = new SpelExpressionParser();
    
    long lockTimeout = 30; // 锁的初始超时时间,单位：秒
    long renewInterval = 10; // 续约间隔,单位：秒
    
    @Pointcut("@annotation(preventDuplicateSubmit)")
    public void preventDuplicateSubmitPointcut(PreventDuplicateSubmit preventDuplicateSubmit) {
    }
    
    
    @Around("preventDuplicateSubmitPointcut(preventDuplicateSubmit)")
    public Object preventDuplicateSubmit1(ProceedingJoinPoint joinPoint, PreventDuplicateSubmit preventDuplicateSubmit) throws Throwable {
        // todo 定义key的取值逻辑 可以是token 也可以是入参里的某个值
        // ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // HttpServletRequest request = requestAttributes.getRequest();
        
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        
        PreventDuplicateSubmit annotation = method.getAnnotation(preventDuplicateSubmit.getClass());
        String annotationValue = annotation.value();
        String methodName = method.getName();
        String className = target.getClass().getSimpleName();
        String parse = parse(annotationValue, method, args);
        String key = annotationValue.concat("_").concat(className).concat(":").concat(methodName).concat(":").concat(parse);
        
        System.out.println("自定义注解");
        
        if (redisTemplate.opsForValue().setIfAbsent(key, "lock", lockTimeout, TimeUnit.SECONDS)) {
            try {
                // 获取锁成功，执行业务逻辑
                return joinPoint.proceed();
                
                // 定时任务，定期续约锁
                // ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                // executorService.scheduleAtFixedRate(() -> {
                //     // 续约锁，延长锁的过期时间
                //     redisTemplate.expire(key, lockTimeout, TimeUnit.SECONDS);
                // }, renewInterval, renewInterval, TimeUnit.SECONDS);
                
                //  业务逻辑执行完毕，等待定时任务续约锁
                // Object result = joinPoint.proceed();
                // executorService.shutdown();
                // executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
                //
                // return result;
            } finally {
                // 释放锁
                redisTemplate.delete(key);
            }
        } else {
            // 获取锁失败,说明重复提交
            throw new RuntimeException("请勿重复提交");
        }
    }
    
    /**
     * 解析SPL表达式
     *
     * @param expressionString
     * @param method
     * @param args
     *
     * @return
     */
    private String parse(String expressionString, Method method, Object[] args) {
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }
        return parser.parseExpression(expressionString).getValue(context, String.class);
    }
}

