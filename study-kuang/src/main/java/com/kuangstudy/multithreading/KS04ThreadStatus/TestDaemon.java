package com.kuangstudy.multithreading.KS04ThreadStatus;

/**
 * @Describe: 测试守护线程
 * <p>
 * <p>
 * 守护(daemon)线程
 * u 线程分为用户线程和守护线程
 * * u 虚拟机必须确保用户线程执行完毕
 * * u 虚拟机不用等待守护线程执行完毕
 * * u 如,后台记录操作日志,监控内存,垃圾回收等待..
 * @Author Happy
 * @Create 2023/3/26-15:39
 **/
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();
    
        Thread daemon = new Thread(god);
        daemon.setDaemon(true);//默认是false表示用户线程,一般的线程都是用户线程
        daemon.start();//守护线程启动
        
        new Thread(you).start();//用户线程启动
    }
}

//设置为守护线程
class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("God Bless You");
        }
    }
}

//设置为被守护的
class You implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都活得开心");
        }
        System.out.println("=============GoodBye World!============");
    }
}
