package com.kuangstudy.juc.demo21Lock;

import java.util.concurrent.TimeUnit;

/**
 * @Describe: 死锁:死锁是指两个或两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象，若无外力干
 * 涉那它们都将无法推进下去.
 * <p></>
 * 产生死锁主要原因：
 * 1、系统资源不足
 * 2、进程运行推进的顺序不合适
 * 3、资源分配不当
 * <p></>
 * 拓展java自带工具操作：
 * 1、查看JDK目录的bin目录
 * 2、使用 jps -l 命令定位进程号
 * 3、使用 jstack 进程号找到死锁查看
 * @Author Happy
 * @Create 2023/5/6-9:56
 **/
public class DeadLockDemo {
    
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        //线程T1持有 lockA 又要试图获取 lockB
        //线程T2持有 lockB 又要试图获取 lockA
        new Thread(new HoldLockThread(lockA, lockB), "T1").start();
        new Thread(new HoldLockThread(lockB, lockA), "T2").start();
    }
}


class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;
    
    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    
    
    @Override
    public void run() {
        /*
         * 线程T1持有 lockA 又要试图获取 lockB
         * 线程T2持有 lockB 又要试图获取 lockA
         * */
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "---> Lock:" + lockA + " ---> get:" + lockB);
            
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "---> Lock:" + lockB + " ---> get:" + lockA);
            }
        }
        
    }
}

/**
 * 排查死锁:
 * 拓展java自带工具操作：
 * 1、查看JDK目录的bin目录
 * 2、使用 jps -l 命令定位进程号
 * 3、使用 jstack 进程号找到死锁查看
 *
 * 结果:
 * //Java stack information for the threads listed above:
 * //===================================================
 * //"T2":
 * //        at com.kuangstudy.juc.demo21Lock.HoldLockThread.run(DeadLockDemo.java:58)
 * //        - waiting to lock <0x000000066bc064c8> (a java.lang.String)
 * //        - locked <0x000000066bc06500> (a java.lang.String)
 * //        at java.lang.Thread.run(Thread.java:750)
 * //"T1":
 * //        at com.kuangstudy.juc.demo21Lock.HoldLockThread.run(DeadLockDemo.java:58)
 * //        - waiting to lock <0x000000066bc06500> (a java.lang.String)
 * //        - locked <0x000000066bc064c8> (a java.lang.String)
 * //        at java.lang.Thread.run(Thread.java:750)
 * //
 * //Found 1 deadlock.
 */
