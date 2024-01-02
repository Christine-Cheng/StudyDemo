package com.kuangstudy.multithreading.KS08ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Describe: 测试线程池子
 * //1.创建服务,创建线程池
 * //2.执行
 * //3.关闭连接
 *
 * @Author Happy
 * @Create 2023/3/28-7:57
 **/
public class TestPool {
    public static void main(String[] args) {
        //1.创建服务,创建线程池
        //newFixedThreadPool 参数为:线程池大小
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ExecutorService executorService2 = Executors.newFixedThreadPool(20);
    
        //执行
        //executorService.submit(new MyThread());
        //executorService.submit(new MyThread());
        //executorService.submit(new MyThread());
        //executorService.submit(new MyThread());
        
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        
        executorService2.execute(new MyThread());
        executorService2.execute(new MyThread());
        executorService2.execute(new MyThread());
        executorService2.execute(new MyThread());
        
        //2.关闭连接
        executorService.shutdown();
        executorService2.shutdown();
    }
    
}


class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
