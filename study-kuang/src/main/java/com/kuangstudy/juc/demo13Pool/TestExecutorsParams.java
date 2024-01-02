package com.kuangstudy.juc.demo13Pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: 线程池子的7大参数
 * corePoolSize,//核心线程数
 * maximumPoolSize,//最大线程数
 * keepAliverTime,//空闲线程存活时间
 * unit,//存活时间单位
 * workQueue,//存放任务的阻塞队列
 * Executors.defaultThreadFactory(),//线程工厂(一般不改动)
 * new ThreadPoolExecutor.AbortPolicy() //拒绝策略   队列满了,不处理这该线程,抛出异常.
 * <p>
 * 四种不同的拒绝策略
 * new ThreadPoolExecutor.AbortPolicy() //拒绝策略   队列满了,不处理这该线程,抛出异常.
 * new ThreadPoolExecutor.CallerRunsPolicy() //哪里来的回到哪里 那个线程来的回到哪里,不执行
 * new ThreadPoolExecutor.DiscardPolicy() //队列满了,不会抛出异常,丢弃任务
 * new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了尝试和最早的竞争,也不会抛出异常
 * <p>
 * 核心线程和最大线程数的设置:
 * 注：IO密集型（某大厂实践经验）
 * 核心线程数 = CPU核数 / （1-阻塞系数）
 * 或着
 * CPU密集型：核心线程数 = CPU核数 + 1
 * IO密集型：核心线程数 = CPU核数 * 2
 *
 * <p>
 * @Author Happy
 * @Create 2023/4/23-22:11
 **/
public class TestExecutorsParams {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,//核心线程数
                //5,//最大线程数
                Runtime.getRuntime().availableProcessors(), //最大线程数
                3,//空闲线程存活时间
                TimeUnit.SECONDS,//存活时间单位
                new LinkedBlockingQueue<>(3),//存放任务的阻塞队列
                Executors.defaultThreadFactory(),//线程工厂(一般不改动)
                new ThreadPoolExecutor.AbortPolicy() //拒绝策略   队列满了,不处理这该线程,抛出异常.
                //new ThreadPoolExecutor.CallerRunsPolicy() //哪里来的回到哪里
                //new ThreadPoolExecutor.DiscardPolicy() //队列满了,不会抛出异常,丢弃任务
                //new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了尝试和最早的竞争,也不会抛出异常
        );
        
        
        try {
            //最大承载:Deque + Max
            //超过 RejectedExecutionException
            for (int i = 0; i < 9; i++) {
                //使用了线程池后,使用线程池来创建线程
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "--->OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
