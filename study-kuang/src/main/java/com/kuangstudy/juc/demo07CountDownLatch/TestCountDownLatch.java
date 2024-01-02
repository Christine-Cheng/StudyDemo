package com.kuangstudy.juc.demo07CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Describe: 辅助类: 计数器(减法)
 * 允许一个或多个线程等待直到在其他线程中执行的一组操作完成的同步辅助。
 * <p>
 * CountDownLatch用给定的计数初始化。
 * await方法阻塞，直到由于countDown()方法的调用而导致当前计数达到零，之后所有等待线程被释放，并且任何后续的await调用立即返回。
 * 这是一个一次性的现象 - 计数无法重置。 如果您需要重置计数的版本，请考虑使用CyclicBarrier 。
 * @Author Happy
 * @Create 2023/4/21-0:00
 **/
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        //有六个线程全部跑完再去关门
        
        //总数是6 必须要执行的任务的时候吗,再使用
        CountDownLatch countDownLatch = new CountDownLatch(6);
        
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "Go Out");
                countDownLatch.countDown();//数量减1
            }, String.valueOf(i)).start();
        }
        //阻塞等待计数器归零
        countDownLatch.await();//等待计数器归零,然后再向下执行
        System.out.println("Close Door");
    }
}
