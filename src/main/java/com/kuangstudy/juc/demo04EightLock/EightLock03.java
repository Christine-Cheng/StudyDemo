package com.kuangstudy.juc.demo04EightLock;

import java.util.concurrent.TimeUnit;

/**
 * @Describe: 8锁问题_关于锁的8个问题
 * 问题4.两个对象,俩个同步方法,是先执行 发短信 还是 打电话 ?  结果--->先执行 打电话
 *
 * 结论:
 * synchronized 锁的对象是方法的调用者!
 * 俩个对象,俩个调用者,俩把锁.按时间谁先得到谁先执行
 *
 * 被synchronized修饰的方法，锁的对象是方法的调用者。因为用了两个对象调用各自的方法，所
 * 以两个方法的调用者不是同一个，所以两个方法用的不是同一个锁，后调用的方法不需要等待先调用的
 * 方法。
 *

 * @Author Happy
 * @Create 2023/4/17-16:51
 **/
public class EightLock03 {
    public static void main(String[] args) {
        //两个对象,俩个调用者,俩把锁
        Phone3 phone3 = new Phone3();
        Phone3 phone4 = new Phone3();
        new Thread(() -> {
            phone3.sendSms();
        }, "A").start();
        
        try {
            TimeUnit.SECONDS.sleep(1);//用JUC的类进行延时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        new Thread(() -> {
            phone4.call();
        }, "B").start();
        
        
    }
}

class Phone3 {
    //synchronized 锁的对象是方法的调用者!
    //俩个方法用的是同一个锁,所以是谁先拿到,谁先执行
    
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("发短信");
    }
    
    public synchronized void call() {
        System.out.println("打电话");
    }
    
    public void hello() {
        System.out.println("hello");
    }
}
