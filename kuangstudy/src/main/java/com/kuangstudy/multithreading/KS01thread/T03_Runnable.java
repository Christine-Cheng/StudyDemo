package com.kuangstudy.multithreading.KS01thread;

/**
 * @author Happy
 * @create 2021/8/30-23:53
 **/

/**
 * 实现Runnable接口,重写run()方法,新建thread对象,然后通过线程代理,调用start()方法
 * u 定义MyRunnable类实现Runnable接口
 * u 实现run()方法，编写线程执行体
 * u 创建线程对象，调用start()方法启动线程
 **/
public class T03_Runnable implements Runnable {
    @Override
    public void run() {
        //run方法:线程体
        for (int i = 0; i <= 2000; i++) {
            System.out.println("啦啦啦,你个傻缺,写代码:--->"+i);
        }
    }
    
    //main方法:主线程
    public static void main(String[] args) {
        //创建对象,实现Runnable接口
        T03_Runnable T03=  new T03_Runnable();
        //创建行程对象,通过线程对象启动线程(代理)
        /*Thread thread=new Thread(T03);
        thread.start();*/
    
        new Thread(T03).start();
        
        
        for (int i = 0; i <= 2000; i++) {
            System.out.println("嘿嘿嘿,你个笨蛋在学习--->"+i);
        }
    }
}
