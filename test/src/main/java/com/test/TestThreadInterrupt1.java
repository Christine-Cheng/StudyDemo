package com.test;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-01-11 10:11:24
 **/
public class TestThreadInterrupt1 {
    public static void main(String[] args) {
        
        testInterrupt2();
    }
    
    
    public static void testInterrupt1() {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                
                try {
                    // 在线程中进行一些操作，这里用 sleep 模拟耗时操作
                    System.out.println("Thread is running...");
                    Thread.sleep(5000); // 休眠5秒钟
                    System.out.println("Thread completed its task.");
                } catch (InterruptedException e) {
                    // 捕捉中断异常
                    System.out.println("Thread is interrupted!");
                    e.printStackTrace();
                }
            }
        });
        
        myThread.start();
        System.out.println("启动线程");
        
        try {
            // 主线程休眠一段时间
            Thread.sleep(2000);
            System.out.println("主线程休眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 中断线程
        myThread.interrupt();
        System.out.println("线程中断");
    }
    
    
    public static void testInterrupt2() {
        // 创建并启动一个线程
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.interrupted()) {
                        // 在线程中进行一些操作，这里用 sleep 模拟耗时操作
                        System.out.println("当前休眠线程" + Thread.currentThread().getName());
                        System.out.println("Thread is running...");
                        Thread.sleep(1000); // 休眠1秒钟
                    }
                } catch (InterruptedException e) {
                    // 捕捉中断异常
                    System.out.println("捕捉中断异常Thread is interrupted!");
                    e.printStackTrace();
                }
            }
        });
        
        // 启动线程
        myThread.start();
        
        // 主线程休眠一段时间
        try {
            // 休眠3秒钟
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 中断线程
        myThread.interrupt();
    }
    
}
