package com.test.variousdemo.aopdemo2;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: 简单的放重复提交锁, 获取不到锁抛出异常, 不会等待(锁和事务同时存在时, 锁在事务之外, 事务的优先级是Ordered.LOWEST_PRECEDENCE)
 * @Author Happy
 * @Create 2024-04-01 16:43:47
 **/
@Slf4j
@Aspect
@Component
public class RepeatSubmitAspect {
    private ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
    
    private SpelExpressionParser parser = new SpelExpressionParser();
    
    private static final String PRE_REDIS_KEY = "REDIS_LOCK:";
    
    private static final String PAY_REDIS_CACHE_KEY_ = "PAY_REDIS_CACHE_KEY_";
    
    private static final Long lockTimeout = 30L;
    
    @Autowired
    private StringRedisTemplate strRedisTemplate;
    
    
    // 定义切点
    @Pointcut("@annotation(RepeatSubmit)")
    private void repeatCut() {
    }
    
    
    // 定义切面
    @Around("repeatCut()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        if (joinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            // 获取被调用的方法
            Method method = methodSignature.getMethod();
            Object target = joinPoint.getTarget();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            // 获取注解中的值
            String value = annotation.value();
            log.info("value:{}", value);
            String parse = this.parse(value, method, joinPoint.getArgs());
            String redisKey = PRE_REDIS_KEY.concat(target.getClass().getSimpleName()).concat(":").concat(method.getName()).concat(":").concat(parse);
            String redisCacheKey = PAY_REDIS_CACHE_KEY_.concat(redisKey);
            log.info("redisCacheKey:{}", redisCacheKey);
            if (Boolean.TRUE.equals(strRedisTemplate.opsForValue().setIfAbsent(redisCacheKey, parse, lockTimeout, TimeUnit.SECONDS))) {
                log.info("RepeatSubmit lock success redisCacheKey:{}", redisCacheKey);
                try {
                    return joinPoint.proceed();
                } finally {
                    log.info("RepeatSubmit unlock success redisCacheKey:{}", redisCacheKey);
                    strRedisTemplate.delete(redisCacheKey);
                }
            } else {
                log.error("RepeatSubmit lock fail redisCacheKey:{}", redisCacheKey);
                throw new Exception("请勿重复提交");
            }
            
        } else {
            log.error("RepeatSubmit joinPoint.getSignature() instanceof MethodSignature error");
            throw new Exception("RepeatSubmit 检查对象类型异常");
        }
    }
    
    
    /**
     * 解析Spel表达式
     *
     * @param expressionStr 表达式
     * @param method        方法
     * @param args          参数
     *
     * @return 解析后的表达式
     *
     * @return
     */
    private String parse(String expressionStr, Method method, Object[] args) {
        String[] parameterNames = discoverer.getParameterNames(method);
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        for (int i = 0; i < Objects.requireNonNull(parameterNames).length; i++) {
            standardEvaluationContext.setVariable(parameterNames[i], args[i]);
        }
        return parser.parseExpression(expressionStr).getValue(standardEvaluationContext, String.class);
    }
}
