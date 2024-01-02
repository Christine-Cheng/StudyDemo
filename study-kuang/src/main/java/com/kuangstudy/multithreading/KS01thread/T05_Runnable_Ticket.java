package com.kuangstudy.multithreading.KS01thread;

/**
 * @author Happy
 * @create 2021/9/8-0:02
 **/

//多个线程同时操作一个对象,以买火车票为例子
//发现问题:多个线程操作同一个资源,线程不安全,数据紊乱--->在并发中解决
public class T05_Runnable_Ticket implements Runnable {
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
        T05_Runnable_Ticket runnableTicket = new T05_Runnable_Ticket();
        new Thread(runnableTicket, "小舞").start();
        new Thread(runnableTicket, "小三").start();
        new Thread(runnableTicket, "小奥").start();
        new Thread(runnableTicket, "黄牛党").start();
    }
}
