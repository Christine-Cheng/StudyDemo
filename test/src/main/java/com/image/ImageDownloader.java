package com.image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下载md文件中图片 ,可以使用 待完善
 */
public class ImageDownloader {
    public static void main(String[] args) {
        String mdFilePath = "C:\\ZDownload\\40张图看懂分布式追踪系统原理及实践.md";  // 替换成你的 Markdown 文件路径
        String targetFolder = "C:\\ZDownload\\40张图看懂分布式追踪系统原理及实践\\.assent";  // 下载图片的目标文件夹
        
        List<String> imageUrls = getImageUrlsFromMarkdown(mdFilePath);
        imageUrls.forEach(System.out::println);
        if (!imageUrls.isEmpty()) {
            int numberOfThreads = 5;  // 可以根据实际情况调整线程数量
            ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
            
            for (String imageUrl : imageUrls) {
                DownloadTask downloadTask = new DownloadTask(imageUrl, targetFolder);
                executor.execute(downloadTask);
            }
            
            executor.shutdown();
            
            // 监听线程状态
            while (!executor.isTerminated()) {
                // 可以在这里输出一些状态信息
                String name = Thread.currentThread().getName();
                Thread.State state = Thread.currentThread().getState();
                System.out.println(name + " is in state: " + state);
            }
            System.out.println("All threads have finished downloading.");
        } else {
            System.out.println("No image URLs found in the markdown file.");
        }
    }
    // size = 36
    
    public static List<String> getImageUrlsFromMarkdown(String mdFilePath) {
        List<String> imageUrls = new ArrayList<>();
        
        try {
            File file = new File(mdFilePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            
            while ((line = br.readLine()) != null) {
                String imageUrl = processLine(line);
                if (imageUrl != null) {
                    imageUrls.add(imageUrl);
                }
            }
            
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return imageUrls;
    }
    
    public static String processLine(String line) {
        String regEx = "!\\[.*?\\]\\((.*?)\\)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(line);
        
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
    
    static class DownloadTask implements Runnable {
        private String imageUrl;
        private String targetFolder;
        
        public DownloadTask(String imageUrl, String targetFolder) {
            this.imageUrl = imageUrl;
            this.targetFolder = targetFolder;
        }
        
        @Override
        public void run() {
            downloadImage(imageUrl, targetFolder);
            System.out.println("Downloaded: " + imageUrl);
        }
    }
    
    
    public static void processLine(String line, String targetFolder) {
        String regEx = "!\\[.*?\\]\\((.*?)\\)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(line);
        
        while (matcher.find()) {
            String imageUrl = matcher.group(1);
            downloadImage(imageUrl, targetFolder);
        }
    }
    
    public static void downloadImage(String imageUrl, String targetFolder) {
        try {
            URL url = new URL(imageUrl);
            InputStream in = url.openStream();
            OutputStream out = new FileOutputStream(targetFolder + "/" + getFileName(imageUrl));
            
            byte[] b = new byte[2048];
            int length;
            while ((length = in.read(b)) != -1) {
                out.write(b, 0, length);
            }
            
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getFileName(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }
}
