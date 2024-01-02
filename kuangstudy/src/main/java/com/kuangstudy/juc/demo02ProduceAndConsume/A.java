package com.kuangstudy.juc.demo02ProduceAndConsume;

/**
 * @Describe: 生产者和消费者 synchroinzed 版
 *
 * 线程之间的通信问题: 生产者和消费者问题. 等待唤醒,通知唤醒
 *
 * ----------------------------------------------------------------
 *
 * 题目：现在两个线程，可以操作初始值为0的一个变量
 * 实现一个线程对该变量 + 1，一个线程对该变量 -1
 * 实现交替10次
 *
 * 诀窍：
 * 1. 高内聚低耦合的前提下，线程操作资源类
 * 2. 判断 、干活、通知
 *
 * ----------------------------------------------------------------
 *
 * 线程交替执行  A  B 操作同一个变量  num=0
 * A num+1
 * B num-1
 *
 * ----------------------------------------------------------------
 *
 * 但是当多余2个线程的时候会出现问题,此时的if判断不可行,需要改为while  看B.java
 * 因为 wait()  notify() 会出现 虚假唤醒的情况
 *
 * @Author Happy
 * @Create 2023/4/17-10:59
 **/
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
    
    
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    
    
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

//生产者消费者的线程通讯问题: 1判断等待,2业务,3通知 三部曲
class Data {//数字 资源类
    private int number = 0;
    
    
    //加一
    public synchronized void increase() throws InterruptedException {
        if (number != 0) {
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
        if (number == 0) {
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "--->" + number);
        //通知其他线程,我-1完毕了
        this.notifyAll();
    }
    
}

/**
 * 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断不能用if，只能用while）
 * A--->1
 * B--->0
 * A--->1
 * B--->0
 * A--->1
 * B--->0
 * A--->1
 * B--->0
 * A--->1
 * B--->0
 * C--->1
 * A--->2
 * C--->3
 * B--->2
 * B--->1
 * B--->0
 * C--->1
 * A--->2
 * C--->3
 * B--->2
 * B--->1
 * D--->0
 * C--->1
 * A--->2
 * C--->3
 * D--->2
 * D--->1
 * D--->0
 * C--->1
 * A--->2
 * C--->3
 * D--->2
 * D--->1
 * D--->0
 * C--->1
 * A--->2
 * C--->3
 * D--->2
 * D--->1
 * D--->0
 */
