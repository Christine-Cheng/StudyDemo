package com.kuangstudy.juc.demo19Singleton;

/**
 * @Describe: 原子性操作的DCL单例
 * 双重检测+锁 单例模式(DCL单例) 只有增加一个volatile关键字来避免指令重排,完成原子性操作
 * @Author Happy
 * @Create 2023/4/24-8:23
 **/
public class LazyMan3Volatile {
    private LazyMan3Volatile() {
        System.out.println(Thread.currentThread().getName() + "--->Start");
    }
    
    //单例模式只是在上面DCL单例模式增加一个volatile关键字来避免指令重排
    private static volatile LazyMan3Volatile lazyMan;
    
    //双重检测+锁+原子性操作的 懒汉式单例 (DCL单例)
    public static LazyMan3Volatile getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan3Volatile.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan3Volatile();//不是一个原子性操作
                    /**
                     * lazyMan = new LazyMan();
                     * 不是原子性操作，至少会经过三个步骤：
                     * 1. 分配对象内存空间
                     * 2. 执行构造方法初始化对象
                     * 3. 设置instance指向刚分配的内存地址
                     *
                     * 当多线程的时候,可能存在 A线程走 上述123的步骤, B线程走132的步骤,这样就出现数据凌乱的情况.
                     */
                }
                /**
                 * DCL懒汉式的单例，保证了线程的安全性，又符合了懒加载，只有在用到的时候，才会去初始化，调用
                 * 效率也比较高，但是这种写法在极端情况还是可能会有一定的问题。因为
                 * lazyMan = new LazyMan();
                 * 不是原子性操作，至少会经过三个步骤：
                 * 1. 分配对象内存空间
                 * 2. 执行构造方法初始化对象
                 * 3. 设置instance指向刚分配的内存地址，此时instance ！=null；
                 *
                 *
                 * 由于指令重排，导致A线程执行 lazyMan = new LazyMan();的时候，可能先执行了第三步（还没执行第
                 * 二步），此时线程B又进来了，发现lazyMan已经不为空了，直接返回了lazyMan，并且后面使用了返回
                 * 的lazyMan，由于线程A还没有执行第二步，导致此时lazyMan还不完整，可能会有一些意想不到的错误
                 *
                 * 故,为了避免上述情况,只有在DCL单例模式增加一个volatile关键字来避免指令重排
                 *
                 */
            }
        }
        return lazyMan;
    }
    
    //DCL单例模式 测试并发环境
    //双重检测+锁 单例模式(DCL单例) 只有增加一个volatile关键字来避免指令重排,完成原子性操作
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan3Volatile.getInstance();
            }).start();
        }
    }
}
