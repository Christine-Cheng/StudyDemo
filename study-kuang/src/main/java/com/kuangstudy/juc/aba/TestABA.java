package com.kuangstudy.juc.aba;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/12-13:00
 **/
public class TestABA {
    
    /**
     * 例子模拟的是A、B两个线程操作一个资源ai，A的执行速度比B的快，在B执行前，A就已经将ai的值改为5之后马上又把ai的值改回为4，但是B不感知，所以最后B就修改成功了。
     *
     * 执行结果:
     * A是否成功将ai的值修改为5：true
     * A是否成功将ai的值修改为4：true
     * B是否成功将ai的值修改为10：true
     * ai最终的值为：10
     *
     * 解决办法:核心思路：加版本号
     * 1.使用AtomicStampedReference解决ABA问题
     * 2.使用AtomicMarkableReference解决ABA问题
     *
     * @param args
     */
    
    public static void main(String[] args) {
        
        testABA();
        
    }
    
    //线程操作资源，原子类ai的初始值为4
    public static final AtomicInteger ai = new AtomicInteger(4);
    
    
    public static void testABA() {
        
        new Thread(() -> {
            //利用CAS将ai的值改成5
            boolean b = ai.compareAndSet(4, 5);
            System.out.println(Thread.currentThread().getName() + "是否成功将ai的值修改为5：" + b);
            //休眠一秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //利用CAS将ai的值改回4
            b = ai.compareAndSet(5, 4);
            System.out.println(Thread.currentThread().getName() + "是否成功将ai的值修改为4：" + b);
        }, "A").start();
        
        new Thread(() -> {
            //模拟此线程执行较慢的情况
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //利用CAS将ai的值从4改为10
            boolean b = ai.compareAndSet(4, 10);
            System.out.println(Thread.currentThread().getName() + "是否成功将ai的值修改为10：" + b);
        }, "B").start();
        
        //等待其他线程完成，为什么是2，因为一个是main线程，一个是后台的GC线程
        while (Thread.activeCount() > 2) {
            Thread.yield(); // 线程让步。顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权
        }
        
        System.out.println("ai最终的值为：" + ai.get());
    }
    
    
}
