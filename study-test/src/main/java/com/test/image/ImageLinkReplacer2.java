package com.test.image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Describe: 将网络图片下载到本地, 换md文件中的链接,且引用本地图片路径为 绝对路径
 *  todo 有问题待优化
 * @Author Happy
 * @Create 2024-03-26 14:29:15
 **/
public class ImageLinkReplacer2 {
    public static void main(String[] args) {
        String mdFilePath = "C:\\ZDownload\\40张图看懂分布式追踪系统原理及实践.md";  // 替换成你的 Markdown 文件路径
        
        try {
            File file = new File(mdFilePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                line = processLine(line, mdFilePath);
                if (line != null) {
                    System.out.println(line);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String processLine(String line, String mdFilePath) {
        String regEx = "!\\[.*?\\]\\((.*?)\\)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(line);
        
        while (matcher.find()) {
            String imageUrl = matcher.group(1);
            String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
            String assetsFolder = mdFilePath.substring(0, mdFilePath.lastIndexOf('.')) + ".assets";
            String localImagePath = assetsFolder + File.separator + fileName;
            
            try {
                URL url = new URL(imageUrl);
                Path imagePath = Paths.get(localImagePath);
                
                // Download the image if it doesn't exist locally
                if (!Files.exists(imagePath)) {
                    Files.createDirectories(imagePath.getParent());
                    InputStream in = url.openStream();
                    Files.copy(in, imagePath);
                    in.close();
                }
                
                // Replace the URL with the local path
                line = line.replace(imageUrl, localImagePath);
                
            } catch (IOException e) {
                // Handle any exceptions related to downloading or replacing the image
                e.printStackTrace();
            }
        }
        
        return line;
    }
}
