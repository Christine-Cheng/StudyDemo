package com.kuangstudy.juc.demo13Pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/23-21:20
 * <p>
 * 3大方法
 * ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程只有一个线程
 * <p>
 * ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定的线程池的大小
 * 执行长期任务性能好，创建一个线程池，一池有N个固定的线程，有固定线程数的线程。
 * <p>
 * ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩的线程池,遇强则强,遇弱则弱
 * 执行很多短期异步任务，线程池根据需要创建新线程，但在先构建的线程可用时将重用他们。可扩容，遇强则强
 */
//Executors 工具类

/**
 * 3大方法
 * ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程只有一个线程
 *
 * ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定的线程池的大小
 * 执行长期任务性能好，创建一个线程池，一池有N个固定的线程，有固定线程数的线程。
 *
 * ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩的线程池,遇强则强,遇弱则弱
 * 执行很多短期异步任务，线程池根据需要创建新线程，但在先构建的线程可用时将重用他们。可扩容，遇强则强
 */

/**
 * 7大参数
 */
public class TestExecutors {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定的线程池的大小
        //ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩的线程池,遇强则强,遇弱则弱
        
        try {
            for (int i = 0; i < 100; i++) {
                //使用线程池创建线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "--->OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完,程序结束,关闭线程池
            threadPool.shutdown();
        }
    }
}
