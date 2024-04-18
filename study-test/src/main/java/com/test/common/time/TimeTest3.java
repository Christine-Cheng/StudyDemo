package com.test.common.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-03-30 15:53
 */
public class TimeTest3 {
    public static void main(String[] args) throws ParseException {
        
        String start = "2021-01-15 12:12:12";
        String end = "2021-01-25 23:59:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date nowDate = new Date("2021-03-15 00:00:00");
        Date nowDate = sdf.parse("2021-02-15 00:00:00");
        Date startDate = sdf.parse(start);//起始时间
        Date endDate = sdf.parse(end);//截止时间
        Calendar beginTime_cal = Calendar.getInstance();
        Calendar endTime_cal = Calendar.getInstance();
        beginTime_cal.setTime(startDate);
        beginTime_cal.set(Calendar.HOUR_OF_DAY, 0);
        beginTime_cal.set(Calendar.MINUTE, 0);
        beginTime_cal.set(Calendar.SECOND, 0);
        beginTime_cal.set(Calendar.MILLISECOND, 0);
        
        endTime_cal.setTime(endDate);
        endTime_cal.set(Calendar.HOUR_OF_DAY, 0);
        endTime_cal.set(Calendar.MINUTE, 0);
        endTime_cal.set(Calendar.SECOND, 0);
        endTime_cal.set(Calendar.MILLISECOND, 0);
        
        Map<String, Integer> stringIntegerMap = calculateTimeDiffer(beginTime_cal.getTime(), endTime_cal.getTime());
        int hasExecuteDays = getHasExecuteDays(startDate, nowDate);//已经摊销天数:开始日期~当前日期的上一个月的最后一天的24点
        
        System.out.println("开始" + beginTime_cal.getTime());
        System.out.println("结束" + endTime_cal.getTime());
        System.out.println("天数" + stringIntegerMap.get("day"));
        System.out.println("已执行天数" + hasExecuteDays);
        
        System.out.println("*****************************************************************");
        
        Calendar startMoment = Calendar.getInstance();
        Calendar endMoment = Calendar.getInstance();
        startMoment.setTime(beginTime_cal.getTime());
        endMoment.setTime(endTime_cal.getTime());
        startMoment.set(Calendar.DATE, startMoment.getActualMinimum(Calendar.DAY_OF_MONTH));
        endMoment.set(Calendar.DATE, endMoment.getActualMaximum(Calendar.DAY_OF_MONTH));
        endMoment.set(Calendar.HOUR_OF_DAY, 24);
        System.out.println(startMoment.getTime());
        System.out.println(endMoment.getTime());
        
        
        System.out.println("/**************************************/");
        Calendar next_cal = Calendar.getInstance();//起始月的下一个月
        next_cal.setTime(beginTime_cal.getTime());
        next_cal.add(Calendar.MONTH, +1);//次月
        int next_days = next_cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        next_cal.set(Calendar.DATE, next_cal.getActualMinimum(Calendar.DATE));
        Date firstDay_1 = next_cal.getTime();//次月第一天
        next_cal.set(Calendar.DATE, next_cal.getActualMaximum(Calendar.DATE));
        next_cal.set(Calendar.HOUR_OF_DAY, 24);
        Date lastDay_1 = next_cal.getTime();//次月最后一天
        System.out.println("firstDay_1>>>" + firstDay_1);
        System.out.println("lastDay_1>>>" + lastDay_1);
        System.out.println("次月天数" + next_days);
        
        if (nowDate.after(firstDay_1) && nowDate.before(lastDay_1)) {
            System.out.println("lllllll啦啦啦啦啦啦");
        }
        
        int days1 = beginTime_cal.getActualMaximum(Calendar.DATE) - beginTime_cal.get(Calendar.DAY_OF_MONTH);//签订月有效天数
        System.out.println(days1);
        System.out.println("/******************LLLLLL********************/");
        
        Calendar start_llll = Calendar.getInstance();
        start_llll.setTime(nowDate);
        int dayss = start_llll.getActualMaximum(Calendar.DAY_OF_MONTH);
        int llll = start_llll.get(Calendar.DATE);
        System.out.println(dayss - llll);
        System.out.println(dayss);
        
        System.out.println("*************************************YGF***************");
        Calendar beforeLast_cal = Calendar.getInstance();
        beforeLast_cal.setTime(endTime_cal.getTime());
        beforeLast_cal.add(Calendar.MONTH, -1);//上一个月
        beforeLast_cal.set(Calendar.DATE, beforeLast_cal.getActualMaximum(Calendar.DATE));
        beforeLast_cal.set(Calendar.HOUR_OF_DAY, 24);
        Date lastDay_2 = beforeLast_cal.getTime();//最后一天
        System.out.println(beforeLast_cal.getTime());
        System.out.println("***********************PPPPPPPPPP***************");
        
        Map<String, Integer> dateMap = TimeTest2.calculateTimeDiffer(beginTime_cal.getTime(), endTime_cal.getTime());
        for (Map.Entry o : dateMap.entrySet()) {
            System.out.println(o.getKey() + ">>>" + o.getValue());
        }
    }
    
    
    /**
     * @Description: 计算已经执行天数:起始时间~当前时间的上一个月的最后一天24点
     **/
    public static int getHasExecuteDays(Date startDate, Date nowDate) {
        //开始时间;时分秒取0
        Calendar beginTime_cal = Calendar.getInstance();
        beginTime_cal.setTime(startDate);
        beginTime_cal.set(Calendar.YEAR, beginTime_cal.get(Calendar.YEAR));
        beginTime_cal.set(Calendar.MONTH, beginTime_cal.get(Calendar.MONTH));
        beginTime_cal.set(Calendar.HOUR_OF_DAY, 24);//取开始日的24点,既是开始日后一天的0点;若是从开始日期0点开始算,那么24改为0
        beginTime_cal.set(Calendar.MINUTE, 0);
        beginTime_cal.set(Calendar.SECOND, 0);
        beginTime_cal.set(Calendar.MILLISECOND, 0);
        Date beginTime = beginTime_cal.getTime();
        
        //当前时间;时分秒取0
        Calendar nowDate_cal = Calendar.getInstance();
        nowDate_cal.setTime(nowDate);//当前日期
        nowDate_cal.set(Calendar.YEAR, nowDate_cal.get(Calendar.YEAR));
        nowDate_cal.set(Calendar.MONTH, nowDate_cal.get(Calendar.MONTH));
        nowDate_cal.set(Calendar.DATE, nowDate_cal.get(Calendar.DATE));
        nowDate_cal.set(Calendar.HOUR_OF_DAY, 0);
        nowDate_cal.set(Calendar.MINUTE, 0);
        nowDate_cal.set(Calendar.SECOND, 0);
        nowDate_cal.set(Calendar.MILLISECOND, 0);
        Date nowTime = nowDate_cal.getTime();
        
        //当前月最后时刻(当前时间所在月的最后一天的24点;既是下一个月开始的0点)
        Calendar nowMonthLastDay_cal = Calendar.getInstance();
        nowMonthLastDay_cal.setTime(nowDate_cal.getTime());
        nowMonthLastDay_cal.set(Calendar.DAY_OF_MONTH, nowMonthLastDay_cal.getActualMaximum(Calendar.DAY_OF_MONTH));//该月最后一天
        nowMonthLastDay_cal.set(Calendar.HOUR_OF_DAY, 24);//24点
        Date monthLastDate = nowMonthLastDay_cal.getTime();
        
        //当前时间上一个月最后时刻(当前月的上一个月的最后一天24点;既是当月开始的0点)
        Calendar lastMonthLastDay_cal = Calendar.getInstance();
        lastMonthLastDay_cal.setTime(nowDate_cal.getTime());
        lastMonthLastDay_cal.add(Calendar.MONTH, -1);//上一个月
        lastMonthLastDay_cal.set(Calendar.DAY_OF_MONTH, lastMonthLastDay_cal.getActualMaximum(Calendar.DAY_OF_MONTH));//该月最后一天
        lastMonthLastDay_cal.set(Calendar.HOUR_OF_DAY, 24);//24点
        Date lastMonthLastDay = lastMonthLastDay_cal.getTime();
        
        //判断:如果开始日期和当前时间是在同年同月;若成立,则该月剩余时间需要计算
        SimpleDateFormat yearMonth_sdf = new SimpleDateFormat("yyyy-MM");
        String start_month = yearMonth_sdf.format(beginTime_cal.getTime());//开始时间年月
        String now_month = yearMonth_sdf.format(nowDate_cal.getTime());
        if (start_month.equals(now_month)) {
            //摊销天数=开始日24点~本月最后一天24点
            int hasExecuteDays = calculateTimeDiffer(beginTime_cal.getTime(), nowMonthLastDay_cal.getTime()).get("day");
            return hasExecuteDays;
        }
        
        //上一个月的最后日期的24点;既是当月的开始0点
        int hasExecuteDays = calculateTimeDiffer(beginTime_cal.getTime(), lastMonthLastDay_cal.getTime()).get("day");//自定义类,获取天数差
        return hasExecuteDays;
    }
    
    /**
     * @Description: 计算时间差:年;月;日;时;分;秒
     * @Param:
     * @return: map {month=, hour=, year=, day=, minute=, second=}
     * @Author: ZhengHengXun
     * @Date: 2021/3/24
     **/
    public static Map<String, Integer> calculateTimeDiffer(Date startDate, Date endDate) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Calendar from = Calendar.getInstance();
        from.setTime(startDate);
        Calendar to = Calendar.getInstance();
        to.setTime(endDate);
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
