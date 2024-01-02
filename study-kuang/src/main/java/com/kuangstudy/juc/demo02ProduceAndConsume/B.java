package com.kuangstudy.juc.demo02ProduceAndConsume;

/**
 * @Describe: 生产者和消费者 synchroinzed 版
 * <p>
 * 线程之间的通信问题: 生产者和消费者问题. 等待唤醒,通知唤醒
 * <p>
 * ----------------------------------------------------------------
 * <p>
 * 题目：现在四个线程，可以操作初始值为0的一个变量
 * 实现两个线程对该变量 + 1，两个线程对该变量 -1
 * 实现交替10次
 * <p>
 * ----------------------------------------------------------------
 * <p>
 * 诀窍：
 * 1. 高内聚低耦合的前提下，线程操作资源类
 * 2. 判断 、干活、通知
 * 3. 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断不能用if，只能用while）
 * @Author Happy
 * @Create 2023/4/17-14:18
 **/
public class B {
    public static void main(String[] args) {
        Data2 data2 = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

//生产者消费者的线程通讯问题: 1判断等待,2业务,3通知 三部曲
class Data2 {//数字 资源类
    private int number = 0;
    
    
    //加一
    public synchronized void increase() throws InterruptedException {
        while (number != 0) {
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "--->" + number);
        //通知其他线程,我+1完毕了
        this.notifyAll();
    }
    
    //减一
    public synchronized void decrease() throws InterruptedException {
        while (number == 0) {
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "--->" + number);
        //通知其他线程,我-1完毕了
        this.notifyAll();
    }
    
}
