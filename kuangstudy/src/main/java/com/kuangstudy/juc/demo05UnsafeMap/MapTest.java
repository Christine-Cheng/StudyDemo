package com.kuangstudy.juc.demo05UnsafeMap;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/19-23:20
 **/
public class MapTest {
    public static void main(String[] args) {
        
        
        //Map<String, String> map = new HashMap<>();//java.util.ConcurrentModificationException
        //Map<String, String> map =  Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();
        
        
        // 人生如程序，不是选择就是循环，时常的自我总结十分的重要
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
