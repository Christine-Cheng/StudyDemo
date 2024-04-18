package com.test.common;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-06-23 18:34
 */
public class test6 {
    public static void main(String[] args) {
        gusse();
    }
    
    public static int gusse() {
        int a = 1;
        if (a == 1) {
            System.out.println("啦啦啦");
            int b = 2;
            if (b == 2) {
                System.out.println("哈哈哈");
                b = 4;
                return b;
            }
            System.out.println(b);
            a = 5;
        }
        System.out.println(a);
        return a;
    }
}
