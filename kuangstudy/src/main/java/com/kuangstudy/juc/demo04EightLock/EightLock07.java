package com.kuangstudy.juc.demo04EightLock;

import java.util.concurrent.TimeUnit;

/**
 * @Describe: 8锁问题_关于锁的8个问题
 * 问题8、一个普通同步方法，一个静态同步方法，2部手机，请问先执行 发短信 还是 打电话？ 结果--->打电话
 * <p>
 * 结论:
 * 被synchronized和static修饰的方法，锁的对象是类的class对象。仅仅被synchronized修饰的方
 * 法，锁的对象是方法的调用者。即便是用同一个对象调用两个方法，锁的对象也不是同一个(更别说不同的对象)，所以两个
 * 方法用的不是同一个锁，后调用的方法不需要等待先调用的方法。
 * @Author Happy
 * @Create 2023/4/17-17:48
 **/
public class EightLock07 {
    public static void main(String[] args) {
        //俩个对象的Class类模板只有一个,static,锁的是Class
        Phone7 phone1 = new Phone7();
        Phone7 phone2 = new Phone7();
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

//Phone6唯一的一个Class对象
class Phone7 {
    //static 静态方法
    //类一加载就有了! 锁的是Class类模板
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    
    
    //普通同步方法,锁的是调用者
    public synchronized void call() {
        System.out.println("打电话");
    }
}
