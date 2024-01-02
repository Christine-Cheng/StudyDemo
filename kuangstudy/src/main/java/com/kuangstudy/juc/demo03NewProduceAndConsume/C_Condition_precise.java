package com.kuangstudy.juc.demo03NewProduceAndConsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Describe: 精确通知顺序访问
 *
 * 题目：多线程之间按顺序调用，实现 A->B->C
 * 三个线程启动，要求如下：
 * AA 打印5次，BB 打印10次。CC打印15次，依次循环
 *
 * 重点：标志位
 *
 * @Author Happy
 * @Create 2023/4/17-15:25
 **/
public class C_Condition_precise {
    public static void main(String[] args) {
        DataCondition dataCondition = new DataCondition();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) dataCondition.printA();
        }, "A").start();
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) dataCondition.printB();
        }, "B").start();
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) dataCondition.printC();
        }, "C").start();
    }
}

class DataCondition {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1;
    
    //模拟 当number=1 执行A  当number=2 执行B   当number=3 执行C
    public void printA() {
        lock.lock();
        try {
            //判断等待-> 执行业务 -> 通知唤醒
    
            //判断等待
            while (number != 1) {
                //等待
                condition1.await();
            }
            
            //执行业务
            System.out.println(Thread.currentThread().getName() + "--->AAAAA");
            //for (int i = 0; i < 5; i++) {
            //    System.out.println(Thread.currentThread().getName() + "--->AAAAA");
            //}
    
            //通知,指定的干活！
            //唤醒,唤醒指定的人--->B
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    public void printB() {
        lock.lock();
        try {
            //判断等待-> 执行业务 -> 通知唤醒
            
            //判断等待
            while (number != 2) {
                //等待
                condition2.await();
            }
            
            //执行业务
            System.out.println(Thread.currentThread().getName() + "--->BBBBB");
            //for (int i = 0; i < 5; i++) {
            //    System.out.println(Thread.currentThread().getName() + "--->BBBBB");
            //}
    
            //通知,指定的干活！
            //通知唤醒,唤醒指定的人--->C
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    public void printC() {
        lock.lock();
        try {
            //判断等待-> 执行业务 -> 通知唤醒
    
            //判断等待
            while (number != 3) {
                //等待
                condition3.await();
            }
            
            //执行业务
            System.out.println(Thread.currentThread().getName() + "--->CCCCC");
            //for (int i = 0; i < 5; i++) {
            //    System.out.println(Thread.currentThread().getName() + "--->CCCCC");
            //}
    
            //通知,指定的干活！
            //通知唤醒,唤醒指定的人--->A
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
