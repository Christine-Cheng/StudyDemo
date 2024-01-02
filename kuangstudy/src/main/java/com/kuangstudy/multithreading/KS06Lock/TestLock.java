package com.kuangstudy.multithreading.KS06Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Describe: 测试锁
 * @Author Happy
 * @Create 2023/3/27-11:42
 **/
public class TestLock {
    public static void main(String[] args) {
        LockTicket lockTicket = new LockTicket();
        new Thread(lockTicket).start();
        new Thread(lockTicket).start();
        new Thread(lockTicket).start();
    }
}

class LockTicket implements Runnable {
    
    //票
    int ticketNum = 10000;
    
    //定义 可重入锁
    private final ReentrantLock lock = new ReentrantLock();
    
    
    @Override
    public void run() {
        buyTicket();
    }
    
    public void buyTicket() {
        while (true) {
            try {
                lock.lock();//枷锁
                if (ticketNum > 0) {
                    System.out.println(Thread.currentThread().getName() + "取得的票是: " + ticketNum--);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }  finally {
                lock.unlock();//解锁
            }
        }
    }
}
