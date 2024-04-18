package com.test.common.data_type;

import java.math.BigDecimal;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-04-08 20:28
 */
public class TestBigDecimal {
    public static void main(String[] args) {
        BigDecimal bigDecimal01 = new BigDecimal(12345.1234567890);
        BigDecimal bigDecimal02 = new BigDecimal(67890.1234567890);
        BigDecimal bigDecimal03 = new BigDecimal(67890.1234567890);
        
        if (bigDecimal02.compareTo(bigDecimal03) >= 0) {
            System.out.println("<=正常");
        } else {
            System.out.println("放屁");
        }
    }
}
