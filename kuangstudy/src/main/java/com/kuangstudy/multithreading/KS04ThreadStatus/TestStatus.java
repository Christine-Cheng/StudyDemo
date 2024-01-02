package com.kuangstudy.multithreading.KS04ThreadStatus;

/**
 * @Describe: 观察线程状态 getState()
 *
 * @Author Happy
 * @Create 2023/3/26-10:14
 **/
public class TestStatus {
    public static void main(String[] args) {
        Thread thread=new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("********************");
        });
        
        //观察线程的状态
        Thread.State state = thread.getState();
        System.out.println(state);
        
        //观察启动后
        thread.start();
        state=thread.getState();
        System.out.println(state);//Run
    
        while (state != Thread.State.TERMINATED) {//只要线程不中止,就一直输出状态
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state=thread.getState();
            System.out.println(state);
        }
    
        /**
         * java.lang.IllegalThreadStateException
         * 	at java.lang.Thread.start(Thread.java:710)
         */
        //thread.start();//死亡之后的线程不能启动.
    }
}
