package com.kuangstudy.multithreading.KS01thread;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/3/24-14:37
 **/
public class T06_Runnable implements Runnable {
    private static String winner = null;
    
    public static void main(String[] args) {
        T06_Runnable rance = new T06_Runnable();
        new Thread(rance, "兔子").start();
        new Thread(rance, "乌龟").start();
        
    }
    
    
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (Thread.currentThread().getName().equals("兔子") && i % 5 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean flag = gameOver(i);
            if (flag) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "--->跑路步数--->" + i);
        }
    }
    
    public Boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        }
        {
            if (steps == 100) {
                winner = Thread.currentThread().getName();
                System.out.println("胜利者是==" + winner);
            }
        }
        return false;
    }
}
