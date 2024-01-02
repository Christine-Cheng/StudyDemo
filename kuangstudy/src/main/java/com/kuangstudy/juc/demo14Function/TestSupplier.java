package com.kuangstudy.juc.demo14Function;

import java.util.function.Supplier;

/**
 * @Describe: 供给型接口，没有输入参数，只有返回参数
 * @Author Happy
 * @Create 2023/4/23-23:18
 **/
public class TestSupplier {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return null;
            }
        };
        Supplier<String> supplier2 = ()->{return "abc";};
        System.out.println(supplier2.get());
    }
}
