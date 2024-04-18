package com.test.variousdemo.aopdemo1;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionSynchronization;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/5/7-23:43
 **/
@Controller
@Aspect
public class TransactionManagerHandler implements TransactionSynchronization {
    /* https://www.bilibili.com/video/BV1Lo4y1c7hR/?spm_id_from=333.999.0.0&vd_source=57009f8e13976f4a7089f0dbfa6bd9ca
     * 1.切面 拦截处理类
     * 2.切入点 拦截规则 前置,后置,异常,最终
     * 3.连接点 具体被拦截到的方法
     * 4.通知 在不同场景下拦截的功能
     * 5.注入 由spring完成,切面类包裹住对象,创建出代理对象
     * */
    
    @Pointcut("execution(public void UserServiceImpl1.insert())")
    public void pointcout() {
    }
    
    @Before("pointcout()")
    public void begin() {
        System.out.println("开启事务");
    }
    
    @AfterReturning("pointcout()")
    public void commit() {
        System.out.println("提交事务");
    }
    
    @AfterThrowing("pointcout()")
    public void rollback() {
        System.out.println("回滚事务");
    }
    
    @After("pointcout()")
    public void closeSession() {
        System.out.println("关闭事务");
    }
}
