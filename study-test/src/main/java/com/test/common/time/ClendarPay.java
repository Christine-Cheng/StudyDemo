package com.test.common.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-03-24 15:28
 */
public class ClendarPay {
    public static void main(String[] args) {
        Date advanceDate = new Date();
        Calendar cl = Calendar.getInstance();
        cl.set(2018, 0, 12);
        cl.set(Calendar.MONTH, cl.get(Calendar.MONTH) - 1);
        System.out.println(cl.MONTH);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cl.getTime()));
        //getCurrentMonthAndNextMonth(8,cl.getTime());
    }
    
    public static void getCurrentMonthAndNextMonth(int payDay, Date advanceDate) {
        // TODO Auto-generated method stub
        Date modelDate;
        Date nextPeriodDate;
        Calendar cal = Calendar.getInstance();
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("当前月最大天数:" + lastDay);
        if (payDay <= lastDay) {
            cal.set(Calendar.DAY_OF_MONTH, payDay);
        } else {
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
        }
        Date currentPeriodDate = cal.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        if (currentPeriodDate.before(advanceDate)) {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
            int nextLastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println("下个月最大天数:" + nextLastDay);
            if (payDay <= nextLastDay) {
                cal.set(Calendar.DAY_OF_MONTH, payDay);
            } else {
                cal.set(Calendar.DAY_OF_MONTH, lastDay);
            }
            nextPeriodDate = cal.getTime();
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        } else {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
            int nextLastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println("上个月最大天数:" + nextLastDay);
            if (payDay <= nextLastDay) {
                cal.set(Calendar.DAY_OF_MONTH, payDay);
            } else {
                cal.set(Calendar.DAY_OF_MONTH, lastDay);
            }
            modelDate = cal.getTime();
            nextPeriodDate = currentPeriodDate;
            currentPeriodDate = modelDate;
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        }
        int currentPeriodDays = daysBetween(currentPeriodDate, nextPeriodDate);
        System.out.println("当前期有：" + currentPeriodDays + "天");
        int holdDays = daysBetween(currentPeriodDate, advanceDate);
        System.out.println("用户持有天数：" + holdDays + "天");
    }
    
    public static int daysBetween(Date beginDate, Date endDate) {
        // TODO Auto-generated method stub
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return (int) between_days;
    }
}
