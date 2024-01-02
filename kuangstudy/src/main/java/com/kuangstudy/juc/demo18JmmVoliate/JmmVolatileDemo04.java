package com.kuangstudy.juc.demo18JmmVoliate;

/**
 * @Describe: volatile的指令重排讲解
 * @Author Happy
 * @Create 2023/5/4-23:13
 **/
public class JmmVolatileDemo04 {
    //见笔记
    
    public static void main(String[] args) {
        int x = 11; // 语句1
        int y = 12; // 语句2
        x = x + 5; // 语句3
        y = x * x; // 语句4
    }
    // 指令顺序预测: 1234 2134 1324
    // 问题：请问语句4可以重排后变成第一条吗？ 答案：不可以
}


// 多线程环境中线程交替执行，由于编译器优化重排的存在
// 两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测。
class TestHappensBefore {
    int a = 0;
    boolean flag = false;
    
    public void m1() {
        a = 1; // 语句1
        flag = true; // 语句2
    }
    
    public void m2() {
        if (flag) {
            a = a + 5; // 语句3
            System.out.println("m2=>" + a);
        }
    }
}
