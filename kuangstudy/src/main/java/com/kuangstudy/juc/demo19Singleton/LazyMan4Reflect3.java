package com.kuangstudy.juc.demo19Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @Describe: 反射破坏单例模式: 双重检测锁 + volatile + 第三重检测锁+加上标记位判断
 * 双重检测+锁 单例模式(DCL单例) 只有增加一个volatile关键字来避免指令重排,完成原子性操作
 * @Author Happy
 * @Create 2023/5/5-14:22
 **/
public class LazyMan4Reflect3 {
    private static boolean flag = false;
    private static volatile LazyMan4Reflect3 lazyMan;
    
    //第三重检测锁+加上标记位判断 flag
    private LazyMan4Reflect3() {
        synchronized (LazyMan4Reflect3.class) {
            //设置一个报错+标志位判断,一定程度上可以解决反射不被破坏
            //如果猜到标志位的名称那么就可以被修改了.
            if (flag == false) {
                flag = true;
            } else {
                throw new RuntimeException("不要试图用反射破坏单例模式");
            }
        }
    }
    
    //双重检测+锁+原子性操作的 懒汉式单例 (DCL单例)
    private static LazyMan4Reflect3 getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan4Reflect3.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan4Reflect3();//不是一个原子性操作
                }
            }
        }
        return lazyMan;
    }
    
    /**
     * 实验:
     * 实验2.3.1: 构造器加第三重检测锁+标志位判断 一定程度能解决 反射破坏单例模式;
     * 如果猜到标志位的名称那么就可以被修改了.
     * 结果:
     * Exception in thread "main" java.lang.reflect.InvocationTargetException
     * Caused by: java.lang.RuntimeException: 不要试图用反射破坏单例模式
     * 	at com.kuangstudy.juc.demo20Singleton.LazyMan4Reflect3.<init>(LazyMan4Reflect3.java:23)
     * 	... 5 more
     * @param args
     * @throws Exception
     */
    //public static void main(String[] args) throws Exception {
    //    Constructor<LazyMan4Reflect3> lazyManDeclConst = LazyMan4Reflect3.class.getDeclaredConstructor(null);
    //    lazyManDeclConst.setAccessible(true);
    //
    //    LazyMan4Reflect3 lazyman1 = lazyManDeclConst.newInstance();
    //    LazyMan4Reflect3 lazyman2 = lazyManDeclConst.newInstance();
    //
    //    System.out.println(lazyman1);
    //    System.out.println(lazyman2);
    //}
    
    /**
     * 实验:
     * 实验2.3.1: 构造器加第三重检测锁+标志位判断 一定程度能解决 反射破坏单例模式;
     * 如果猜到标志位的名称那么就可以被修改了.
     * 直接修改字段值
     * 结果:
     * com.kuangstudy.juc.demo20Singleton.LazyMan4Reflect3@72ea2f77
     * com.kuangstudy.juc.demo20Singleton.LazyMan4Reflect3@33c7353a
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
    
    
        Constructor<LazyMan4Reflect3> lazyManDeclConst = LazyMan4Reflect3.class.getDeclaredConstructor(null);
        lazyManDeclConst.setAccessible(true);
        //反射修改了字段值,破坏了构造器的 第三重检测锁+加上标记位判断
        Field flag = LazyMan4Reflect3.class.getDeclaredField("flag");
        flag.setAccessible(true);
        
        LazyMan4Reflect3 lazyman1 = lazyManDeclConst.newInstance();
        flag.set(lazyman1,false);//修改回默认的值
        
        LazyMan4Reflect3 lazyman2 = lazyManDeclConst.newInstance();
        
        System.out.println(lazyman1);
        System.out.println(lazyman2);
    }
    
}
