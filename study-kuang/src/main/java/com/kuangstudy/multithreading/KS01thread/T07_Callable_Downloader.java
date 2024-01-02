package com.kuangstudy.multithreading.KS01thread;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Happy
 * @create 2021/9/8-1:04
 * <p>
 * 1. 实现Callable接口，需要返回值类型
 * 2. 重写call方法，需要抛出异常
 * 3. 创建目标对象
 * 4. 创建执行服务：ExecutorService ser = Executors.newFixedThreadPool(1);
 * 5. 提交执行：Future<Boolean> result1 = ser.submit(t1);
 * 6. 获取结果：boolean r1 = result1.get()
 * 7. 关闭服务：ser.shutdownNow();
 */

/**
 * 1. 实现Callable接口，需要返回值类型
 * 2. 重写call方法，需要抛出异常
 * 3. 创建目标对象
 * 4. 创建执行服务：ExecutorService ser = Executors.newFixedThreadPool(1);
 * 5. 提交执行：Future<Boolean> result1 = ser.submit(t1);
 * 6. 获取结果：boolean r1 = result1.get()
 * 7. 关闭服务：ser.shutdownNow();
 */

/**
 * Callable优点
 * 1.可以定义返回值
 * 2.可以抛出异常
 */
public class T07_Callable_Downloader implements Callable<Boolean> {
    private String url;//网路文件地址
    private String name;//保存文件名称
    
    public T07_Callable_Downloader() {
    }
    
    public T07_Callable_Downloader(String url, String name) {
        this.url = url;
        this.name = name;
    }
    
    //下载器主体
    @Override
    public Boolean call() {
        WebDownloader_Callable webDownloader = new WebDownloader_Callable();
        webDownloader.downloader(url, name);
        System.out.println("下载文件名为" + name);
        return true;
    }
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        T07_Callable_Downloader thread1 = new T07_Callable_Downloader("https://img0.baidu.com/it/u=4078444380,951774016&fm=26&fmt=auto&gp=0.jpg", "小黄人01.jpg");
        T07_Callable_Downloader thread3 = new T07_Callable_Downloader("https://img1.baidu.com/it/u=3789727041,4194843647&fm=26&fmt=auto&gp=0.jpg", "小黄人02.jpg");
        T07_Callable_Downloader thread2 = new T07_Callable_Downloader("https://img1.baidu.com/it/u=3833675303,1099592070&fm=26&fmt=auto&gp=0.jpg", "小黄人03.jpg");
        
        //4. 创建执行服务：
        ExecutorService service = Executors.newFixedThreadPool(3);
        //5. 提交执行：
        Future<Boolean> result1 = service.submit(thread1);
        Future<Boolean> result2 = service.submit(thread2);
        Future<Boolean> result3 = service.submit(thread3);
        //6. 获取结果：
        boolean rs1 = result1.get();
        System.out.println("result1--->" + rs1);
        boolean rs2 = result2.get();
        System.out.println("result2--->" + rs2);
        boolean rs3 = result3.get();
        System.out.println("result3--->" + rs3);
        //7. 关闭服务：
        service.shutdownNow();
        
    }
    
}

//网络文件下载器
class WebDownloader_Callable {
    //下载方法
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常,downloader方法出行问题");
        }
    }
}
