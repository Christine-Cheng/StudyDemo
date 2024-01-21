package com.test.thread;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-01-11 10:48:17
 **/
public class TestThreadInterrupt2 {
    public static void main(String[] args) {
        Thread threadB = new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    System.out.println("threadB going to be interrupted");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // How to find name or any contextual info of threadA here?
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread threadA = new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                }
                System.out.println("I am going to interrupt, catch me if you can");
                threadB.interrupt();
            }
        });
        
        threadB.start();
        threadA.start();
    }
}
