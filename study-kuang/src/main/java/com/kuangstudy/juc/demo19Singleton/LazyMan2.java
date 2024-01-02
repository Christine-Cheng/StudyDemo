package com.kuangstudy.juc.demo19Singleton;

/**
 * @Describe: 双重检测锁 模式的懒汉式单例 DCL 的单例
 * @Author Happy
 * @Create 2023/4/24-8:19
 **/
public class LazyMan2 {
    private LazyMan2() {
        System.out.println(Thread.currentThread().getName() + "--->Start");
    }
    
    private static LazyMan2 lazyMan;
    
    //双重检测锁  模式的  懒汉式单例  (DCL的单例)
    public static LazyMan2 getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan2.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan2();//不是一个原子性操作
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
    
    //DCL单例模式 测试并发环境,出现有时候成功是时候失败,原因是lazyMan = new LazyMan2();不是原子操作
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan2.getInstance();
            }).start();
        }
    }
}
