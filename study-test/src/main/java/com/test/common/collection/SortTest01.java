package com.test.common.collection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-04-06 01:01
 */
public class SortTest01 {
    public static void main(String[] args) throws ParseException {
        List<Date> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dd = sdf.parse("2000-11-11 12:12:21");
        Date aa = sdf.parse("2002-11-11 12:12:21");
        Date date = new Date();
        
        
        dates.add(date);
        dates.add(dd);
        dates.add(aa);
        /*Collections.sort(dates, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                int flag = o2.compareTo(o1);//降序
                return flag;
            }
        });*/
        
        dates.sort(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                int days = o2.compareTo(o1);
                return days;
            }
        });
        
        dates.forEach(o -> {
            System.out.println(o);
        });
    }
    
}
