package com.kuangstudy.juc.demo14Function;

import java.util.function.Predicate;

/**
 * @Describe: 断定型接口，有一个输入参数，返回只有布尔值。
 * @Author Happy
 * @Create 2023/4/23-23:14
 **/
public class TestPredicate {
    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };
        
        
        // 简写
        Predicate<String> predicate2 = s -> {
            return s.isEmpty();
        };
        System.out.println(predicate2.test("abc"));
    }
}
