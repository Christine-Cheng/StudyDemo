package com.kuangstudy.juc.demo21Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/5/5-23:36
 **/
public class LockDemo2 {
    
    /**
     * 可重入锁（也叫递归锁）
     * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
     * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
     */
    public static void main(String[] args) throws Exception {
        Phone100 phone = new Phone100();
        // T1 线程在外层获取锁时，也会自动获取里面的锁
        new Thread(() -> {
            phone.sendSMS();
        }, "T1").start();
        
        new Thread(() -> {
            phone.sendSMS();
        }, "T2").start();
    }
}

class Phone100 {
    Lock lock = new ReentrantLock();
    
    public void sendSMS() {
        
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " sendSMS");
            sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    public void sendEmail() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " sendEmail");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
