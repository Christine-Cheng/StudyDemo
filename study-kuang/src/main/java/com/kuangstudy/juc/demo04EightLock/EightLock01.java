package com.kuangstudy.juc.demo04EightLock;

import java.util.concurrent.TimeUnit;

/**
 * @Describe: 8锁问题_关于锁的8个问题
 * 一个对象一把锁
 * <p>
 * 问题1.标准情况下,两个线程先打印 发短信 还是 打电话?        结果--->1.发短信 2.打电话
 * 问题2.sendSms延迟4秒,两个线程先打印 发短信 还是 打电话?    结果--->1.发短信  2.打电话
 * <p>
 * 结论:
 * 1.synchronized 锁的对象是方法的调用者!
 * 2.俩个方法用的是同一个锁,所以是谁先拿到,谁先执行
 * <p>
 * 被synchronized修饰的方法，锁的对象是方法的调用者。因为两个方法的调用者是同一个，所以
 * 两个方法用的是同一个锁，先调用方法的先执行。
 * <p>
 * 被synchronized修饰的方法，锁的对象是方法的调用者。因为两个方法的调用者是同一个，所以
 * 两个方法用的是同一个锁，先调用方法的先执行，第二个方法只有在第一个方法执行完释放锁之后才能
 * 执行。
 * @Author Happy
 * @Create 2023/4/17-16:22
 **/
public class EightLock01 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();
        
        
        try {
            TimeUnit.SECONDS.sleep(1);//用JUC的类进行延时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone {
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
}
