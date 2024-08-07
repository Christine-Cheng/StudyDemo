package com.test.soldierstudy.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Happy
 * @create 2021/8/18-12:25
 **/


public class T01_WhatIsThread {
    
    public static void main(String[] args) {
        //new T1().run();
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
        
    }
    
    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }
    
    
}
