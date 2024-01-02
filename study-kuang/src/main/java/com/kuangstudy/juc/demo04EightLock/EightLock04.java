package com.kuangstudy.juc.demo04EightLock;

import java.util.concurrent.TimeUnit;

/**
 * @Describe: 8锁问题_关于锁的8个问题
 * 问题5.俩个静态的同步方法,只有一个对象,是先执行 发短信 还是 打电话 ? 结果---> 发短信 打电话
 * <p>
 * 被synchronized和static修饰的方法，锁的对象是类的class对象。因为两个同步方法都被static修
 * 饰了，所以两个方法用的是同一个锁，后调用的方法需要等待先调用的方法。
 * @Author Happy
 * @Create 2023/4/17-17:02
 **/
public class EightLock04 {
    public static void main(String[] args) {
        
        Phone4 phone4 = new Phone4();
        new Thread(() -> {
            phone4.sendSms();
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

//Phone4唯一的一个Class对象
class Phone4 {
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
