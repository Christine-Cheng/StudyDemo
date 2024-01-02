package com.kuangstudy.multithreading.KS04ThreadStatus;

/**
 * @Describe: 测试线程的停止
 * <p>
 * 1.建议线程正常停止--->利用次数,不建议死循环
 * 2.建议使用标志位--->设置一个标志位
 * 3.不要使用stop或者destroy或者JDK不建议使用的方法.
 * @Author Happy
 * @Create 2023/3/25-11:09
 **/
public class TestStop implements Runnable {
    //定义一个标志位置
    private boolean flag = true;
    
    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        
        for (int i = 1; i <= 1000; i++) {
            System.out.println("main循环次数--->" + i);//主线程跑950次的时候,结束子线程
            if (i == 950) {
                testStop.stop();
                System.out.println(Thread.currentThread().getName() + "线程停止");
            }
        }
    }
    
    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("子线程run...thread" + i++);
        }
    }
    
    public void stop() {
        this.flag = false;
    }
}
