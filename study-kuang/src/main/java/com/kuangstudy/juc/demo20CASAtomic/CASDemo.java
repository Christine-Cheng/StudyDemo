package com.kuangstudy.juc.demo20CASAtomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Describe: 参数：期望值，更新值
 * public final boolean compareAndSet(int expect, int update) {
 * return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
 * }
 * <p></p>
 * @Author Happy
 * @Create 2023/4/24-8:41
 **/
public class CASDemo {
    //CAS : 比较并交换 compareAndSet
    //一句话：真实值和期望值相同，就修改成功，真实值和期望值不同，就修改失败！
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);//真实值
        //期望, 更新
        //public final boolean compareAndSet(int expect, int update)
        //如果期望值达到了,那么就更新,否则,就不更新. CAS是cpu的并发原语
        
        // 期望的是5，后面改为 2020 ， 所以结果为 true，2020
        System.out.println(atomicInteger.compareAndSet(5, 2020) + "=>" + atomicInteger.get());
        // 期望的是5，后面改为 1024 ， 所以结果为 false，2020
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "=>" + atomicInteger.get());
    }
    
    
}
