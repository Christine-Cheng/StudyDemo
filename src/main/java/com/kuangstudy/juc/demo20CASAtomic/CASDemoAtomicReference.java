package com.kuangstudy.juc.demo20CASAtomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Describe: 原子引用AtomicReference 解决ABA问题
 * 版本号原子引用，类似乐观锁
 * @Author Happy
 * @Create 2023/5/5-22:37
 **/
public class CASDemoAtomicReference {
    //CAS : 比较并交换 compareAndSet
    //一句话：真实值和期望值相同，就修改成功，真实值和期望值不同，就修改失败！
    
    
    //AtomicStampedReference 注意: 如果泛型是一个包装类,注意对象的引用问题
    /*
    * 对于Integer var = ? 范围是-128至127之间赋值,Integer对象是在IntegerCache.cache产生,
    * 会复用已有的对象,这个区间内的Integer值可以直接使用==进行判断,
    * 但是,这个区间之外的数据都会在堆上产生,并不会复用已有的对象,
    * 这是一个大坑,推荐使用equals方法进行判断.
    * */
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
    
    //和乐观锁原理一样
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("A1--->" + atomicStampedReference.getStamp());
            
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            System.out.println(atomicStampedReference.compareAndSet(
                    100,
                    101,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1));
            System.out.println("A2--->" + atomicStampedReference.getStamp());
    
    
            System.out.println(atomicStampedReference.compareAndSet(
                    101,
                    100,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1));
            System.out.println("A3--->" + atomicStampedReference.getStamp());
            
        },"A").start();
        
        
        new Thread(() -> {
    
            System.out.println("B1--->" + atomicStampedReference.getStamp());
    
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            System.out.println(atomicStampedReference.compareAndSet(
                    100,
                    106,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1));
            System.out.println("B2--->" + atomicStampedReference.getStamp());
    
    
            System.out.println(atomicStampedReference.compareAndSet(
                    106,
                    100,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1));
            System.out.println("B3--->" + atomicStampedReference.getStamp());
        
        },"B").start();
    }
}
