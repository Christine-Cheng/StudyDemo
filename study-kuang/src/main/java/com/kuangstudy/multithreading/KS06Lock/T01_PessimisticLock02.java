package com.kuangstudy.multithreading.KS06Lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Happy
 * @create 2021/8/23-7:47
 **/
public class T01_PessimisticLock02 {
    
    static AtomicInteger number = new AtomicInteger(0);
    
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread T01 = new Thread(new Runnable() {
                @Override
                public void run() {
                    
                    while (number.get() < 10) {
                        System.out.println("ThreadName" + Thread.currentThread().getName() + ":--->" + number.incrementAndGet());
                    }
                }
            });
            T01.start();
        }
    }
}
