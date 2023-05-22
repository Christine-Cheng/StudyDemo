package com.test;

import java.math.BigDecimal;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-03-31 20:07
 */
public class AmountTest1 {
    public static void main(String[] args) {
        BigDecimal totalMoney = new BigDecimal(12345.1234);
        int totalDays = 100;
        int nowDays = 31;
        BigDecimal differencePrice = totalMoney.divide(new BigDecimal(totalDays), 9, BigDecimal.ROUND_CEILING).multiply(new BigDecimal(nowDays));
        System.out.println(differencePrice);
        //3826.988254000  ROUND_HALF_UP
        //3826.988254031  ROUND_UP
        //3826.988254031  ROUND_CEILING
        System.out.println(new BigDecimal(totalDays));
    }
}
