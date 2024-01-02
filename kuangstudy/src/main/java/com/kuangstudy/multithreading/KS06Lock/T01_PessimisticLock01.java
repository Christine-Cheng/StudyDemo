package com.kuangstudy.multithreading.KS06Lock;


/**
 * @author Happy
 * @create 2021/8/23-7:31
 **/
/*
 * 1.不使用任何同步操作的时候一定会产生线程安全的问题
 * 2.可以使用synchronized来进行互斥锁
 * 那如何无锁实现内?
 * */
public class T01_PessimisticLock01 {
    
    static Integer number = 0;
    
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread T01 = new Thread(new Runnable() {
                @Override
                public void run() {
                    //synchronized (T01_PessimisticLock01.class) {
                    //     while (number <= 100) {
                    //        System.out.println("ThreadName" + Thread.currentThread().getName() + ":--->" + number++);
                    //    }
                    //}
                    
                    while (number <= 100) {
                        System.out.println("ThreadName" + Thread.currentThread().getName() + ":--->" + number++);
                    }
                }
            });
            T01.start();
        }
    }
}
