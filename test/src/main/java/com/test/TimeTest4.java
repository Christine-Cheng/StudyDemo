package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-04-08 11:48
 */
public class TimeTest4 {
    public static void main(String[] args) throws ParseException {
        String dateStr = "2021-04-08 11:55:30";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(dateStr);

        Date myDateTest1 = dateTime(date1, null, null, "first", null, null, null, null);
        System.out.println(myDateTest1);


        Calendar date_cal = Calendar.getInstance();
        date_cal.setTime(date1);
        date_cal.set(Calendar.DATE, date_cal.get(Calendar.DAY_OF_MONTH));
        System.out.println(date_cal.getTime());

    }

    /**
     * @Description: 设置时间:年,月,日,时,分,秒,毫秒
     * @Param: date:传入的时间;
     * isNowMonth:是否当月(null或"now"表示当月,"lsat"上月,"next"下月);
     * day:null或"now"表当天,"first"表当月初天,"end"表当月末天(其余在当月范围内)
     * minOrMaxDay:"min"当月第一天,"max"当月最后一天;minOrMaxDay==null则day有效
     * hourOfDay:小时;
     * minute:分钟;
     * second:秒;
     * milliSecond:毫秒;
     * @return: result
     * @Author: ZhengHengXun
     * @Date: 2021/4/8
     **/
    public static Date dateTime(Date date, String isNowMonth, String minOrMaxDay, String day, Integer hourOfDay, Integer minute, Integer second, Integer milliSecond) {
        Calendar date_cal = Calendar.getInstance();
        date_cal.setTime(date);
        date_cal.set(Calendar.YEAR, date_cal.get(Calendar.YEAR));//设置当前年

        //设置当月,上月,下月
        if (isNowMonth == null || "now".equals(isNowMonth)) {//当前月
            date_cal.set(Calendar.MONTH, date_cal.get(Calendar.MONTH));//设置当前月
        } else if ("last".equals(isNowMonth)) {//上一个月
            date_cal.set(Calendar.MONTH, date_cal.get(Calendar.MONTH));//设置当前月
            date_cal.add(Calendar.MONTH, -1);
        } else if ("next".equals(isNowMonth)) {//下一个月
            date_cal.set(Calendar.MONTH, date_cal.get(Calendar.MONTH));//设置当前月
            date_cal.add(Calendar.MONTH, 1);
        }

        //设置最大最小当前天;null和""表示当天
        if ("min".equals(minOrMaxDay)) {
            date_cal.set(Calendar.DATE, date_cal.getActualMinimum(Calendar.DAY_OF_MONTH));//设置当前月第一天
        } else if ("max".equals(minOrMaxDay)) {
            date_cal.set(Calendar.DATE, date_cal.getActualMaximum(Calendar.DAY_OF_MONTH));//设置当前月最后天
        }

        //设置天数
        if (minOrMaxDay == null) {//minOrMaxDay == null 则day才有效
            if (day == null || "now".equals(day)) {
                date_cal.set(Calendar.DATE, date_cal.get(Calendar.DATE));//设置当前天
            } else if ("first".equals(day)) {//当月第一天
                date_cal.set(Calendar.DATE, date_cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            } else if ("end".equals(day)) {//当月最后一天
                date_cal.set(Calendar.DATE, date_cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else if (date_cal.getActualMinimum(Calendar.DAY_OF_MONTH) <= Integer.parseInt(day) && Integer.parseInt(day) <= date_cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {//输入的天数在当月天数范围内
                date_cal.set(Calendar.DATE, Integer.parseInt(day));//设置当月内的第几天
            } else {//否则就是当天
                date_cal.set(Calendar.DATE, date_cal.get(Calendar.DATE));//设置当前天
            }
        }

        //设置时分秒毫秒
        if (hourOfDay != null && (0 <= hourOfDay.intValue() || hourOfDay.intValue() <= 24)) {
            date_cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        } else if (hourOfDay == null) {
            date_cal.set(Calendar.HOUR_OF_DAY, date_cal.get(Calendar.HOUR));
        }

        if (minute != null && (0 <= minute || minute <= 60)) {
            date_cal.set(Calendar.MINUTE, minute);
        } else if (hourOfDay == null) {
            date_cal.set(Calendar.MINUTE, date_cal.get(Calendar.MINUTE));
        }

        if (second != null && (0 <= second || second <= 60)) {
            date_cal.set(Calendar.SECOND, second);
        } else if (hourOfDay == null) {
            date_cal.set(Calendar.SECOND, date_cal.get(Calendar.SECOND));
        }

        if (milliSecond != null && (0 <= milliSecond || milliSecond <= 1000)) {
            date_cal.set(Calendar.MILLISECOND, milliSecond);
        } else if (hourOfDay == null) {
            date_cal.set(Calendar.MILLISECOND, date_cal.get(Calendar.MILLISECOND));
        }

        Date result = date_cal.getTime();
        return result;
    }


}
