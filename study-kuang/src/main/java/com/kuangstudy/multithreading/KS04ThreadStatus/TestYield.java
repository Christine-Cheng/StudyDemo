package com.kuangstudy.multithreading.KS04ThreadStatus;

/**
 * @Describe: 测试礼让线程
 * 礼让不一定成功,看cpu心情.
 * <p>
 * <p>
 * u 礼让线程，让当前正在执行的线程暂停，但不阻塞
 * u 将线程从运行状态转为就绪状态
 * u 让cpu重新调度，礼让不一定成功！看CPU心情
 * @Author Happy
 * @Create 2023/3/25-22:11
 **/
public class TestYield {
    public static void main(String[] args) {
        NyYield yield = new NyYield();
        new Thread(yield, "a").start();
        new Thread(yield, "b").start();
    }
    
    
}

class NyYield implements Runnable {
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程结束");
    }
}
