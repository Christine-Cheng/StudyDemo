package com.kuangstudy.juc.demo14Function;

import java.util.function.Function;

/**
 * @Describe:
 * Function 函数类型接口,有一个输入参数,有一个输出
 * 只要是函数类接口,可用,lambda简化
 * @Author Happy
 * @Create 2023/4/23-23:02
 **/
public class TestFunction {
    public static void main(String[] args) {
        //工具类: 输入输出值
        Function function = new Function<String, String>() {
            
            @Override
            public String apply(String s) {
                return s;
            }
        };
    
    
        // 简写
        Function<String,Integer> function2 = s->{return s.length();};
        System.out.println(function2.apply("abc"));
    }
}
