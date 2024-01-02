package com.kuangstudy.juc.demo18JmmVoliate;

/**
 * @Describe: java memory model
 * 实验:代码验证可见性
 * <p>
 * volatile是不错的可见性机制，但是也不能保证原子性。
 * @Author Happy
 * @Create 2023/5/3-23:48
 **/
//Volatile 用来保证数据的同步，也就是可见性
public class JmmVolatileDemo01 {
    // volatile 不加volatile没有可见性
    // 不加 volatile 就会死循环，这里给大家将主要是为了面试，可以避免指令重排
    private static int num = 0;
    //private volatile static int num = 0;
    
    public static void main(String[] args) throws InterruptedException {//main
        new Thread(() -> {//当num不加volatile的时候,线程1 对主内存的变化是不知道的
            while (num == 0) { //此处不要编写代码,让计算机忙的不可开交
            }
        }).start();
        Thread.sleep(1000);
        num = 1;
        System.out.println(num);
    }
}
