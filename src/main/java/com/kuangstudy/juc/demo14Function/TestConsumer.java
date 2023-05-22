package com.kuangstudy.juc.demo14Function;

import java.util.function.Consumer;

/**
 * @Describe: 消费型接口，有一个输入参数，没有返回值
 * @Author Happy
 * @Create 2023/4/23-23:18
 **/
public class TestConsumer {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
            
            }
        };
        
        
        // 简写
        Consumer<String> consumer2 = s -> { System.out.println(s);};
        consumer2.accept("abc");
    }
}
