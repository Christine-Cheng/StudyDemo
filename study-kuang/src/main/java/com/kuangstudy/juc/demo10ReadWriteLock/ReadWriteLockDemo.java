package com.kuangstudy.juc.demo10ReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Describe: 读写锁
 * 独占和共享
 * @Author Happy
 * @Create 2023/4/21-16:26
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();
        //MyCache myCache = new MyCache();
        //写
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }
        
        
        //读
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }
        
        /**
         * 结果:
         * 0写入0
         * 0写入OK
         * 1写入1
         * 1写入OK
         * 2写入2
         * 2写入OK
         * 3写入3
         * 3写入OK
         * 4写入4
         * 4写入OK
         * 0读取0
         * 0读取OK
         * 4读取4
         * 4读取OK
         * 1读取1
         * 1读取OK
         * 2读取2
         * 2读取OK
         * 3读取3
         * 3读取OK
         */
        
    }
    
    
}

class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    //读写锁,控制粒度更细
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    //存,写入的时候,只希望同时只有一个线程写
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    
    
    //读取,多人都可以读取
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
    
    
}

// 测试发现问题: 写入的时候，还没写入完成，会存在其他的写入！造成问题
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + " 写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " 写入成功!");
    }
    
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + " 读取" + key);
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + " 读取结果：" + result);
    }
    
    /**
     * 结果
     * 0 写入0
     * 2 写入2
     * 3 写入3
     * 4 写入4
     * 4 写入成功!
     * 1 写入1
     * 1 写入成功!
     * 0 读取0
     * 1 读取1
     * 1 读取结果：1
     * 3 写入成功!
     * 2 写入成功!
     * 0 写入成功!
     * 3 读取3
     * 3 读取结果：3
     * 2 读取2
     * 0 读取结果：0
     * 2 读取结果：2
     * 4 读取4
     * 4 读取结果：4
     */
}
