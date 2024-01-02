package com.kuangstudy.multithreading.KS05ThreadSynchro;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Describe: 测试JUC 安全类型的集合
 * @Author Happy
 * @Create 2023/3/27-10:27
 **/
public class TestJUC {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                copyOnWriteArrayList.add(Thread.currentThread().getName());
            }).start();
        }
    
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        System.out.println(copyOnWriteArrayList.size());
        
    }
}
