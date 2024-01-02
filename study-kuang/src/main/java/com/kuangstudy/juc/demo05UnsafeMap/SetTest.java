package com.kuangstudy.juc.demo05UnsafeMap;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/19-22:39
 **/

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 1 故障现象：ConcurrentModificationException
 * 2 导致原因：add 方法没有加锁
 * 3 解决方案：换一个集合类
 * 1、Set<String> set = new HashSet<>(); 默认
 * 2、Set<String> set = Collections.synchronizedSet(new HashSet<>());
 * 3、Set<String> set = new CopyOnWriteArraySet();
 * 4 优化建议：（同样的错误，不出现第2次）
 */
public class SetTest {
    public static void main(String[] args) {
        
        //Set<String> set = new HashSet<>();//报错:ConcurrentModificationException
        //Set<String> set = Collections.synchronizedSet(new HashSet<>())
        Set<String> set = new CopyOnWriteArraySet();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
