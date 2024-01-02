package com.kuangstudy.juc.aba;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/12-13:08
 **/
public class AtomicStampedReferenceTest {
    
    /**
     * 执行结果:
     * A是否成功将STAMPED_REFERENCE的值修改为5：true，当前stamp值为1
     * A是否成功将STAMPED_REFERENCE的值修改为4：true，当前stamp值为2
     * B是否成功将STAMPED_REFERENCE的值修改为10：false，当前stamp值为2
     * STAMPED_REFERENCE最终的值为：4，标记stamp值为：2
     *
     *
     * 使用AtomicStampedReference解决ABA问题
     * 实现思路:
     * 本质是有一个 int 值作为版本号，每次更改前先取到这个int值的版本号，等到修改的时候，
     * 比较当前版本号与当前线程持有的版本号是否一致，如果一致，则进行修改，并将版本号+1
     * （当然加多少或减多少都是可以自己定义的），在zookeeper中保持数据的一致性也是用的这种方式。
     *
     * @param args
     */
    
    
    public static void main(String[] args) {
        testAtomicStampedReference();
    }
    
    
    public static final AtomicStampedReference<Integer> STAMPED_REFERENCE = new AtomicStampedReference<>(4, 0);
    
    public static void testAtomicStampedReference() {
        
        new Thread(() -> {
            
            final int stamp = STAMPED_REFERENCE.getStamp();
            // 四个参数分别是预估内存值，更新值，预估版本号，初始版本号
            // 只有当预估内存值==实际内存值相等 并且 预估版本号==实际版本号，才会进行修改
            boolean b = STAMPED_REFERENCE.compareAndSet(4, 5, stamp, stamp + 1);
            
            System.out.println(Thread.currentThread().getName() + "是否成功将STAMPED_REFERENCE的值修改为5：" + b + "，当前stamp值为" + STAMPED_REFERENCE.getStamp());
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            int stampAdd = STAMPED_REFERENCE.getStamp();
            //System.out.println("当前stamp值为：" + stampAdd);
            
            b = STAMPED_REFERENCE.compareAndSet(5, 4, stampAdd, stampAdd + 1);
            
            System.out.println(Thread.currentThread().getName() + "是否成功将STAMPED_REFERENCE的值修改为4：" + b + "，当前stamp值为" + STAMPED_REFERENCE.getStamp());
            
        }, "A").start();
        
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            boolean b = STAMPED_REFERENCE.compareAndSet(4, 10, 0, 1);
            
            System.out.println(Thread.currentThread().getName() + "是否成功将STAMPED_REFERENCE的值修改为10：" + b + "，当前stamp值为" + STAMPED_REFERENCE.getStamp());
            
        }, "B").start();
        
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        
        System.out.println("STAMPED_REFERENCE最终的值为：" + STAMPED_REFERENCE.getReference() + "，标记stamp值为：" + STAMPED_REFERENCE.getStamp());
        
    }
}
