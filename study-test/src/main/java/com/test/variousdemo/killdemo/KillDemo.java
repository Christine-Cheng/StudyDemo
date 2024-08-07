package com.test.variousdemo.killdemo;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/3/22-17:16
 **/
public class KillDemo {
    /**
     * 启动10个用户线程
     * 库存6个
     * 生成一个合并队列，3个用户一批次
     * 每个用户能拿到自己的请求响应
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        KillDemo killDemo = new KillDemo();
        killDemo.mergeJob();
        Thread.sleep(2000);
        
        CountDownLatch countDownLatch = new CountDownLatch(10);
        
        System.out.println("-------- 库存 --------");
        System.out.println("库存初始数量 :" + killDemo.stock);
        
        Map<UserRequest, Future<Result>> requestFutureMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            final Long orderId = i + 100L;
            final Long userId = Long.valueOf(i);
            UserRequest userRequest = new UserRequest(orderId, userId, 1);
            Future<Result> future = executorService.submit(() -> {
                countDownLatch.countDown();
                countDownLatch.await(1, TimeUnit.SECONDS);
                return killDemo.operate(userRequest);
            });
            
            requestFutureMap.put(userRequest, future);
        }
        
        System.out.println("------- 客户端响应 -------");
        Thread.sleep(1000);
        requestFutureMap.entrySet().forEach(entry -> {
            try {
                Result result = entry.getValue().get(300, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + ":客户端请求响应:" + result);
                
                if (!result.isSuccess() && result.getMsg().equals("等待超时")) {
                    // 超时，发送请求回滚
                    System.out.println(entry.getKey() + " 发起回滚操作");
                    killDemo.rollback(entry.getKey());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        System.out.println("------- 库存操作日志 -------");
        System.out.println("扣减成功条数: " + killDemo.operateChangeLogList.stream().filter(e -> e.getOperateType().equals(1)).count());
        killDemo.operateChangeLogList.forEach(e -> {
            if (e.getOperateType().equals(1)) {
                System.out.println(e);
            }
        });
        
        System.out.println("扣减回滚条数: " + killDemo.operateChangeLogList.stream().filter(e -> e.getOperateType().equals(2)).count());
        killDemo.operateChangeLogList.forEach(e -> {
            if (e.getOperateType().equals(2)) {
                System.out.println(e);
            }
        });
        
        System.out.println("-------- 库存 --------");
        System.out.println("库存初始数量 :" + killDemo.stock);
        
    }
    
    private void rollback(UserRequest userRequest) {
        if (operateChangeLogList.stream().anyMatch(operateChangeLog -> operateChangeLog.getOrderId().equals(userRequest.getOrderId()))) {
            // 回滚
            boolean hasRollback = operateChangeLogList.stream().anyMatch(operateChangeLog -> operateChangeLog.getOrderId().equals(userRequest.getOrderId()) && operateChangeLog.getOperateType().equals(2));
            if (hasRollback) {
                return;
            }
            System.out.println(" 最终回滚");
            stock += userRequest.getCount();
            saveChangeLog(Lists.newArrayList(userRequest), 2);
        }
        // 忽略
    }
    
    // 模拟数据库行
    private Integer stock = 6;
    
    private BlockingQueue<RequestPromise> queue = new LinkedBlockingQueue<>(10);
    
    /**
     * 用户库存扣减
     *
     * @param userRequest
     *
     * @return
     */
    public Result operate(UserRequest userRequest) throws InterruptedException {
        // TODO 阈值判断
        // TODO 队列的创建
        RequestPromise requestPromise = new RequestPromise(userRequest);
        synchronized (requestPromise) {
            boolean enqueueSuccess = queue.offer(requestPromise, 100, TimeUnit.MILLISECONDS);
            if (!enqueueSuccess) {
                return new Result(false, "系统繁忙");
            }
            try {
                requestPromise.wait(200);
                if (requestPromise.getResult() == null) {
                    return new Result(false, "等待超时");
                }
            } catch (InterruptedException e) {
                return new Result(false, "被中断");
            }
        }
        return requestPromise.getResult();
    }
    
    public void mergeJob() {
        new Thread(() -> {
            List<RequestPromise> list = new ArrayList<>();
            while (true) {
                if (queue.isEmpty()) {
                    try {
                        Thread.sleep(10);
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                int batchSize = 3;
                for (int i = 0; i < batchSize; i++) {
                    try {
                        list.add(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                // 用户ID=5的批次和之后的批次，请求都会超时
                if (list.stream().anyMatch(e -> e.getUserRequest().getUserId().equals(5L))) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                System.out.println(Thread.currentThread().getName() + ":合并扣减库存:" + list);
                
                int sum = list.stream().mapToInt(e -> e.getUserRequest().getCount()).sum();
                // 两种情况
                if (sum <= stock) {
                    // 开始事务
                    stock -= sum;
                    saveChangeLog(list.stream().map(RequestPromise::getUserRequest).collect(Collectors.toList()), 1);
                    // 关闭事务
                    // notify user
                    list.forEach(requestPromise -> {
                        requestPromise.setResult(new Result(true, "ok"));
                        synchronized (requestPromise) {
                            requestPromise.notify();
                        }
                    });
                    list.clear();
                    continue;
                }
                for (RequestPromise requestPromise : list) {
                    int count = requestPromise.getUserRequest().getCount();
                    if (count <= stock) {
                        // 开启事务
                        stock -= count;
                        saveChangeLog(Lists.newArrayList(requestPromise.getUserRequest()), 1);
                        // 关闭事务
                        requestPromise.setResult(new Result(true, "ok"));
                    } else {
                        requestPromise.setResult(new Result(false, "库存不足"));
                    }
                    synchronized (requestPromise) {
                        requestPromise.notify();
                    }
                }
                list.clear();
            }
        }, "mergeThread").start();
    }
    
    // 模拟数据库操作日志表
    // order_id_operate_type uk
    private List<OperateChangeLog> operateChangeLogList = new ArrayList<>();
    
    /**
     * 写库存流水
     *
     * @param list
     * @param operateType
     */
    private void saveChangeLog(List<UserRequest> list, int operateType) {
        List<OperateChangeLog> collect = list.stream().map(userRequest -> new OperateChangeLog(userRequest.getOrderId(),
                userRequest.getCount(), operateType)).collect(Collectors.toList());
        operateChangeLogList.addAll(collect);
    }
}









