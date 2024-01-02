package com.kuangstudy.multithreading.KS05ThreadSynchro;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/3/26-23:01
 **/
public class SafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket2 buyTicket2 = new BuyTicket2();
       
        new Thread(buyTicket2, "老王").start();
        new Thread(buyTicket2, "翠花").start();
        new Thread(buyTicket2, "麻子").start();
        new Thread(buyTicket2, "幺鸡").start();
    }
}

class BuyTicket2 implements Runnable {
    //票
    private int ticketNum = 10;
    
    //外部停止标志位
    boolean flag = true;
    
    @Override
    public void run() {
        //买票
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * synchronized 同步方法,锁的是this
     */
    private synchronized void buy() throws InterruptedException {
        if (ticketNum <= 0) {
            flag = false;
            return;
        }
        
        //模拟延时,放大问题
        Thread.sleep(500);
        
        //打印谁买了票
        System.out.println(Thread.currentThread().getName() + ": 拿到票 " + ticketNum--);
    }
}
