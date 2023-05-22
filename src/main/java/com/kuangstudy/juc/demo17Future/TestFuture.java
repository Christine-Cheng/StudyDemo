package com.kuangstudy.juc.demo17Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Describe: 异步调用 类比ajax
 * 利用Future的实现类 CompletableFuture
 * 执行 成功回调 异步回调 失败回调
 * @Author Happy
 * @Create 2023/4/26-8:38
 **/
public class TestFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
    
        /**
         * 执行结果:
         * //测试CompletableFuture,Future异步执行不占用程序执行时间
         * //ForkJoinPool.commonPool-worker-9--->runAsync
         * 先打印CompletableFuture外面的句子,后执行里面的.
         *
         */
        //发起一个请求
        //异步任务在发起的时候不会占用程序的时间,进行休眠2秒,观察主线程是否执行
        /*没有返回值的异步回调*/
        /*CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            //try {
            //    TimeUnit.SECONDS.sleep(2);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            System.out.println(Thread.currentThread().getName() + "--->runAsync");
        });
        System.out.println("测试CompletableFuture,Future异步执行不占用程序执行时间");
        completableFuture.get();//获取阻塞执行结果*/
    
    
        /**
         * 执行结果:
         * T--->null
         * U--->java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
         * 22233
         */
        //有返回值的supplyAsync异步回调
        // 类似 ajax 成功返回异步回调值,失败返回错误信息.
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"--->supplyAsync===>Integer");
            int i = 10 / 0;
            return 1024;
        });
        //integerCompletableFuture.get();
        System.out.println(integerCompletableFuture.whenComplete((T, U) -> {
            System.out.println("T--->" + T);//T是正常的返回结果
            System.out.println("U--->" + U);//U是错误信息
        }).exceptionally((e) -> {
            e.printStackTrace();
            return 22233;//可以获取错误的返回结果
        }).get());
        
        
    }
    
    
}
