package com.kuangstudy.juc.demo19Singleton;

import java.lang.reflect.Constructor;

/**
 * @Describe: 反射破坏单例模式:双重检测锁+volatile
 * <p>
 * 双重检测+锁 单例模式(DCL单例) 只有增加一个volatile关键字来避免指令重排,完成原子性操作
 * @Author Happy
 * @Create 2023/5/5-9:36
 **/
public class LazyMan4Reflect {
    
    
    private LazyMan4Reflect() {
        System.out.println(Thread.currentThread().getName() + "--->Start");
    }
    
    //单例模式只是在上面DCL单例模式增加一个volatile关键字来避免指令重排
    private static volatile LazyMan4Reflect lazyMan;
    
    
    //双重检测+锁+原子性操作的 懒汉式单例 (DCL单例)
    public static LazyMan4Reflect getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan4Reflect.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan4Reflect();//不是一个原子性操作
                }
            }
        }
        return lazyMan;
    }
    
    /**
     * 实验1:反射破坏单例
     * 结果:
     * main--->Start
     * main--->Start
     * com.kuangstudy.juc.demo20Singleton.LazyMan4Reflect@5b2133b1
     * com.kuangstudy.juc.demo20Singleton.LazyMan4Reflect@72ea2f77
     * 不同的hashcode
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        LazyMan4Reflect lazyMan1 = LazyMan4Reflect.getInstance();
        
        Constructor<LazyMan4Reflect> lazyManDeclConst = LazyMan4Reflect.class.getDeclaredConstructor(null);
        lazyManDeclConst.setAccessible(true);
        LazyMan4Reflect lazyMan2 = lazyManDeclConst.newInstance();
        
        System.out.println(lazyMan1);
        System.out.println(lazyMan2);
    }
}
