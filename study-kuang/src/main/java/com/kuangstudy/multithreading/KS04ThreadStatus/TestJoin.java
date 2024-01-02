package com.kuangstudy.multithreading.KS04ThreadStatus;

/**
 * @Describe: 测试join方法   类似于插队
 * u Join合并线程，待此线程执行完成后，再执行其他线程，其他线程阻塞
 * u 可以想象成插队
 * @Author Happy
 * @Create 2023/3/25-22:43
 **/
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("线程VIP--->" + i);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        //启动线程
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();
        
        //主线程
        for (int i = 1; i <= 500; i++) {
            if (i == 200) {
                thread.join();//插队
            }
            System.out.println("main--->" + i);
        }
        
    }
}
