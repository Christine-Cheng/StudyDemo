package com.test.common.collection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-03-26 11:39
 */
public class IteatorTest {
    
    public static void main(String[] args) throws ParseException {
        testiterList();
        String dateTo = "2020-12-10 13:30:10";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = sdf.parse(dateTo);
        Calendar beforeLast_cal = Calendar.getInstance();
        beforeLast_cal.setTime(endDate);
        beforeLast_cal.set(Calendar.DATE, beforeLast_cal.getActualMaximum(Calendar.DATE));
        
        System.out.println("获取月份" + (beforeLast_cal.get(Calendar.MONTH) + 1));
        
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:dd:ss").format(beforeLast_cal.getTime()));
        
        System.out.println("*************************************");
        
        Calendar test_cal = Calendar.getInstance();
        Date date_test = new Date();
        test_cal.setTime(date_test);
        test_cal.set(Calendar.MONTH, test_cal.get(Calendar.MONTH));//calendar的month是从0开始记录,输出显示时候月份要加1,其他不需要
        test_cal.set(Calendar.DAY_OF_MONTH, test_cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        test_cal.set(Calendar.HOUR_OF_DAY, 24);
        test_cal.set(Calendar.MINUTE, 0);
        test_cal.set(Calendar.SECOND, 0);
        test_cal.set(Calendar.MILLISECOND, 0);
        
        int month = test_cal.get(Calendar.MONTH) + 1;//calendar的month是从0开始记录,输出显示时候月份要加1
        int date = test_cal.get(Calendar.DATE);
        
        System.out.println("获取当前月份" + month);
        System.out.println("天" + date);
        System.out.println("日期" + test_cal.getTime());
        System.out.println("微秒" + test_cal.get(Calendar.MILLISECOND));
        
    }
    
    public static void testiterList() {//遍历数组
        //可以遍历数组,集合,
        List<String> list = new ArrayList<>();
        list.add("shan");
        list.add("qi");
        list.add("jie");
        for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
            String temp = iter.next();//遍历数组迭代器
            System.out.println(temp);
        }
    }
}
