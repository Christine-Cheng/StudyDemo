package com.kuangstudy.juc.demo21Lock;

import java.util.concurrent.TimeUnit;

/**
 * @Describe: 自旋锁测试
 * @Author Happy
 * @Create 2023/5/6-9:42
 **/
public class SpinLockTest {
    public static void main(String[] args) throws InterruptedException {
        //Lock lock=new ReentrantLock();
        //lock.lock();
        //lock.unlock();
        
        //底层使用的是自旋锁CAS
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        
        new Thread(() -> {
            spinLockDemo.lock();//T1加锁
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.unlock();//T1解锁
            }
        }, "T1").start();
        
        TimeUnit.SECONDS.sleep(1);//中间延迟
        
        new Thread(() -> {
            spinLockDemo.lock();//T2加锁
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.unlock();//T2解锁
            }
        }, "T2").start();
        
    }
}
