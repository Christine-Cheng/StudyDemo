package com.kuangstudy.multithreading.KS01thread;

/*
* 创建线程方式01:继承Thread类,重新run()方法,调用start()开启线程
*
* 结论:
* 调用run(),主线程执行run()方法,结束后执行主线程的代码
* 调用start()方法,主线程执行start(),子线程执行run(),多条路径交替执行
* 线程开启不一定立即执行,由cpu调度执行.
*
* */
/**
 * u 自定义线程类继承Thread类
 * u 重写run()方法，编写线程执行体
 * u 创建线程对象，调用start()方法启动线程
 */

/**
 * @author Happy
 * @create 2021/8/20-23:06
 **/
public class T01_Thread extends Thread{
    @Override
    public void run() {
        //run方法:线程体
        for (int i = 0; i <= 2000; i++) {
            System.out.println("啦啦啦,你个傻缺,写代码:--->"+i);
        }
    }
    
    //main方法:主线程
    public static void main(String[] args) {
        //创建线程
        T01_Thread T01=  new T01_Thread();
        //调用start方法
        T01.start();
        //调用run()
        //T01.run();
        
    
        for (int i = 0; i <= 2000; i++) {
            System.out.println("嘿嘿嘿,你个笨蛋在学习对线程--->"+i);
        }
    }
    
    
    
}
