package com.kuangstudy.juc.demo12SynchronousQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: SynchronousQueue 同步队列
 * <p>
 * SynchronousQueue 没有容量。
 * 与其他的 BlockingQueue 不同，SynchronousQueue是一个不存储元素的 BlockingQueue 。
 * 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
 * @Author Happy
 * @Create 2023/4/23-18:35
 **/
public class TestSynchronousQueue {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();
        
        
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();
    }
}
