package com.kuangstudy.juc.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/16-23:03
 **/
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        //并发：多个线程操作同一个资源类，把资源类丢入线程
        Ticket2 ticket2 = new Ticket2();
        
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket2.sale();
        }, "A").start();
        
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket2.sale();
        }, "B").start();
        
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket2.sale();
        }, "C").start();
        
        
    }
}


/**
 * Lock三部曲
 * 1. new ReentrantLock()
 * 2. Lock.lock();//加锁
 * 3. finally ---> lock.unlock();// 解锁
 */
//资源类 OOP
class Ticket2 {
    //属性,方法
    
    //票数
    private int number = 50;
    
    Lock lock = new ReentrantLock();
    
    //买票
    public void sale() {
        lock.lock();//加锁
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票，剩余：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }
    }
    
}
