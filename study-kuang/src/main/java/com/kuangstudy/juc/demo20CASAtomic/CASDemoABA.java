package com.kuangstudy.juc.demo20CASAtomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Describe: CAS引起的ABA问题 (狸猫换太子)
 * CAS算法实现一个重要前提：需要取出内存中某时刻的数据并在当下时刻比较并交换，那么在这个时间差内会导致数据的变化
 * <p></>
 * 比如内存中有A=1,现在有线程T1,T2从主内存中取出A=1,
 * T1对A进行CAS操作,cas(1,2)此时A=2
 * T2对A进行CAS操作,cas(1,5)此时A=5,由于T2比较快,再次对A进行CAS操作,cas(5,1)此时A=1
 * 乍看,A在内存中最终的值没有变化.
 * 但是A的值已经变过几次了
 * 由此就是ABA问题
 * @Author Happy
 * @Create 2023/5/5-21:43
 **/
public class CASDemoABA {
    //CAS : 比较并交换 compareAndSet
    //一句话：真实值和期望值相同，就修改成功，真实值和期望值不同，就修改失败！
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2022);//真实值
        /*期望, 更新
        public final boolean compareAndSet(int expect, int update)
        如果期望值达到了,那么就更新,否则,就不更新. CAS是cpu的并发原语*/
        
        //捣乱的线程
        System.out.println(atomicInteger.compareAndSet(2022, 2023) + "=>" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2023, 2022) + "=>" + atomicInteger.get());
        
        //期望的线程
        //期望的是2022，后面改为 2023 ， 所以结果为 true，2020
        System.out.println(atomicInteger.compareAndSet(2022, 2023) + "=>" + atomicInteger.get());
        
        /**
         * 结果:
         * true=>2023
         * true=>2022
         * true=>2023
         */
        //类似的对于sql为了防止出现ABA这种问题,可以使用 乐观锁来解决.
    }
    
}
