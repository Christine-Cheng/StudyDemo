package com.image;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Describe: todo 有问题待优化
 * @Author Happy
 * @Create 2024-03-26 14:39:42
 **/
public class MarkdownImageLocalizer {
    public static void main(String[] args) {
        String mdFilePath = "C:\\ZDownload\\40张图看懂分布式追踪系统原理及实践.md";  // 替换成你的Markdown文件路径
        String localImageDirectory = "C:\\ZDownload\\";  // 替换成你的本地图片文件夹路径
        
        try {
            processMarkdownFile(mdFilePath, localImageDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void processMarkdownFile(String mdFilePath, String localImageDirectory) throws IOException {
        String fileContent = loadFileContent(mdFilePath);
        String directoryName = FilenameUtils.getBaseName(mdFilePath) + ".assets";
        String localImageRelativePath = "./" + directoryName + "/";
        
        Pattern pattern = Pattern.compile("!\\[.*?]\\((.*?)\\)");
        
        Matcher matcher = pattern.matcher(fileContent);
        
        while (matcher.find()) {
            String imageUrl = matcher.group(1);
            String imageName = FilenameUtils.getName(imageUrl);
            String localImagePath = localImageDirectory + File.separator + directoryName + File.separator + imageName;
            
            try {
                downloadImage(imageUrl, localImagePath);
                fileContent = fileContent.replace(imageUrl, localImageRelativePath + imageName);
            } catch (IOException e) {
                // Handle any exceptions related to downloading or replacing the image
                e.printStackTrace();
            }
        }
        
        // Save the updated content back to the file
        saveFileContent(mdFilePath, fileContent);
    }
    
    private static String loadFileContent(String filePath) throws IOException {
        return FileUtils.readFileToString(new File(filePath), "UTF-8");
    }
    
    private static void downloadImage(String imageUrl, String localImagePath) throws IOException {
        Path directory = Paths.get(localImagePath).getParent();
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        
        // Download the image if it doesn't exist locally
        try (InputStream in = new URL(imageUrl).openStream()) {
            Files.copy(in, Paths.get(localImagePath));
        }
    }
    
    private static void saveFileContent(String filePath, String content) throws IOException {
        FileUtils.writeStringToFile(new File(filePath), content, "UTF-8");
    }
}
