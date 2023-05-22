package com.kuangstudy.juc.demo08CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Describe: 允许一组线程全部等待彼此达到共同屏障点的同步辅助.循环阻塞在涉及固定大小的线程方的程序中很有用,这些线程必须偶尔等待彼此.
 * 屏障被称为循环,因为它可以在等待的线程被释放之后重新使用.
 * CyclicBarrier支持一个可选的Runnable命令,每个屏障点运行一次,在派对中的最后一个线程到达之后,但在任何线程释放之前.
 * 在任何一方继续进行之前,此屏障操作对更新共享状态很有用.
 * @Author Happy
 * @Create 2023/4/21-15:20
 **/
public class TestCyclicBarrier {
    public static void main(String[] args) {
        
        /**
         * 集齐7个龙珠召唤神龙
         *
         * 注意如果说计数没有满的时候会阻塞.停止不动
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功!");
        });
        
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集了第" + temp + "个龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
            
        }
        
    }
}
