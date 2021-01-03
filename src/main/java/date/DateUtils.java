package date;


import com.alibaba.fastjson.JSON;
import list.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static int getLastDay() {//获取当前日
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static int getNowDay() {//获取当前日
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static int getLastMonth() {//获取上个月
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,-1);
        int month = cal.get(Calendar.MONTH);
        return month + 1;
    }

    public static int getNowMonth() {//获取当前月
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        return month + 1;
    }

    public static int getLastYear() {//获取上一年
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    public static int getNowYear() {//获取当前年
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    public static int getDayOfWeek() {//获取当前星期几
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.DAY_OF_WEEK);
        return week - 1;
    }

    public static int getLastHourOfDay() {//获取上一小时
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public static int getHourOfDay() {//获取当前小时
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public static int getMinute() {//获取当前分
        Calendar cal = Calendar.getInstance();
        int minute = cal.get(Calendar.MINUTE);
        return minute;
    }

    public static int getSeconds() {//获取当前秒
        Calendar cal = Calendar.getInstance();
        int second = cal.get(Calendar.SECOND);
        return second;
    }

    public static String getDateString(String str) {//获取当前年月日
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(str));
        String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        return date;
    }

    public static Date getDateFromTimeStamp(String str) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(str));
        return cal.getTime();
    }

    public static String getTimeString(String str) {//获取当前年月日时分秒
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(str));
        String time = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        return time;
    }

    public static String getTimeStamp() {//获取时间戳
        Calendar cal = Calendar.getInstance();
        String str = "" + cal.getTimeInMillis();
        return str;
    }

    //获取时分秒的时间戳
    public static String getHMSStamp() {
        long result = 0;
        Calendar c = Calendar.getInstance();
        result = c.getTimeInMillis() % (24 * 60 * 60 * 1000);
        return result + "";
    }

    //将时间戳转换成时间字符串
    public static String getDateByTime(Date date) {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_");
        String dateString = sdf.format(date);
        return dateString;
    }


    public static String getFormatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(date);
        return dateString;
    }


    public static Date getDateByString(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
        }
        return date;
    }

    public static String getDspDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return week + "" + hour;
    }

    public static String addDate(Date dt, long day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        long time = dt.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return sdf.format(new Date(time));
    }

    public static long differentDays(String end, String start) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        long diff = 2;
        try {
            Date date1 = sdf.parse(end);
            Date date2 = sdf.parse(start);
            diff = (date1.getTime() - date2.getTime()) / (1000 * 3600 * 24) + 1;
            return diff;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    public static void main(String[] args) throws ParseException {
        /*String str1 = "20201013";
        String str2 = "20201014";
        long l = differentDays(str2, str1);
        System.out.println(l);*/

       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String str1 = "20201010";
        String s = addDate(sdf.parse(str1), -1);
        System.out.println(s);*/


        /*String startHour = "20201015";
        String endHour = "20201015";
        System.out.println(startHour.compareTo(endHour) > 0);


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dt = dateFormat.parse("20201023");
        System.out.println(dt);
*/
        /*long t1 = System.currentTimeMillis();

        String time_scale = "[\"10\",\"11\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"110\",\"111\",\"112\",\"113\",\"114\",\"115\",\"116\",\"117\",\"118\",\"119\",\"120\",\"121\",\"122\",\"123\",\"20\",\"21\",\"24\",\"25\",\"26\",\"27\",\"28\",\"29\",\"210\",\"211\",\"212\",\"213\",\"214\",\"215\",\"216\",\"217\",\"218\",\"219\",\"220\",\"221\",\"222\",\"223\",\"30\",\"31\",\"34\",\"35\",\"36\",\"37\",\"38\",\"39\",\"310\",\"311\",\"312\",\"313\",\"314\",\"315\",\"316\",\"317\",\"318\",\"319\",\"320\",\"321\",\"322\",\"323\",\"40\",\"41\",\"44\",\"45\",\"46\",\"47\",\"48\",\"49\",\"410\",\"411\",\"412\",\"413\",\"414\",\"415\",\"416\",\"417\",\"418\",\"419\",\"420\",\"421\",\"422\",\"423\",\"50\",\"51\",\"54\",\"55\",\"56\",\"57\",\"58\",\"59\",\"510\",\"511\",\"512\",\"513\",\"514\",\"515\",\"516\",\"517\",\"518\",\"519\",\"520\",\"521\",\"522\",\"523\",\"60\",\"61\",\"64\",\"65\",\"66\",\"67\",\"68\",\"69\",\"610\",\"611\",\"612\",\"613\",\"614\",\"615\",\"616\",\"617\",\"618\",\"619\",\"620\",\"621\",\"622\",\"623\",\"00\",\"01\",\"04\",\"05\",\"06\",\"07\",\"08\",\"09\",\"010\",\"011\",\"012\",\"013\",\"014\",\"015\",\"016\",\"017\",\"018\",\"019\",\"020\",\"021\",\"022\",\"023\"]";
        String dspDate = DateUtils.getDspDate();
        int week = DateUtils.getDayOfWeek();
        int hourOfDay = DateUtils.getHourOfDay();

        String replace = time_scale.replace("\"", "");
        String substring = replace.substring(1, replace.length() - 1);

        String[] timeScale = substring.split(",");

        //int time_window = 10;
        Integer time_window = new Integer(10);

        int hour = 0;
        int startIndex = 0;
        for (int i = 0; i < timeScale.length; i++) {
            if (timeScale[i].equals(dspDate)) {
                startIndex = i;
                break;
            }
        }
        boolean currentHour = false;
        for (int i = startIndex ; i < timeScale.length; i++) {
            if (timeScale[i].startsWith(String.valueOf(week))) {
                String substring1 = timeScale[i].substring(1, timeScale[i].length());
                if (substring1.equals(String.valueOf(hourOfDay))) {
                    currentHour = true;
                }
                if (Integer.parseInt(substring1) > hourOfDay) {
                    hour++;
                }
            } else {
                break;
            }
        }

        System.out.println("剩余小时数：" + hour);
        int a = hour * 60 / time_window;
        System.out.println("后面整数个小时数的10分钟的个数：" + a);

        int minute = DateUtils.getMinute();
        System.out.println("当前分：" + minute);
        int b = 1;
        if (currentHour) {
            b = (59 - minute) / time_window + 1;
        }
        System.out.println("该小时里的10分钟的个数：" + b);

        System.out.println("剩余时间的10分钟个数：" + (a + b));

        long t4 = System.currentTimeMillis();
        System.out.println("总耗时：" + (t4 - t1));*/
    }
}
