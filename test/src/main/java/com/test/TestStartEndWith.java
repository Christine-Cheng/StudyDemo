package com.test;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-01-05 15:43:48
 **/
public class TestStartEndWith {
    public static void main(String[] args) {
        String check1 = "一致";
        String check2 = "不一致";
        String check3 = "一致正常";
        String check4 = "不一致异常";
        
        System.out.println("一致1" + check1.startsWith("一致"));
        System.out.println("一致2" + check2.startsWith("一致"));
        System.out.println("一致3" + check3.startsWith("正常"));
        System.out.println("一致4" + check3.startsWith("一致"));
        System.out.println("一致4.2" + check3.startsWith("不一致"));
    }
}
