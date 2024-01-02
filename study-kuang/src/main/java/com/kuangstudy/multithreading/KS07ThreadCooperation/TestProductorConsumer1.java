package com.kuangstudy.multithreading.KS07ThreadCooperation;

/**
 * @Describe: 测试生产者消费者模型1--->缓冲区解决:俗称"管程法"
 * @Author Happy
 * @Create 2023/3/27-16:50
 **/
//需要的部件: 生产者 , 消费者 , 产品 , 缓冲区
public class TestProductorConsumer1 {
    public static void main(String[] args) {
        SynchroContainer synchroContainer = new SynchroContainer();
        new Thread(new Productor(synchroContainer)).start();
        new Thread(new Consumer(synchroContainer)).start();
    }
}


//生产者
class Productor implements Runnable {
    SynchroContainer synchroContainer;
    
    public Productor(SynchroContainer synchroContainer) {
        this.synchroContainer = synchroContainer;
    }
    
    //生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchroContainer.produce(new Chicken(i));
            System.out.println("生产了第" + i + "只鸡");
        }
    }
}

//消费者
class Consumer implements Runnable {
    SynchroContainer synchroContainer;
    
    public Consumer(SynchroContainer synchroContainer) {
        this.synchroContainer = synchroContainer;
    }
    
    //消费
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了--->第" + synchroContainer.consume().id + "只鸡");
        }
    }
}

//产品
class Chicken {
    public Chicken(int id) {
        this.id = id;
    }
    
    int id;//编号
}

//缓冲区
class SynchroContainer {
    //需要一个容器
    Chicken[] chickens = new Chicken[10];
    
    //容器计数器
    int count = 0;
    
    //生产者生产产品
    public synchronized void produce(Chicken chicken) {
        //若容器满了,就等待消费者消费
        if (count == chickens.length) {
            //生产者等待;通知消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        //如果没有满,就丢入产品.
        chickens[count] = chicken;
        count++;
        
        //可以通知消费者进行消费
        this.notifyAll();
    }
    
    //消费者消费产品
    public synchronized Chicken consume() {
        //判断是否有产品
        if (count == 0) {
            //消费者等待;通知生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        //消费者消费
        count--;
        Chicken chicken = chickens[count];
        
        //吃完了,通知生产者生产.
        this.notifyAll();
        return chicken;
    }
}
