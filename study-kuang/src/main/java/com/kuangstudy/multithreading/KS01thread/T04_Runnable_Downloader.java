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
        WebDownloader_Runnable webDownloader = new WebDownloader_Runnable();
        webDownloader.downloader(url, name);
        System.out.println("下载文件名为" + name);
    }
    
    public static void main(String[] args) {
        T02_Thread_Downloader thread1 = new T02_Thread_Downloader("https://img2.baidu.com/it/u=2151538831,3661748864&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500", "小黄人01.jpg");
        T02_Thread_Downloader thread3 = new T02_Thread_Downloader("https://img2.baidu.com/it/u=2498623067,847135744&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800", "小黄人02.jpg");
        T02_Thread_Downloader thread2 = new T02_Thread_Downloader("https://img2.baidu.com/it/u=4127395109,1954135995&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500", "小黄人03.jpg");
        T02_Thread_Downloader thread4 = new T02_Thread_Downloader("https://img1.baidu.com/it/u=2479956157,2733927645&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=888", "小黄人04.jpg");
    
        //照理来说是顺序执行,但是实际是由cpu调度执行.谁先调度谁先执行
        //创建行程对象,通过线程对象启动线程(代理)
        new Thread(thread1).start();
        new Thread(thread2).start();
        new Thread(thread3).start();
        new Thread(thread4).start();
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
