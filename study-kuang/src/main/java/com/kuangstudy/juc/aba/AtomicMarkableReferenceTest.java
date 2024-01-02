package com.kuangstudy.juc.aba;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/12-13:26
 **/
public class AtomicMarkableReferenceTest {
    public static void main(String[] args) {
        testAtomicMarkableReference();
    }
    
    
    /**
     * 执行结果:
     * A是否成功将ATOMIC_MARKABLE_REFERENCE的值修改为aba：true，当前marked值为true
     * A是否成功将ATOMIC_MARKABLE_REFERENCE的值修改为abc：true，当前marked值为false
     * B是否成功将ATOMIC_MARKABLE_REFERENCE的值修改为abb：true，当前marked值为true
     * ATOMIC_MARKABLE_REFERENCE最终的值为：abb，标记marked值为：true
     * <p>
     * <p>
     * AtomicMarkableReference 解决aba问题，注意，它并不能解决aba的问题 ，
     * 它是通过一个boolean来标记是否更改，本质就是只有true和false两种版本来回切换，
     * 只能降低aba问题发生的几率，并不能阻止aba问题的发生，看下面的例子
     **/
    
    public final static AtomicMarkableReference<String> ATOMIC_MARKABLE_REFERENCE = new AtomicMarkableReference<String>("abc", false);
    
    public static void testAtomicMarkableReference() {
        
        
        new Thread(() -> {
            
            //线程A 获取到mark状态为false，原始值为“abc”
            boolean marked = ATOMIC_MARKABLE_REFERENCE.isMarked();
            
            // 四个参数分别是预估内存值，更新值，预估标记，初始标记
            // 如果当前引用Reference 和 expectedReference相同，并且当前标记mark值和期望mark值相同，则原子更新引用和标记为新值newReference 和 newMark
            boolean b = ATOMIC_MARKABLE_REFERENCE.compareAndSet("abc", "aba", marked, !marked);
            
            System.out.println(Thread.currentThread().getName() + "是否成功将ATOMIC_MARKABLE_REFERENCE的值修改为aba：" + b + "，当前marked值为" + ATOMIC_MARKABLE_REFERENCE.isMarked());
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            boolean markedNew = ATOMIC_MARKABLE_REFERENCE.isMarked(); // true
            //            System.out.println("当前marked值为：" + markedNew);
            
            b = ATOMIC_MARKABLE_REFERENCE.compareAndSet("aba", "abc", markedNew, !markedNew);
            
            System.out.println(Thread.currentThread().getName() + "是否成功将ATOMIC_MARKABLE_REFERENCE的值修改为abc：" + b + "，当前marked值为" + ATOMIC_MARKABLE_REFERENCE.isMarked());
            
        }, "A").start();
        
        
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            boolean b = ATOMIC_MARKABLE_REFERENCE.compareAndSet("abc", "abb", false, true);
            
            System.out.println(Thread.currentThread().getName() + "是否成功将ATOMIC_MARKABLE_REFERENCE的值修改为abb：" + b + "，当前marked值为" + ATOMIC_MARKABLE_REFERENCE.isMarked());
            
        }, "B").start();
        
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        
        System.out.println("ATOMIC_MARKABLE_REFERENCE最终的值为：" + ATOMIC_MARKABLE_REFERENCE.getReference() + "，标记marked值为：" + ATOMIC_MARKABLE_REFERENCE.isMarked());
        
    }
}
