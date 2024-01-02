package com.kuangstudy.juc.demo19Singleton;

import java.lang.reflect.Constructor;

/**
 * @Describe:  反射破坏单例模式: 三重检锁+volatile
 * 双重检测+锁 单例模式(DCL单例) 只有增加一个volatile关键字来避免指令重排,完成原子性操作
 * @Author Happy
 * @Create 2023/5/5-13:34
 **/
public class LazyMan4Reflect2 {
    
    private static volatile LazyMan4Reflect2 lazyMan;
    
    //第三重检测锁:构造器检测锁
    private LazyMan4Reflect2() {
        synchronized (LazyMan4Reflect2.class) {
            if (lazyMan != null) {
                throw new RuntimeException("不要试图用反射破坏单例模式");
                //设置一个报错,但是一上来就直接用反射创建对象,我们的判断就不生效了
            }
        }
    }
    
    //双重检测+锁+原子性操作的 懒汉式单例 (DCL单例)
    private static LazyMan4Reflect2 getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan4Reflect2.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan4Reflect2();//不是一个原子性操作
                }
            }
        }
        return lazyMan;
    }
    
    
    /**
     * 实验2.1: 构造器加第三重检测锁 一定程度能解决 反射破坏单例模式;
     * 结果:
     * Exception in thread "main" java.lang.reflect.InvocationTargetException
     * Caused by: java.lang.RuntimeException: 不要试图用反射破坏单例模式
     * at com.kuangstudy.juc.demo20Singleton.LazyMan4Reflect2.<init>(LazyMan4Reflect2.java:20)
     * ... 5 more
     * <p>
     * 但是,如果说一上来就直接用反射创建对象,我们的判断就不生效了
     *
     * @param args
     */
    //public static void main(String[] args) throws Exception{
    //
    //    LazyMan4Reflect2 lazyMan1=LazyMan4Reflect2.getInstance();
    //    Constructor<LazyMan4Reflect2> lazyManDeclConst = LazyMan4Reflect2.class.getDeclaredConstructor(null);
    //    LazyMan4Reflect2 lazyMan2 = lazyManDeclConst.newInstance();
    //
    //    System.out.println(lazyMan1);
    //    System.out.println(lazyMan2);
    //}
    
    
    
    /**
     * 实验2.2: 构造器加第三重检测锁 一定程度能解决 反射破坏单例模式;
     * 但是,一上来就直接用反射创建对象,我们的判断就不生效了
     * 结果:
     * com.kuangstudy.juc.demo20Singleton.LazyMan4Reflect2@5b2133b1
     * com.kuangstudy.juc.demo20Singleton.LazyMan4Reflect2@72ea2f77
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Constructor<LazyMan4Reflect2> lazyManDeclConst = LazyMan4Reflect2.class.getDeclaredConstructor(null);
        lazyManDeclConst.setAccessible(true);
        LazyMan4Reflect2 lazyman1 = lazyManDeclConst.newInstance();
        LazyMan4Reflect2 lazyman2 = lazyManDeclConst.newInstance();
        
        System.out.println(lazyman1);
        System.out.println(lazyman2);
    }
    
}
