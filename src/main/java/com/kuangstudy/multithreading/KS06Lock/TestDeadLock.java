package com.kuangstudy.multithreading.KS06Lock;

/**
 * @Describe: 死锁:多个线程相互抱着对方的需要的资源,然后形成僵持.
 * @Author Happy
 * @Create 2023/3/27-11:13
 **/
public class TestDeadLock {
    public static void main(String[] args) {
        Makeup girl1 = new Makeup(0, "灰姑娘");
        Makeup girl2 = new Makeup(1, "白雪公主");
    
        new Thread(girl1).start();
        new Thread(girl2).start();
    }
}

//口红
class Lipstick{

}

//镜子
class Mirror{

}

class Makeup implements Runnable{
    int choice;//选择
    String girlName;//女孩的名字
    //需要的资源只有一份,用static来保证
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();
    
    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }
    
    @Override
    public void run() {
     //化妆
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 例子1:
     * 不死锁的方式:把原本相互套住的锁资源放到外面,每个对象一个锁
     *
     * 结果:
     * 灰姑娘获得口红的锁
     * 白雪公主获得镜子的锁
     * 白雪公主获得口红的锁
     * 灰姑娘获得镜子的锁
     * @throws InterruptedException
     */
    private  void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.girlName+"获得口红的锁");
                Thread.sleep(1000);
            }
            synchronized (mirror) {
                System.out.println(this.girlName+"获得镜子的锁");
            }
        } else {
            synchronized (mirror ) {
                System.out.println(this.girlName+"获得镜子的锁");
                Thread.sleep(2000);
            }
            synchronized (lipstick) {
                System.out.println(this.girlName+"获得口红的锁");
            }
        }
    }
    
    /**
     * 例子2:
     * 这种方式,互相抱着对方的资源会出现死锁
     * @throws InterruptedException
     */
    //private  void makeup() throws InterruptedException {
    //    if (choice == 0) {
    //        synchronized (lipstick) {
    //            System.out.println(this.girlName+"获得口红的锁");
    //            Thread.sleep(1000);
    //            synchronized (mirror) {
    //                System.out.println(this.girlName+"获得镜子的锁");
    //            }
    //        }
    //    } else {
    //        synchronized (mirror ) {
    //            System.out.println(this.girlName+"获得镜子的锁");
    //            Thread.sleep(2000);
    //            synchronized (lipstick) {
    //                System.out.println(this.girlName+"获得口红的锁");
    //            }
    //        }
    //    }
    //}
    
    
}

