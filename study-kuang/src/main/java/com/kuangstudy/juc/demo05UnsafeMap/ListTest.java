package com.kuangstudy.juc.demo05UnsafeMap;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/17-22:53
 **/
//报错:java.util.ConcurrentModificationException 并发修改异常

public class ListTest {
    public static void main(String[] args) {
        //并发下ArrayList是不安全的. 可以用Synchronized解决,但是太普通,且synchronized开销太大
        /**
         * 解决方案:
         * 1. List<String> list = new Vector<>();//其add()是用的Synchronized 效率低
         * 2. List<String> list1 = new Collections.synchronizedList(new ArrayList<>());
         * 3. List<String> list2 = new CopyOnWriteArrayList<>();
         */
        //CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种简化策略
        //多个线程调用的时候,list,读取的时候,固定的,写入(覆盖)
        //在写入的时候避免覆盖,造成数据问题
        //读写分离
        
        /**
         * 写入时复制（CopyOnWrite，简称COW）思想是计算机程序设计领域中的一种优化策略。其核心思想
         * 是，如果有多个调用者（Callers）同时要求相同的资源（如内存或者是磁盘上的数据存储），他们会共
         * 同获取相同的指针指向相同的资源，直到某个调用者视图修改资源内容时，系统才会真正复制一份专用
         * 副本（private copy）给该调用者，而其他调用者所见到的最初的资源仍然保持不变。这过程对其他的
         * 调用者都是透明的（transparently）。此做法主要的优点是如果调用者没有修改资源，就不会有副本
         * （private copy）被创建，因此多个调用者只是读取操作时可以共享同一份资源。
         * 读写分离，写时复制出一个新的数组，完成插入、修改或者移除操作后将新数组赋值给array
         */
        List<String> list2 = new CopyOnWriteArrayList<>();//看其add() 方法的源码 用的锁是 ReentrantLock
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list2.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list2);
            }, String.valueOf(i)).start();
        }
    }
}
