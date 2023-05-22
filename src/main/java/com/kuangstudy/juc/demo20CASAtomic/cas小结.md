# CAS
AtomicInteger源码
![](.cas小结_images/AtomicInteger源码.png)
Unsafe类
![](.cas小结_images/Unsafe类.png)
Unsafe类的getAndAddInt(),是自旋锁
![](.cas小结_images/自旋锁.png)

```
cas:比较当前工作内存中的值和主内存中的值,如果这个值是期望的,那么执行操作! 如果不是就一直循环!
```
缺点:
1.循环会耗时
2.一次性能保证一个共享变量的原子性
2.ABA问题
