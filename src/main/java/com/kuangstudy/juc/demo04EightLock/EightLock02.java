package com.kuangstudy.juc.demo04EightLock;

import java.util.concurrent.TimeUnit;

/**
 * @Describe:
 * 8锁问题_关于锁的8个问题
 * 问题3.添加一个普通方法后,是先执行 发短信 还是 hello ?  结果--->先执行 hello()
 *
 * 结论:
 * 新增的方法没有被synchronized修饰，不是同步方法，不受锁的影响，所以不需要等待。其他线
 * 程共用了一把锁，所以还需要等待。
 *
 * @Author Happy
 * @Create 2023/4/17-16:44
 **/
public class EightLock02 {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        new Thread(() -> {
            phone2.sendSms();
        }, "A").start();
        
        
        try {
            TimeUnit.SECONDS.sleep(1);//用JUC的类进行延时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //new Thread(() -> {
        //    phone2.call();
        //}, "B").start();
        
        
        new Thread(() -> {
            phone2.hello();
        }, "B").start();
        
    }
}

class Phone2 {
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
    
    //这里没有锁,不是同步方法,不受锁的影响
    public void hello() {
        System.out.println("hello");
    }
}
