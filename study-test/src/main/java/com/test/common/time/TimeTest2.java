package com.test.common.time;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-03-23 17:46
 */
public class TimeTest2 {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = "2021-5-14 00:00:00";
        String endDate = "2022-5-16 00:00:00";
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        List<Date> differMonth = getDifferMonth(start, end);
        differMonth.forEach(month -> {
            System.out.println(month.toString());
        });
    }
    
    
    /**
     * @Description: 计算已经执行天数:起始时间~当前时间的上一个月的最后一天
     **/
    public static int getHasExecuteDays(Date startDate, Date nowDate) {
        //开始时间
        Calendar beginTime_cal = Calendar.getInstance();
        beginTime_cal.setTime(startDate);
        beginTime_cal.set(Calendar.YEAR, beginTime_cal.get(Calendar.YEAR));
        beginTime_cal.set(Calendar.MONTH, beginTime_cal.get(Calendar.MONTH));
        beginTime_cal.set(Calendar.HOUR_OF_DAY, 24);//取开始日的24点
        beginTime_cal.set(Calendar.MINUTE, 0);
        beginTime_cal.set(Calendar.SECOND, 0);
        beginTime_cal.set(Calendar.MILLISECOND, 0);
        //当前时间
        Calendar nowDate_cal = Calendar.getInstance();
        nowDate_cal.setTime(nowDate);//当前日期
        nowDate_cal.set(Calendar.YEAR, nowDate_cal.get(Calendar.YEAR));
        nowDate_cal.set(Calendar.MONTH, nowDate_cal.get(Calendar.MONTH));//当月的上一个月
        nowDate_cal.set(Calendar.DAY_OF_MONTH, nowDate_cal.getActualMaximum(Calendar.DAY_OF_MONTH));//上一个月的最后一天
        nowDate_cal.set(Calendar.HOUR_OF_DAY, 24);
        nowDate_cal.set(Calendar.MINUTE, 0);
        nowDate_cal.set(Calendar.SECOND, 0);
        nowDate_cal.set(Calendar.MILLISECOND, 0);
        
        //进行判断:1如果开始日期和当前时间是在同年同月
        int star_month = beginTime_cal.get(Calendar.MONTH);
        int now_month = nowDate_cal.get(Calendar.MONTH);
        if (star_month == now_month) {
            //摊销天数=开始日24点~本月最后一天24点
            int hasExecuteDays = calculateTimeDiffer(beginTime_cal.getTime(), nowDate_cal.getTime()).get("day");
            System.out.println("啦啦啦啦啦" + hasExecuteDays);
            return hasExecuteDays;
        }
        
        Date lastMonth = nowDate_cal.getTime();//上一个月的最后日期
        System.out.println("上个月的最后日期" + new Date(lastMonth.getTime()));
        int hasExecuteDays = calculateTimeDiffer(startDate, lastMonth).get("day");//自定义类,获取天数差
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
    
    /**
     * 计算期数:和每期的天数;
     * 第一期是签订日所在的月份的剩余天数+签订月后一个月的天数
     */
    public static Map<Integer, Integer> getPeriodAndDays(Date startDate, Date endDate) {
        List<Date> differMonth = getDifferMonth(startDate, endDate);//获取总的月份跨度
        //<期数,每期天数>
        Map<Integer, Integer> periodMap = new LinkedHashMap<>(differMonth.size() - 1);
        for (int i = 0; i < differMonth.size(); i++) {
            if (i == 0) {//首期天数
                Calendar firstMonth = Calendar.getInstance();//首月
                Calendar secondMonth = Calendar.getInstance();//次月
                firstMonth.clear();
                secondMonth.clear();
                firstMonth.setTime(differMonth.get(i));
                secondMonth.setTime(differMonth.get(i + 1));
                int firstMonthDays = firstMonth.getActualMaximum(Calendar.DATE) - firstMonth.get(Calendar.DAY_OF_MONTH);
                int secondMonthDays = secondMonth.get(Calendar.DATE);
                int firstPeriodDays = firstMonthDays + secondMonthDays;//首期天数
                periodMap.put(i + 1, firstPeriodDays);//第一期,第一期天数
            } else if (i == differMonth.size() - 1) {//末期天数
                Calendar lastMonth = Calendar.getInstance();
                lastMonth.clear();
                lastMonth.setTime(differMonth.get(differMonth.size() - 1));
                int lastPeriodDays = lastMonth.get(Calendar.DATE);
                periodMap.put(differMonth.size() - 1, lastPeriodDays);
            } else {
                Calendar cal = Calendar.getInstance();
                cal.clear();
                cal.setTime(differMonth.get(i));
                periodMap.put(i + 1, cal.get(Calendar.DATE));
            }
        }
        for (Map.Entry period : periodMap.entrySet()) {
            System.out.println("第几期" + period.getKey() + ";" + "天数" + period.getValue());
        }
        return periodMap;
    }
    
    public static List<Date> getDifferMonth(Date startDate, Date endDate) {
        /**
         * 获取:期间占用的月份和日期
         **/
        List<Date> result = new ArrayList<Date>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
        //Calendar  日期工具类
        Calendar begin = Calendar.getInstance(); //获取Calendar对象
        Calendar end = Calendar.getInstance();
        begin.clear();
        end.clear();
        //sdf.parse 字符串日期转日期格式，Calendar.setTime(Date) 给Calendar日期对象指定日期
        begin.setTime(startDate);
        begin.set(begin.get(Calendar.YEAR), begin.get(Calendar.MONTH), 1); //指定 年月日
        end.setTime(endDate);
        end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), 2);
        
        Calendar curr = begin;//当前日期
        while (curr.before(end)) {
            //begin.get(Calendar.DATE);
            //result.add(sdf.format(curr.getTime()));
            result.add(curr.getTime());
            curr.add(Calendar.MONTH, 1); //下个月
        }
        
        //设置起始时间结束时间;以及每个月的结束时间
        for (int i = 0; i < result.size(); i++) {
            Calendar now = Calendar.getInstance();
            if (i == 0) {//起始日期
                now.clear();
                now.setTime(startDate);
                result.set(i, now.getTime());
            } else if (i == result.size() - 1) {//结束日期
                now.clear();
                now.setTime(endDate);
                result.set(result.size() - 1, now.getTime());
            } else if (1 <= i || i < result.size()) {//中间日期
                now.clear();
                now.setTime(result.get(i));
                now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
                result.set(i, now.getTime());
            }
        }
        return result;
    }
}
