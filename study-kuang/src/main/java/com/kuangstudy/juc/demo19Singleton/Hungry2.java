package com.kuangstudy.juc.demo19Singleton;

/**
 * @Describe: 静态内部类
 * <p>
 * 这种方式是第一种饿汉式的改进版本，同样也是在类中定义static变量的对象，并且直接初始化，不
 * 过是移到了静态内部类中，十分巧妙。既保证了线程的安全性，同时又满足了懒加载。
 * @Author Happy
 * @Create 2023/5/5-0:27
 **/
public class Hungry2 {
    private Hungry2() {
    }
    
    public static Hungry2 getInstance() {
        return InnerClass.hungry;
    }
    
    private static class InnerClass {
        private static final Hungry2 hungry = new Hungry2();
    }
}
