package com.kuangstudy.juc.demo19Singleton;

/**
 * @Describe: 饿汉式单例
 * 单例模式的一个重要的特点 就是 构造方法 是私有的
 * <p>
 * 饿汉式是最简单的单例模式的写法，保证了线程的安全，在很长的时间里，我都是饿汉模式来完成单例
 * 的，因为够简单
 * 在Hungry类中，我定义了四个byte数组，当代码一运行，这四个数组就被初始化，并且放入内存了，如
 * 果长时间没有用到getInstance方法，不需要Hungry类的对象，这不是一种浪费吗？我希望的是 只有用
 * 到了 getInstance方法，才会去初始化单例类，才会加载单例类中的数据。所以就有了 第二种单例模
 * 式：懒汉式。
 * @Author Happy
 * @Create 2023/4/24-8:02
 **/
public class Hungry1 {
    private byte[] data1 = new byte[1024];
    private byte[] data2 = new byte[1024];
    private byte[] data3 = new byte[1024];
    private byte[] data4 = new byte[1024];
    
    private Hungry1() {
        //单例模式的一个重要的特点 就是 构造方法 是私有的
    }
    
    private final static Hungry1 hungry = new Hungry1();
    
    public static Hungry1 getInstance() {
        return hungry;
    }
}
