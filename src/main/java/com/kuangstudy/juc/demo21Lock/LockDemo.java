package com.kuangstudy.juc.demo21Lock;


/**
 * @Describe: ReentrantLock、Synchronized 就是一个典型的可重入锁
 * <p>
 * 可重入锁最大的作用就是避免死锁
 * @Author Happy
 * @Create 2023/5/5-23:34
 **/
public class LockDemo {
    
    /**
     * 可重入锁（也叫递归锁）
     * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
     * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
     */
    public static void main(String[] args) throws Exception {
        Phone99 phone = new Phone99();
        // T1 线程在外层获取锁时，也会自动获取里面的锁
        new Thread(() -> {
            phone.sendSMS();
        }, "T1").start();
        
        new Thread(() -> {
            phone.sendSMS();
        }, "T2").start();
    }
}

class Phone99 {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + " sendSMS");
        sendEmail();
    }
    
    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + " sendEmail");
    }
}
