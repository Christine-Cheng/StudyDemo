package com.kuangstudy.multithreading.KS05ThreadSynchro;

/**
 * @Describe: 不安全买票的案列1
 * 线程不安全,出现负数.
 * @Author Happy
 * @Create 2023/3/26-17:27
 **/
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket, "老王").start();
        new Thread(buyTicket, "翠花").start();
        new Thread(buyTicket, "麻子").start();
        new Thread(buyTicket, "花鸡").start();
    }
}

class BuyTicket implements Runnable {
    //票
    private int tickets = 10;
    
    //外部停止标志位
    boolean flag = true;
    
    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    //买票
    private void buy() throws InterruptedException {
        if (tickets <= 0) {
            flag = false;
            return;
        }
        
        //模拟延时,放大问题
        Thread.sleep(1000);
        
        //打印谁买了票
        System.out.println(Thread.currentThread().getName() + "拿到" + tickets--);
    }
}
