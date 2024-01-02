package com.kuangstudy.juc.demo09Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Describe:
 * 一个计数信号量.在概念上,信号量维持一组许可证.
 * 如果有必要,每个acquire()都会阻塞,直到许可证可用,然后才能使用它.
 * 每个release()添加许可证,潜在地释放阻塞获取方. 但是,没有使用实际的许可证对象;
 * Semaphore只保留可用数量的计数,并相应地执行.
 *
 * @Author Happy
 * @Create 2023/4/21-15:39
 **/
public class TestSemaphore {
    public static void main(String[] args) {
        //默认线程数量: 类比为停车位,可以进行限流
        //一次只有3个线程可以跑,剩余的阻塞,3个3个一起为一组
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                //acquire()得到
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到了车位");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放
                }
            }).start();
        }
    
        /**
         * 执行结果
         * Thread-0抢到了车位
         * Thread-4抢到了车位
         * Thread-1抢到了车位
         * Thread-4离开了车位
         * Thread-1离开了车位
         * Thread-0离开了车位
         *
         * Thread-3抢到了车位
         * Thread-5抢到了车位
         * Thread-2抢到了车位
         * Thread-3离开了车位
         * Thread-2离开了车位
         * Thread-5离开了车位
         */
    }
}
