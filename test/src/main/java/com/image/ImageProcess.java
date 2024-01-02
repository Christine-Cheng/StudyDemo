package com.image;

import java.io.File;
import java.util.LinkedList;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/8/28-23:06
 **/
public class ImageProcess {
    public static void main(String[] args) {
        //遍历文件夹下的所有目录
        
        String path = "D:\\Tencent\\QQ\\1737575350\\Image\\Group2";
        traverseFolder1(path);
    
    
    }
    
    
    /**
     * 不使用递归的方法调用。
     * @param path
     */
    public static void traverseFolder1(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> fileLinkedList = new LinkedList<>();
            File[] fileArr = file.listFiles();
            for (File fileElement : fileArr) {
                if (fileElement.isDirectory()) {
                    System.out.println("文件夹:" + fileElement.getAbsolutePath());
                    fileLinkedList.add(fileElement);
                    fileNum++;
                } else {
                    System.out.println("文件:" + fileElement.getAbsolutePath());
                    folderNum++;
                }
            }
            File tempFile;
            while (!fileLinkedList.isEmpty()) {
                tempFile = fileLinkedList.removeFirst();
                fileArr = tempFile.listFiles();
                for (File fileElement : fileArr) {
                    if (fileElement.isDirectory()) {
                        System.out.println("文件夹:" + fileElement.getAbsolutePath());
                        fileLinkedList.add(fileElement);
                        fileNum++;
                    } else {
                        System.out.println("文件:" + fileElement.getAbsolutePath());
                        folderNum++;
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);
    }
}
