package com.kuangstudy.juc.demo01;

/**
 * @Describe: 基本买票例子
 * 真正的多线程开发，公司中的开发
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 需要包含那些东西：1.属性 2.方法
 * @Author Happy
 * @Create 2023/4/16-22:00
 **/
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        //并发：多个线程操作同一个资源类，把资源类丢入线程
        Ticket ticket = new Ticket();
        
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    
    
    }
}

//资源类 OOP
class Ticket {
    
    //票数
    private int number = 50;
    
    //买票
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票，剩余：" + number);
        }
    }
    
}
