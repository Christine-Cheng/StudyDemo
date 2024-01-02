package com.kuangstudy.multithreading.KS01thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Happy
 * @create 2021/9/7-23:33
 **/
public class T04_Runnable_Downloader implements Runnable {
    private String url;//网路文件地址
    private String name;//保存文件名称
    
    public T04_Runnable_Downloader() {
    }
    
    public T04_Runnable_Downloader(String url, String name) {
        this.url = url;
        this.name = name;
    }
    
    //下载器主体
    @Override
    public void run() {
        WebDownloader_Runnable  webDownloader= new WebDownloader_Runnable();
        webDownloader.downloader(url, name);
        System.out.println("下载文件名为" + name);
    }
    
    public static void main(String[] args) {
        T02_Thread_Downloader thread1 = new T02_Thread_Downloader("https://img0.baidu.com/it/u=4078444380,951774016&fm=26&fmt=auto&gp=0.jpg", "小黄人01.jpg");
        T02_Thread_Downloader thread3 = new T02_Thread_Downloader("https://img1.baidu.com/it/u=3789727041,4194843647&fm=26&fmt=auto&gp=0.jpg", "小黄人02.jpg");
        T02_Thread_Downloader thread2 = new T02_Thread_Downloader("https://img1.baidu.com/it/u=3833675303,1099592070&fm=26&fmt=auto&gp=0.jpg", "小黄人03.jpg");
        
        //照理来说是顺序执行,但是实际是由cpu调度执行.谁先调度谁先执行
        //创建行程对象,通过线程对象启动线程(代理)
        new Thread(thread1).start();
        new Thread(thread2).start();
        new Thread(thread3).start();
    }
}

//网络文件下载器
class WebDownloader_Runnable {
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
