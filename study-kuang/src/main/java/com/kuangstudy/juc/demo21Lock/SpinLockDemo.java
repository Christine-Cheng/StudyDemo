package com.kuangstudy.juc.demo21Lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Describe: 自旋锁
 * @Author Happy
 * @Create 2023/5/6-9:32
 **/
public class SpinLockDemo {
    
    //原子引用线程,没有写参数,引用类型默认为null
    AtomicReference<Thread> threadAtomicReference = new AtomicReference();
    
    
    //加锁
    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "--->myLock");
    
        //自旋锁
        while (threadAtomicReference.compareAndSet(null, thread)) {
        
        }
    }
    
    
    //解锁
    public void unlock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "--->myUnlock");
        threadAtomicReference.compareAndSet(thread, null);
    }
}
