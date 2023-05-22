package com.kuangstudy.multithreading.KS04ThreadStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Describe: 模拟倒计时
 * @Author Happy
 * @Create 2023/3/25-14:24
 **/
public class TestSleep2 {
    public static void main(String[] args) {
        new TestSleep2().timeCountDown();
        try {
            //打印系统当前时间
            Date currentTime = new Date(System.currentTimeMillis());//系统当前时间
            while (true) {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("YYYY-mm-dd HH:mm:ss").format(currentTime));
                currentTime = new Date(System.currentTimeMillis());//系统当前时间
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    public void timeCountDown() {
        try {
            int num = 10;
            while (true) {
                System.out.println(num--);
                Thread.sleep(1000);
                if (num <= 0) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
