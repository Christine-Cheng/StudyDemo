package com.kuangstudy.juc.demo03NewProduceAndConsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Describe: 新版生产者和消费者写法
 * @Author Happy
 * @Create 2023/4/17-15:09
 **/
public class B_Condition {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data3.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data3.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data3.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data3.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

//生产者消费者的线程通讯问题: 1判断等待,2业务,3通知 三部曲
//数字 资源类
class Data3 {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    
    //condition.await();// 等待
    //condition.signalAll();// 全部唤醒
    
    
    //加一
    public void increase() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                //等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "--->" + number);
            
            //通知其他线程,我+1完毕了
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    //减一
    public void decrease() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                //等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "--->" + number);
            //通知其他线程,我-1完毕了
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
}
