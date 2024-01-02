package com.kuangstudy.juc.demo16ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/24-7:36
 **/
public class TestForkJoin {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test1(); //普通求和时间      time: 5843
        test2();//ForkJoin求和时间   time: 4307
        //test3();//并行流求和时间       time: 126
    }
    
    //普通线程实现
    public static void test1() {
        Long x = 0L;
        Long y = 10_0000_0000L;
        long startTime = System.currentTimeMillis();
        for (Long i = 0L; i <= y; i++) {
            x += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("invoke = " + x + " time: " + (endTime - startTime));
    }
    
    
    // forkjoin这个框架针对的是大任务执行，效率才会明显的看出来有提升，于是我把总数调大到1亿。
    public static void test2() throws ExecutionException, InterruptedException {
        //ForkJoin实现
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();//实现ForkJoin 就必须有ForkJoinPool的支持
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0001L);//参数为起始值与结束值
        ForkJoinTask<Long> result = forkJoinPool.submit(task);//submit()是异步提交  execute()是同步执行
        Long sum = result.get();
        long end = System.currentTimeMillis();
        System.out.println("invoke = " + sum + " time: " + (end - start));
    }
    
    
    //Java 8 并行流的实现
    public static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("invoke = " + sum + " time: " + (end - start));
    }
    
}
