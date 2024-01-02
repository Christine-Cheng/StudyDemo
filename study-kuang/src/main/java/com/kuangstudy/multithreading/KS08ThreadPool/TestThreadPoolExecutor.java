package com.kuangstudy.multithreading.KS08ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/7-16:36
 **/
public class TestThreadPoolExecutor {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                3,
                10,//计算公式  线程数量=cpu的数量*cpu期望利用率*(1 + wait time / service time)
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "--->办理业务");
            });
        }
        threadPool.shutdown();
    }
}
