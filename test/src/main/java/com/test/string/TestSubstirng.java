package com.test.string;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-11 09:39:14
 **/
public class TestSubstirng {
    public static void main(String[] args) {
        String str = "1234567";
        
        System.out.println(str.substring(0, 3));
        System.out.println(str.substring(3));
        
        
        int num1 = 0b101010;  // 二进制表示为 101010
        int num2 = 0b110011;  // 二进制表示为 110011
        
        // 与操作
        int resultAnd = num1 & num2;
        System.out.println("与操作结果：" + Integer.toBinaryString(resultAnd));  // 输出结果的二进制表示
        
        // 或操作
        int resultOr = num1 | num2;
        System.out.println("或操作结果：" + Integer.toBinaryString(resultOr));  // 输出结果的二进制表示
    }
}
