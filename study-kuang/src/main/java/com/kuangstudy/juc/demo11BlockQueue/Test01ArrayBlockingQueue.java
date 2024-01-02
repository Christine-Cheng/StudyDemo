package com.kuangstudy.juc.demo11BlockQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: 队列测试
 * ArrayBlockingQueue：由数组结构组成的有界阻塞队列
 * <p>
 * 测试队列的api对
 * 1.抛出异常:    添加add()      移除remove()  检测队首元素element()
 * 2.返回特殊值:  添加offer()    移除poll()    检测队首元素peek()
 * 3.等待阻塞:    添加put()      移除take()    检测队首元素(无)
 * 4.超时退出:    添加offer(,,)  移除poll(,,)  检测队首元素(无)
 * @Author Happy
 * @Create 2023/4/23-16:36
 **/
public class Test01ArrayBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        //test1();
        //test2();
        //test3();
        test4();
    }
    
    /**
     * 1.抛出异常
     * api对  添加add  移除remove  检测队首元素element
     */
    public static void test1() {
        //队列添加方法
        //public boolean add(E e) {
        //        if (offer(e))
        //            return true;
        //        else
        //            throw new IllegalStateException("Queue full");
        //    }
        
        //队列大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("A"));
        System.out.println(blockingQueue.add("B"));
        System.out.println(blockingQueue.add("C"));
        if (blockingQueue.size() == 3) {
            System.out.println("队列满了");
        }
        //java.lang.IllegalStateException: Queue full 抛出异常
        //blockingQueue.add("D");//加满了再加会出现异常.
        
        System.out.println(blockingQueue.element()); // 检测队列队首元素！
        
        System.out.println("====================================");
        
        //public E remove() 返回值E，就是移除的值
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //java.util.NoSuchElementException 抛出异常
        //System.out.println(blockingQueue.remove());
        
        
    }
    
    /**
     * 2.有返回值,没有异常(返回特殊值)
     * api对  添加offer  移除poll  检测队首元素peek
     */
    public static void test2() {
        // 队列大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));// true
        System.out.println(blockingQueue.offer("b"));// true
        System.out.println(blockingQueue.offer("c"));// true
        //System.out.println(blockingQueue.offer("d"));// false 队列满了就返回 false
        System.out.println(blockingQueue.peek());//检测队列队首元素！
        
        // public E poll()
        System.out.println(blockingQueue.poll()); // a
        System.out.println(blockingQueue.poll()); // b
        System.out.println(blockingQueue.poll()); // c
        System.out.println(blockingQueue.poll()); // null
    }
    
    /**
     * 3.等待,阻塞(一直阻塞):
     * api对  添加put  移除take  检测队首元素(无)
     */
    public static void test3() throws InterruptedException {
        //队列大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        //一直阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //blockingQueue.put("d");
        
        System.out.println(blockingQueue.take()); // a
        System.out.println(blockingQueue.take()); // b
        System.out.println(blockingQueue.take()); // c
        //System.out.println(blockingQueue.take()); // 阻塞不停止等待
        
    }
    
    
    /**
     * 4.超时退出
     * api对  添加offer(,,)  移除poll(,,)  检测队首元素(无)
     */
    public static void test4() throws InterruptedException {
        // 队列大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        // 一直阻塞
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        blockingQueue.offer("d", 3L, TimeUnit.SECONDS); // 等待3秒超时退出
        System.out.println("=====================================================");
        System.out.println(blockingQueue.poll()); // a
        System.out.println(blockingQueue.poll()); // b
        System.out.println(blockingQueue.poll()); // c
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS)); // 阻塞不停止等待
    }
}
