package com.test.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-04-12 00:02
 */
public class TimeTest5CompareTo {
    public static void main(String[] args) throws ParseException {
        String date1 = "2021-03-15 12:30:30";
        String date2 = "2021-04-15 12:30:30";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = sdf2.parse(date1);

        Date d1_1 = sdf.parse(date1);
        System.out.println(d1);
        System.out.println(d1_1);


    }
}
