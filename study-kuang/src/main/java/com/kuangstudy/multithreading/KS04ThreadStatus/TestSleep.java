package com.kuangstudy.multithreading.KS04ThreadStatus;

/**
 * @Describe: 模拟网络延时 可以方放大问题的发生的可能性.
 * @Author Happy
 * @Create 2023/3/25-12:46
 **/
//多个线程同时操作一个对象,以买火车票为例子
//发现问题:多个线程操作同一个资源,线程不安全,数据紊乱---->在并发中解决
public class TestSleep implements Runnable {
    //票数
    private Integer ticketNum = 10;
    
    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                break;
            }
            try {
                Thread.sleep(100);
    
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第:" + ticketNum-- + "张票");
        }
    }
    
    public static void main(String[] args) {
        TestSleep testSleep = new TestSleep();
        new Thread(testSleep, "小舞").start();
        new Thread(testSleep, "小三").start();
        new Thread(testSleep, "小奥").start();
        new Thread(testSleep, "黄牛党").start();
    }
}
