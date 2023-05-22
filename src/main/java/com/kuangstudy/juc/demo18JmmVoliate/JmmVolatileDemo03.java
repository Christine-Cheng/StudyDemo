package com.kuangstudy.juc.demo18JmmVoliate;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Describe: 利用atomic原子类 和 volatile 保证原子性
 * @Author Happy
 * @Create 2023/5/4-16:12
 **/
public class JmmVolatileDemo03 {
    
    //用原子类 Integer
    private volatile static AtomicInteger num = new AtomicInteger();
    
    public static void add() {
        num.getAndIncrement();
        //不是简单的 num++ ; 用的是底层Unsafe类下的CAS 实际调用方法: unsafe.getAndAddInt(this, valueOffset, 1);
    }
    
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    add();
                }
            }).start();
        }
        
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        
        System.out.println(Thread.currentThread().getName() + " num = " + num);
        
    }
    
}
