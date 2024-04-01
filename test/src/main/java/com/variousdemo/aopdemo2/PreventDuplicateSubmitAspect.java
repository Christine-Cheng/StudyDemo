package com.variousdemo.aopdemo2;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
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
    
    // private ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
    private ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
    
    private ExpressionParser parser = new SpelExpressionParser();
    
    long lockTimeout = 30; // 锁的初始超时时间，单位：秒
    long renewInterval = 10; // 续约间隔，单位：秒
    
    @Pointcut("@annotation(preventDuplicateSubmit)")
    public void preventDuplicateSubmitPointcut(PreventDuplicateSubmit preventDuplicateSubmit) {
    }
    
    
    @Around("preventDuplicateSubmitPointcut(preventDuplicateSubmit)")
    public Object preventDuplicateSubmit1(ProceedingJoinPoint joinPoint, PreventDuplicateSubmit preventDuplicateSubmit) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        //todo 定义key的取值逻辑 可以是token 也可以是入参里的某个值
        String key = preventDuplicateSubmit.value() + "_" + methodName;
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
        Object[] args = joinPoint.getArgs();
        String value = preventDuplicateSubmit.value();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String parse = parse(value, method, args);
        System.out.println("自定义注解");
        key = joinPoint.getTarget().getClass().getSimpleName().concat(":").concat(methodName).concat(":").concat(parse);
        
        if (redisTemplate.opsForValue().setIfAbsent(key, "lock", lockTimeout, TimeUnit.SECONDS)) {
            try {
//                // 获取锁成功，执行业务逻辑
                return joinPoint.proceed();
//                // 定时任务，定期续约锁
//                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//                executorService.scheduleAtFixedRate(() -> {
//                    // 续约锁，延长锁的过期时间
//                    redisTemplate.expire(key, lockTimeout, TimeUnit.SECONDS);
//                }, renewInterval, renewInterval, TimeUnit.SECONDS);
                // 业务逻辑执行完毕，等待定时任务续约锁
//                Object result = joinPoint.proceed();
//                executorService.shutdown();
//                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

//                return result;
            } finally {
                // 释放锁
                redisTemplate.delete(key);
            }
        } else {
            // 获取锁失败，说明重复提交
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

