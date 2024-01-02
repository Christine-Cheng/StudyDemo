package com.kuangstudy.juc.demo04EightLock;

import java.util.concurrent.TimeUnit;

/**
 * @Describe:
 * 8锁问题_关于锁的8个问题
 * 问题6.俩个对象,俩个静态的同步方法,是先执行 发短信 还是 打电话 ? 结果---> 发短信 打电话
 *
 * 结论:
 * 被synchronized和static修饰的方法，锁的对象是类的class对象。因为两个同步方法都被static修
 * 饰了，即便用了两个不同的对象调用方法，两个方法用的还是同一个锁，后调用的方法需要等待先调用
 * 的方法。
 *
 * @Author Happy
 * @Create 2023/4/17-17:18
 **/
public class EightLock05 {
    public static void main(String[] args) {
        //俩个对象的Class类模板只有一个,static,锁的是Class
        Phone5 phone1 = new Phone5();
        Phone5 phone2 = new Phone5();
        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();
        
        try {
            TimeUnit.SECONDS.sleep(1);//用JUC的类进行延时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}

//Phone5唯一的一个Class对象
class Phone5 {
    //static 静态方法
    //类一加载就有了! 锁的是Class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    
    public static synchronized void call() {
        System.out.println("打电话");
    }
}
