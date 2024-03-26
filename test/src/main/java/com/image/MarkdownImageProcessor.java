package com.image;

import org.apache.commons.io.FilenameUtils;

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
 * @Describe: todo 有问题待优化
 * @Author Happy
 * @Create 2024-03-26 14:32:41
 **/
public class MarkdownImageProcessor {
    public static void main(String[] args) {
        String mdFilePath = "path_to_your_markdown_file.md";  // 替换成你的Markdown文件路径
        
        try {
            processMarkdownFile(mdFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void processMarkdownFile(String mdFilePath) throws IOException {
        String fileContent = loadFileContent(mdFilePath);
        String directoryName = FilenameUtils.getBaseName(mdFilePath) + ".assets";
        String localImageDirectory = FilenameUtils.concat(new File(mdFilePath).getParent(), directoryName);
        
        Pattern pattern = Pattern.compile("!\\[.*?]\\((.*?)\\)");
        
        Matcher matcher = pattern.matcher(fileContent);
        
        while (matcher.find()) {
            String imageUrl = matcher.group(1);
            String imageName = FilenameUtils.getName(imageUrl);
            
            try {
                // Download the image and replace the link
                String localImagePath = downloadImage(imageUrl, localImageDirectory, imageName);
                if (localImagePath != null) {
                    fileContent = fileContent.replace(imageUrl, localImagePath);
                }
            } catch (IOException e) {
                // Handle any exceptions related to downloading or replacing the image
                e.printStackTrace();
            }
        }
        
        // Save the updated content back to the file
        String newMdFilePath = modifyImagePathInMarkdown(fileContent, mdFilePath, directoryName);
        System.out.println("Updated file saved at: " + newMdFilePath);
    }
    
    private static String loadFileContent(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        
        reader.close();
        return stringBuilder.toString();
    }
    
    private static String downloadImage(String imageUrl, String localImageDirectory, String imageName) throws IOException {
        Path directory = Paths.get(localImageDirectory);
        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }
        
        Path imagePath = Paths.get(localImageDirectory, imageName);
        
        // Check if the image exists locally
        if (Files.exists(imagePath)) {
            return imagePath.toString();
        }
        
        // Download the image if it doesn't exist locally
        try (InputStream in = new URL(imageUrl).openStream()) {
            Files.copy(in, imagePath);
        }
        
        return imagePath.toString();
    }
    
    private static String modifyImagePathInMarkdown(String fileContent, String mdFilePath, String directoryName) throws IOException {
        String newMdFilePath = FilenameUtils.getFullPath(mdFilePath) + FilenameUtils.getBaseName(mdFilePath) + "_updated.md";
        // fileContent = fileContent.replaceAll("(?s)!(\\[.*?]\\()(.+?)(\\))", "\$1" + directoryName + "/\$2\$3");
        //
        // BufferedWriter writer = new BufferedWriter(new FileWriter(newMdFilePath));
        // writer.write(fileContent);
        // writer.close();
        
        return newMdFilePath;
    }
}
