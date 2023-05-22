package com.kuangstudy.multithreading.KS04ThreadStatus;

/**
 * @Describe: u Java提供一个线程调度器来监控程序中启动后进入就绪状态的所有线程，线程调度
 * 器按照优先级决定应该调度哪个线程来执行。
 * u 线程的优先级用数字表示，范围从1~10.
 * u Thread.MIN_PRIORITY = 1;
 * u Thread.MAX_PRIORITY = 10;
 * u Thread.NORM_PRIORITY = 5;
 * <p>
 * <p>
 * u 使用以下方式改变或获取优先级
 * u getPriority() . setPriority(int xxx)
 * @Author Happy
 * @Create 2023/3/26-14:53
 **/
public class TestPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "--->" + Thread.currentThread().getPriority());
        
        MyPriority myPriority = new MyPriority();
        
        Thread thread1 = new Thread(myPriority);
        Thread thread2 = new Thread(myPriority);
        Thread thread3 = new Thread(myPriority);
        Thread thread4 = new Thread(myPriority);
        Thread thread5 = new Thread(myPriority);
        Thread thread6 = new Thread(myPriority);
        Thread thread7 = new Thread(myPriority);
        Thread thread8 = new Thread(myPriority);
        Thread thread9 = new Thread(myPriority);
        Thread thread10 = new Thread(myPriority);
        
        
        thread10.setPriority(10);
        thread10.start();
        
        thread4.setPriority(4);
        thread4.start();
        
        thread5.setPriority(5);
        thread5.start();
        
        thread6.setPriority(6);
        thread6.start();
        
        thread7.setPriority(7);
        thread7.start();
        
        thread8.setPriority(8);
        thread8.start();
        
        thread9.setPriority(9);
        thread9.start();
        
        thread1.setPriority(1);
        thread1.start();
        
        thread2.setPriority(2);
        thread2.start();
        
        thread3.setPriority(3);
        thread3.start();
        
        
    }
}

class MyPriority implements Runnable {
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--->" + Thread.currentThread().getPriority());
    }
}
