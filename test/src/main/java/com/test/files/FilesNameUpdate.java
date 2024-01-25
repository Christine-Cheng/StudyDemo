package com.test.files;

import java.io.File;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-01-20 20:25:01
 **/
public class FilesNameUpdate {
    
    public static void main(String[] args) {
        String screenShortPath = "C:\\Users\\Happy\\Desktop\\心动";
        String targetPath = "C:\\Users\\Happy\\Desktop\\心动Rename";
        
        RenameFiles renameFiles = new RenameFiles();
        
        renameFiles.remaneFiles(screenShortPath, targetPath);
    }
}


class RenameFiles {
    public void remaneFiles(String sourcePath, String targetPath) {
        
        // 创建目录对象
        File targetDirectory = new File(targetPath);
        File sourceDirectory = new File(sourcePath);
        
        // 检查目录是否存在
        if (sourceDirectory.exists() && sourceDirectory.isDirectory()) {
            // 获取目录下的所有文件
            File[] filesList = sourceDirectory.listFiles();
            
            String prefix = "Screenshot_";
            String suffix = ".jpg.jpg";
            
            // 遍历文件列表
            if (filesList != null) {
                for (File file : filesList) {
                    // 判断是否为文件
                    if (file.isFile()) {
                        //Screenshot_2023-11-13-20-55-13-146_com.tencent.mm
                        String oldName = file.getName();
                        
                        String newName = removePrefixAndSuffix(oldName, prefix, suffix);
                        
                        // 创建新的文件对象
                        File newFile = new File(targetDirectory, newName);
                        
                        // 重命名文件
                        if (file.renameTo(newFile)) {
                            System.out.println("文件重命名成功: " + newName);
                        } else {
                            System.out.println("文件重命名失败: " + oldName);
                        }
                    }
                }
            } else {
                System.out.println("目录为空");
            }
        } else {
            System.out.println("目录不存在或不是一个目录");
        }
    }
    
    private String removePrefixAndSuffix(String fileName, String cutString1, String cutString2) {
        // 获取文件名中最后一个点的索引
        int lastDotIndex = fileName.lastIndexOf('.');
        String suffixName = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
        
        System.out.println("原文件名:" + fileName + " 后缀名:" + suffixName);
        
        // 如果文件名中没有点，或者点的位置在前缀之后，返回原文件名
        if (lastDotIndex == -1 || lastDotIndex < cutString1.length()) {
            return fileName;
        }
        
        // // 截取文件名的前缀和后缀
        // String prefix = fileName.substring(0, lastDotIndex - cutString2.length());
        // String suffix = fileName.substring(lastDotIndex);
        //
        // // 去除前缀和后缀中的固定字符串
        // prefix = prefix.startsWith(cutString1) ? prefix.substring(cutString1.length()) : prefix;
        // suffix = suffix.endsWith(cutString2) ? suffix.substring(0, suffix.length() - cutString2.length()) : suffix;
        
        fileName = fileName.contains(cutString1) ? fileName.replace(cutString1, "") : fileName;
        fileName = fileName.contains(cutString2) ? fileName.replace(cutString2, "") : fileName;
        // 拼接新的文件名
        String newName = fileName.concat(suffixName);
        System.out.println("新文件名称:" + newName);
        return newName;
    }
}
