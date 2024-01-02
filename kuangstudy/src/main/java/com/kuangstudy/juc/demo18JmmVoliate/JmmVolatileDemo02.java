package com.kuangstudy.juc.demo18JmmVoliate;

/**
 * @Describe: 验证 volatile 不保证原子性
 * <p>
 * 原子性: 不可分割，完整性，也就是某个线程正在做某个具体的业务的时候，中间不可以被加塞或者被分割，需
 * 要整体完整，要么同时成功，要么同时失败。
 * <p>
 * 线程A在执行任务的时候,不能被打扰,也不能被分割.要么同时成功,要么同时失败.
 * @Author Happy
 * @Create 2023/5/4-0:28
 **/
public class JmmVolatileDemo02 {
    private static int num = 0;
    
    private static void add() {
        num++;//不是一个原子性操作
    }
    
    //不论是否加上 volatile 都不可以每次都达到预期值
    //private static int num = 0;
    
    
    //synchronize可以满足原子性
    //private synchronized static void add() {
    //    num++;
    //}
    
    
    public static void main(String[] args) {
        /**
         * 理论上结果是20000
         *
         * 但是实际上,会收到其他线程的影响,很难达到2万
         *
         * 而private static int num = 0;不论是否加上 volatile 都不可以每次都达到预期值
         * 并且 num++;//不是一个原子性操作
         *
         * 在add()上加synchronize可以满足原子性操作和线程安全
         *
         * num++ 在多线程下是非线程安全的,而如何在不用synchronize,lock()的前提下保证原子性呢?
         * 用Atomic包下面的原子类 + volatile 保证原子性,详见下一个demo
         */
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    add();
                }
            }).start();
        }
        // 需要等待上面20个线程都全部计算完毕，看最终结果
        while (Thread.activeCount() > 2) { // 默认一个 main线程 一个 gc 线程
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
