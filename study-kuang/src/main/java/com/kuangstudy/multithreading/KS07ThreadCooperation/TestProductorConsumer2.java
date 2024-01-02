package com.kuangstudy.multithreading.KS07ThreadCooperation;

/**
 * @Describe: 测试生产者消费者模型2--->信号灯法:即是通过标志位解决
 * 实质是一个互锁机制
 * @Author Happy
 * @Create 2023/3/27-19:47
 **/
public class TestProductorConsumer2 {
    public static void main(String[] args) {
        TV tv = new TV();
        Player player = new Player(tv);
        Viewer viewer = new Viewer(tv);
        
        new Thread(player).start();
        new Thread(viewer).start();
    }
}

//生产者--->演员
//生产者只负责生产
class Player implements Runnable {
    TV tv;
    
    public Player(TV tv) {
        this.tv = tv;
    }
    
    @Override
    public void run() {
        
        for (int i = 0; i < 20; i++) {//模拟20小时
            if (i % 2 == 0) {
                tv.performing("快乐大本营");
            } else {
                tv.performing("抖音:记录美好生活");
            }
        }
    }
}

//消费者--->观众
//消费者只负责消费
class Viewer implements Runnable {
    TV tv;
    
    public Viewer(TV tv) {
        this.tv = tv;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watching();
        }
    }
}

//产品--->节目
class TV {
    //演员表演,观众等待  flag=true
    //观众观看,演员等待  flag=false
    
    String shows;
    boolean flag = true;
    
    
    //演员表演
    public synchronized void performing(String shows) {
        if (!flag) {//观众观看,演员等待  flag=false
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("演员表演了" + shows);
        //通知观众观看,唤醒线程
        this.notifyAll();
        this.shows = shows;
        this.flag = !this.flag;//演员等待
    }
    
    //观众观看
    public synchronized void watching() {
        if (flag) {//演员表演,观众等待  flag=true
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("观众观看了: " + shows);
        //通知演员表演,唤醒线程
        this.notifyAll();
        this.flag = !this.flag;//观众等待
    }
}
