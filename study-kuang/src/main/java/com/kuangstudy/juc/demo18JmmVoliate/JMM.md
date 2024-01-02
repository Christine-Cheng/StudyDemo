16、JMM 问题：请你谈谈你对volatile的理解 volitile 是 Java 虚拟机提供的轻量级的同步机制，三大特性： 1、保证可见性 2、不保证原子性 3、禁止指令重排

什么是JMM JMM 本身是一种抽象的概念，并不真实存在，它描述的是一组规则或者规范~
JMM 关于同步的规定： 1、线程解锁前，必须把共享变量的值刷新回主内存 2、线程加锁前，必须读取主内存的最新值到自己的工作内存 3、加锁解锁是同一把锁 JMM即为JAVA 内存模型（java memory
model）。因为在不同的硬件生产商和不同的操作系统下，内 存的访问逻辑有一定的差异，结果就是当你的代码在某个系统环境下运行良好，并且线程安全，但是换
了个系统就出现各种问题。Java内存模型，就是为了屏蔽系统和硬件的差异，让一套代码在不同平台下 能到达相同的访问结果。JMM从java 5开始的JSR-133发布后，已经成熟和完善起来。
JMM规定了内存主要划分为主内存和工作内存两种。此处的主内存和工作内存跟JVM内存划分（堆、 栈、方法区）是在不同的层次上进行的，如果非要对应起来，主内存对应的是Java堆中的对象实例部
分，工作内存对应的是栈中的部分区域，从更底层的来说，主内存对应的是硬件的物理内存，工作内存 对应的是寄存器和高速缓存。

`问题:`在于线程A操作的数据,线程B是否能知道主内存重的值发生了变化,反之亦然
![](.JMM_images/8种内存操作流程简图.png)

![](.JMM_images/8种内存操作流程图(说明).png)

![](.JMM_images/不加volatile关键字.png)

![](.JMM_images/加有volatile关键字.png)

**不保证原子性的原因**
![](.JMM_images/volatile不保证原子性的原因.png)
![](.JMM_images/双加号的直接码实现.png)

**指令重排**
![](.JMM_images/内存屏障.png)
![](.JMM_images/指令重排小结.png)

