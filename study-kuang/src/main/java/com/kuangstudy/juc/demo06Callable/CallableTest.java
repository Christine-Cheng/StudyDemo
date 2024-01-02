package com.kuangstudy.juc.demo06Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/19-23:41
 **/
public class CallableTest {
    ///**
    // * FutureTask源码:
    // *
    // *     Creates a {@code FutureTask} that will, upon running, execute the
    // *     given {@code Callable}.
    // *
    // *     @param  callable the callable task
    // *     @throws NullPointerException if the callable is null
    // *     public FutureTask(Callable<V> callable) {
    // *         if (callable == null)
    // *             throw new NullPointerException();
    // *         this.callable = callable;
    // *         this.state = NEW;       // ensure visibility of callable
    // *     }
    // *
    // * -----------------------------------------------------------------------------
    // *
    // *     Creates a {@code FutureTask} that will, upon running, execute the
    // *     given {@code Runnable}, and arrange that {@code get} will return the
    // *     given result on successful completion.
    // *
    // *     @param runnable the runnable task
    // *     @param result the result to return on successful completion. If
    // *     you don't need a particular result, consider using
    // *     constructions of the form:
    // *     {@code Future<?> f = new FutureTask<Void>(runnable, null)}
    // *     @throws NullPointerException if the runnable is null
    // *     public FutureTask(Runnable runnable, V result) {
    // *         this.callable = Executors.callable(runnable, result);
    // *         this.state = NEW;       // ensure visibility of callable
    // *     }
    // *
    // */
    
    public static void main(String[] args) throws Exception {
        //真实项目中可以不用这种方式了
        //new Thread(new MyThread()).start();
        //new Thread(new Runnable()).start();
        
        //*********************************************************************************
        
        //new Thread(new Runnable()).start(); 等价于 new Thread(new FutureTask<>()).start();
        /**
         * Callable不能直接用new Thread() 进行启动
         * 思考如何用Thread启动Callable
         *
         * api文档中:
         * FutureTask(Callable<V>
         * FutureTask可用于包装Callable或Runnable对象。 因为FutureTask实现Runnable ，一个FutureTask可以提交到一个Executor执行。
         * FutureTask(Callable<V> callable)创建一个 FutureTask,它将在运行时执行给定的 Callable.
         * FutureTask(Runnable runnable, V result)创建一个 FutureTask,将在运行时执行给定的 Runnable,并安排get将在成功完成后返回给定的结果
         *
         * 源码中有
         * Thread 实现了Runnable
         * FutureTask 实现 RunnableFuture 而 RunnableFuture 继承 Runnable 和 Future
         * Runnable的实现类是FutureTask 所以 //new Thread(new Runnable()).start(); 等价于 new Thread(new FutureTask<>()).start();
         * FutureTask 的2个构造方法中又有 Callable Runnable
         *
         *
         * 结论:
         * 可以通过 FutureTask 启动Callable 所以 new Thread(new FutureTask<V>(Callable)).start();
         *
         */
        
        /**
         *  futureTask.get();//这个get()可能会发生阻塞,  处理方式是放到最后 或者 异步通讯来处理.
         */
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);//适配类
        new Thread(futureTask, "AAA").start();
        new Thread(futureTask, "BBB").start();// 第二次调用执行，会有结果缓存，不用再次计算
        
        System.out.println(Thread.currentThread().getName() + " OK");
        
        String result = (String) futureTask.get();//这个get()可能会发生阻塞,处理方式是放到最后 或者 异步通讯来处理.
        System.out.println("resutl--->" + result);
    }
}


class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("11234567890");
        return "OK";
    }
}
