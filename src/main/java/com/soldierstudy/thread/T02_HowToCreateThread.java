package com.soldierstudy.thread;

/*
* thread的三种启动方式：
* 用thread 然后start
* 用runnable 然后start
* 用线程池executors.newCacheThread
* */

/**
 * @author Happy
 * @create 2021/8/18-12:47
 **/
public class T02_HowToCreateThread {
    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(()->{
            System.out.println("hello lambda");
        }).start();
        
    }
    
    
}

class MyThread extends Thread {
    @Override
    public void run(){
        System.out.println("hello myThread");
    }


}


class MyRun implements Runnable{
    @Override
    public void run() {
        System.out.println("hello myRun");
    }
}
