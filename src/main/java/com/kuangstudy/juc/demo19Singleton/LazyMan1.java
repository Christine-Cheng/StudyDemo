package com.kuangstudy.juc.demo19Singleton;

/**
 * @Describe: 正常的 懒汉式单例：
 * @Author Happy
 * @Create 2023/4/24-8:02
 **/
public class LazyMan1 {
    private LazyMan1() {
        System.out.println(Thread.currentThread().getName() + "Start");
    }
    
    private static LazyMan1 lazyMan;
    
    //普通懒汉式这样写在并发下不安全
    public static LazyMan1 getInstance() {
        if (lazyMan == null) {
            lazyMan = new LazyMan1();
        }
        return lazyMan;
    }
    
    
    //普通单例模式 测试并发环境,出现有时候成功是时候失败
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan1.getInstance();
            }).start();
        }
    }
    
}
