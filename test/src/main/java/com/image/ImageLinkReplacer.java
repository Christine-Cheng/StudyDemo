package com.image;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Describe: 将下载到本地的网络图片, 换md文件中的链接, 绝对路径 可以使用待完善
 * @Author Happy
 * @Create 2024-03-26 14:10:03
 **/
public class ImageLinkReplacer {
    public static void main(String[] args) {
        String mdFilePath = "C:\\ZDownload\\40张图看懂分布式追踪系统原理及实践.md";  // 替换成你的 Markdown 文件路径
        String relativeImagePath = "C:\\ZDownload\\40张图看懂分布式追踪系统原理及实践\\.assets";  // 下载图片的目标文件夹
        
        
        List<String> imageUrls = getAndReplaceImageUrls(mdFilePath, relativeImagePath);
        if (imageUrls.isEmpty()) {
            System.out.println("No image URLs found or replaced in the markdown file.");
        } else {
            System.out.println("Image URLs replaced in the markdown file:");
            for (String replacedUrl : imageUrls) {
                System.out.println(replacedUrl);
            }
        }
    }
    
    public static List<String> getAndReplaceImageUrls(String mdFilePath, String targetFolder) {
        List<String> replacedImageUrls = new ArrayList<>();
        
        try {
            File file = new File(mdFilePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>();
            String line;
            
            while ((line = br.readLine()) != null) {
                String replacedLine = processLine(line, targetFolder);
                if (replacedLine != null) {
                    replacedImageUrls.add(replacedLine);
                }
                lines.add(replacedLine != null ? replacedLine : line);
            }
            
            
            br.close();
            
            // Write the modified content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(mdFilePath));
            for (String modifiedLine : lines) {
                if (modifiedLine != null) {
                    writer.write(modifiedLine);
                    writer.newLine();
                }
            }
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return replacedImageUrls;
    }
    
    
    public static String processLine(String line, String relativeImagePath) {
        String regEx = "!\\[.*?\\]\\((.*?)\\)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(line);
        
        while (matcher.find()) {
            String imageUrl = matcher.group(1);
            Path path = Paths.get(imageUrl);
            String fileName = path.getFileName().toString();
            String destinationFilePath = relativeImagePath + File.separator + fileName;
            
            // If the file exists, replace the URL with the local path
            File imageFile = new File(destinationFilePath);
            if (imageFile.exists()) {
                line = line.replace(imageUrl, destinationFilePath);
                return destinationFilePath;
            }
        }
        
        return null;
    }
}
