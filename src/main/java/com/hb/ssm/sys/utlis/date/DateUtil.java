package com.hb.ssm.sys.utlis.date;

import org.activiti.engine.impl.util.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期公共函数
 *
 * @author huangbo
 * @create: 2019-08-03 13:59
 * <p>
 * public static final int DAY_OF_WEEK_IN_MONTHget 和 set 的字段数字，指示当前月中的第几个星期。
 * 与 DAY_OF_WEEK 字段一起使用时，就可以唯一地指定某月中的某一天。
 * 与 WEEK_OF_MONTH 和 WEEK_OF_YEAR 不同，
 * 该字段的值并不 取决于 getFirstDayOfWeek() 或 getMinimalDaysInFirstWeek()。
 * DAY_OF_MONTH 1 到 7 总是对应于 DAY_OF_WEEK_IN_MONTH 1；8 到 14 总是对应于 DAY_OF_WEEK_IN_MONTH 2，
 * 依此类推。DAY_OF_WEEK_IN_MONTH 0 表示 DAY_OF_WEEK_IN_MONTH 1 之前的那个星期。
 * 负值是从一个月的末尾开始逆向计数，因此，一个月的最后一个星期天被指定为 DAY_OF_WEEK = SUNDAY,
 * DAY_OF_WEEK_IN_MONTH = -1。因为负值是逆向计数的，所以它们在月份中的对齐方式通常与正值的不同。
 * 例如，如果一个月有 31 天，那么 DAY_OF_WEEK_IN_MONTH -1 将与 DAY_OF_WEEK_IN_MONTH 5
 * 和 DAY_OF_WEEK_IN_MONTH 4 的末尾相重叠。
 */
public class DateUtil {

    private static List<Calendar> holidayList;
    private static boolean holidayFlag;

    /**
     * 计算工作日
     * 具体节日包含哪些,可以在HolidayMap中修改
     *
     * @param src     日期(源)
     * @param adddays 要加的天数
     * @throws throws [违例类型] [违例说明]
     * @version [s001, 2010-11-19]
     * @author shenjunjie
     */

    public static Calendar addDateByWorkDay(Calendar src, int adddays) {
//        Calendar result = null;
        holidayFlag = false;
        for (int i = 0; i < adddays; i++) {
            //把源日期加一天
            src.add(Calendar.DAY_OF_MONTH, 1);
            holidayFlag = checkHoliday(src);
            if (holidayFlag) {
                i--;
            }
            System.out.println(src.getTime());
        }
        System.out.println("Final Result:" + src.getTime());
        return src;
    }

    /**
     * @param src 要校验的日期(源)
     * @Description: 具体节日包含哪些, 可以在HolidayMap中修改
     * @return:
     * @Author: huangbo
     * @Date: 2019/8/3
     */
    public static boolean checkHoliday(Calendar src) {
        boolean result = false;
        if (holidayList == null) {
            initHolidayList();
        }
        //先检查是否是周六周日(有些国家是周五周六)
        if (src.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || src.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        for (Calendar c : holidayList) {
            if (src.get(Calendar.MONTH) == c.get(Calendar.MONTH)
                    && src.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)) {
                result = true;
            }
        }
        return result;
    }


    /**
     * 初始化节日List,如果需要加入新的节日,请在这里添加
     * 加的时候请尽量使用Calendar自带的常量而不是魔鬼数字
     * 注:年份可以随便写,因为比的时候只比月份和天
     *
     * @version [s001, 2010-11-19]
     * @author shenjunjie
     */
    private static void initHolidayList() {
        holidayList = new ArrayList();
        //五一劳动节
        Calendar may1 = Calendar.getInstance();
        may1.set(Calendar.MONTH, Calendar.MAY);
        may1.set(Calendar.DAY_OF_MONTH, 1);
        holidayList.add(may1);
        Calendar may2 = Calendar.getInstance();
        may2.set(Calendar.MONTH, Calendar.MAY);
        may2.set(Calendar.DAY_OF_MONTH, 2);
        holidayList.add(may2);
        Calendar may3 = Calendar.getInstance();
        may3.set(Calendar.MONTH, Calendar.MAY);
        may3.set(Calendar.DAY_OF_MONTH, 3);
        holidayList.add(may3);
        Calendar h3 = Calendar.getInstance();
        h3.set(2000, 1, 1);
        holidayList.add(h3);
        Calendar h4 = Calendar.getInstance();
        h4.set(2000, 12, 25);
        holidayList.add(h4);
        //中国母亲节：五月的第二个星期日
        Calendar may5 = Calendar.getInstance();
        //设置月份为5月
        may5.set(Calendar.MONTH, Calendar.MAY);
        //设置星期:第2个星期
        may5.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
        //星期日
        may5.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//        System.out.println(may5.getTime());
        holidayList.add(may5);
    }


    /** 
    * @Description: 两个日期之间的工作日天数
    * @Param: [strStartDate, strEndDate] 
    * @return: int 
    * @Author: huangbo
    * @Date: 2019/8/3 
    */
    public static int getDutyDays(String strStartDate,String strEndDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarENd = Calendar.getInstance();

        Date startDate=null;
        Date endDate = null;

        try {
            startDate=df.parse(strStartDate);
            calendarStart.setTime(startDate);
            endDate = df.parse(strEndDate);
            calendarENd.setTime(endDate);
        } catch (ParseException e) {
            System.out.println("非法的日期格式,无法进行转换");
            e.printStackTrace();
        }
        int result = 0;
        while (startDate.compareTo(endDate) <= 0) {
            if (calendarStart.get(Calendar.DAY_OF_WEEK) != 6 && calendarStart.get(Calendar.DAY_OF_WEEK) != 0)
                result++;
            //calendarStart.set(Calendar.DAY_OF_MONTH, 1);
            int i = calendarStart.get(Calendar.DAY_OF_MONTH);
            System.out.println(i);
            calendarStart.set(calendarStart.get(Calendar.DAY_OF_MONTH), 1);
            startDate = calendarStart.getTime();

          //  int k = calendarStart.get(Calendar.DAY_OF_MONTH);

          //  if (startDate.getDay() != 6 && startDate.getDay() != 0)
          //      result++;
          //  int d = startDate.getDate();
          ////  System.out.println(d);
            //startDate.setDate(startDate.getDate() + 1);
        }

        return result;
    }
    public static void main(String[] args){
        int dutyDays = getDutyDays("2019-07-27", "2019-08-05");
        System.out.println(dutyDays);
    }
}