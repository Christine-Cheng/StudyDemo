package com.kuangstudy.juc.demo16ForkJoin;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/24-7:05
 **/

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算任务
 * 三种方式  1.直接for循环  2.forkJoin  3.Stream并行流
 * 1.如何通过 forkJoinPool 来运行
 * 2. 计算任务 forkJoinPool.execute(ForkJoinTask<?> task )
 * 3. 计算类继承RecursiveTask
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    
    
    private Long start;
    private Long end;
    private static final Long temp = 10000L; //临界值
    
    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }
   
    //计算方法
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <=end; i++) {
                sum += i;
            }
            return sum;
        } else {//forkJoin 递归
            long middle = (start+end)/2;//计算的两个值的中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();//拆分，并压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();//拆分，并压入线程队列
            return task1.join() + task2.join();//合并
        }
    }
}
