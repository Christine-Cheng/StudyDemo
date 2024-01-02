package com.kuangstudy.multithreading.KS05ThreadSynchro;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe: 线程不安全
 * @Author Happy
 * @Create 2023/3/26-22:40
 **/
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
