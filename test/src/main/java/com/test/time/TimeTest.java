package com.test.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-03-23 16:49
 */
public class TimeTest {
    public static void main(String[] args) {
        try {
            String dateFrom = "2020-2-1 00:00:00";
            String dateTo = "2020-12-10 13:30:10";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String, Integer> map = calculateTimeDifference(sdf.parse(dateFrom), sdf.parse(dateTo));
            System.out.println(map.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> calculateTimeDifference(Date dateFrom, Date dateTo) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Calendar from = Calendar.getInstance();
        from.setTime(dateFrom);
        Calendar to = Calendar.getInstance();
        to.setTime(dateTo);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int year = 0;
        int month = toYear * 12 + toMonth - (fromYear * 12 + fromMonth);
        if (month >= 12) {
            year = toYear - fromYear;
        }
        int day = (int) ((to.getTimeInMillis() - from.getTimeInMillis()) / (24 * 60 * 60 * 1000));
        int hour = (int) ((to.getTimeInMillis() - from.getTimeInMillis()) / (1000 * 60 * 60));
        int minute = (int) ((to.getTimeInMillis() - from.getTimeInMillis()) / (1000 * 60));
        int second = (int) ((to.getTimeInMillis() - from.getTimeInMillis()) / (1000));
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        map.put("hour", hour);
        map.put("minute", minute);
        map.put("second", second);

        return map;
    }

}
