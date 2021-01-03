package date;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class DateTest {

    @Test
    public void test() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date today = new Date();

        String date = dateFormat.format(org.apache.commons.lang3.time.DateUtils.addDays(today, -1));
        System.out.println(date);

        String date1 = dateFormat.format(org.apache.commons.lang3.time.DateUtils.addDays(today, -2));
        System.out.println(date1);
    }

    private int getTimeWindowNum(String time_scale, Integer TIME_WINDOW) {

        String dspDate = DateUtils.getDspDate();
        int week = DateUtils.getDayOfWeek();
        int hour = DateUtils.getHourOfDay();
        int minute = DateUtils.getMinute();

        String replace = time_scale.replace("\"", "");
        String substring = replace.substring(1, replace.length() - 1);
        // 9999999 特殊处理
        if ("9999999".equals(substring)) {
            int hourOfDay = DateUtils.getHourOfDay();

            int a = (23 - hourOfDay) * 60 / TIME_WINDOW;
            int b = (59 - minute) / TIME_WINDOW + 1;
            return a + b;
        }
        String[] timeScale = substring.split(",");

        int remainHour = 0;
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
                String item = timeScale[i].substring(1, timeScale[i].length());
                // 判断投放时间是否包含当前小时
                if (item.equals(String.valueOf(hour))) {
                    currentHour = true;
                }
                // 只有投放时间的小时大于当前小时，才把剩余小时加1
                if (Integer.parseInt(item) > hour) {
                    remainHour ++;
                }
            } else {
                break;
            }
        }

        int a = remainHour * 60 / TIME_WINDOW;
        int b = 0;
        // 只有投放时间包含当前小时数才算分
        if (currentHour) {
            b = (59 - minute) / TIME_WINDOW + 1;
        }
        return a + b == 0 ? 1 : a + b;
    }

    @Test
    public void testdsp() {
        DateTest dateTest = new DateTest();
        String time_scale = "[\"13\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"110\",\"111\",\"112\",\"113\",\"114\",\"115\",\"116\",\"117\",\"118\",\"119\",\"120\",\"121\",\"122\",\"23\",\"24\",\"25\",\"26\",\"27\",\"28\",\"29\",\"210\",\"211\",\"212\",\"213\",\"214\",\"215\",\"216\",\"217\",\"218\",\"219\",\"220\",\"221\",\"222\",\"33\",\"34\",\"35\",\"36\",\"37\",\"38\",\"39\",\"310\",\"311\",\"312\",\"313\",\"314\",\"315\",\"316\",\"317\",\"318\",\"319\",\"320\",\"321\",\"322\",\"43\",\"44\",\"45\",\"46\",\"47\",\"48\",\"49\",\"410\",\"411\",\"412\",\"413\",\"414\",\"415\",\"416\",\"417\",\"418\",\"419\",\"420\",\"421\",\"422\",\"50\",\"51\",\"52\",\"53\",\"54\",\"55\",\"56\",\"57\",\"58\",\"59\",\"510\",\"511\",\"512\",\"513\",\"514\",\"515\",\"516\",\"517\",\"63\",\"64\",\"65\",\"66\",\"67\",\"68\",\"69\",\"610\",\"611\",\"612\",\"613\",\"614\",\"615\",\"616\",\"617\",\"618\",\"619\",\"620\",\"621\",\"622\",\"03\",\"04\",\"05\",\"06\",\"07\",\"08\",\"09\",\"010\",\"011\",\"012\",\"013\",\"014\",\"015\",\"016\",\"017\",\"018\",\"019\",\"020\",\"021\",\"022\"]";
        Integer integer = new Integer(10);
        int timeWindowNum = dateTest.getTimeWindowNum(time_scale, integer);
        System.out.println(timeWindowNum);

        Integer budget = 600;
        double consume =100d;
        double limit = (budget - consume) / 0;
        System.out.println(limit);

    }

    @Test
    public void test3333() {
        String time_scale = "[\"9999999\"]";
        String replace = time_scale.replace("\"", "");
        System.out.println(replace);
        String substring = replace.substring(1, replace.length() - 1);
        System.out.println(substring);

        System.out.println(29 /10);

    }

    @Test
    public void test44() {
        long timeMillis = System.currentTimeMillis();
        System.out.println(timeMillis / 10 / 60 / 1000);

        // 999999特殊处理
        int hourOfDay = DateUtils.getHourOfDay();
        int minute = DateUtils.getMinute();
        System.out.println(hourOfDay);
        System.out.println(minute);

        int a = (23 - hourOfDay) * 60 /10;
        int b = (60 - minute) % 10 > 0 ? (60 - minute) / 10 + 1 : (60 - minute) / 10;
        System.out.println(a+b);
    }

    @Test
    public void test1() {
        long l = (1L << 41) / (1000L * 3600 * 24 * 365);
        System.out.println(l);
    }

    @Test
    public void test333() {
        String start = "2019-07-18 09";
        String end = "2019-07-18 16";
        long startTime = DateUtils.getDateByString(start, "yyyy-MM-dd HH").getTime();
        long endTime = DateUtils.getDateByString(end, "yyyy-MM-dd HH").getTime();

        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void test2() {
        String start = "2019-05-08 09";
        String end = "2019-05-08 14";
        long startTime = DateUtils.getDateByString(start, "yyyy-MM-dd HH").getTime();
        long endTime = DateUtils.getDateByString(end, "yyyy-MM-dd HH").getTime();

        List<String> result  = new ArrayList<>();
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.setTime(new Date(startTime));
        beforeCalendar.add(Calendar.DAY_OF_YEAR,-1);
        System.out.println("before:" + beforeCalendar.getTime());
        //兼容8小时延迟
        result.add(new SimpleDateFormat("yyyy.MM.dd").format(beforeCalendar.getTime()));
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();


        startCalendar.setTime(new Date(startTime));
        endCalendar.setTime(new Date(endTime));
        int between = endCalendar.get(Calendar.DAY_OF_YEAR) - startCalendar.get(Calendar.DAY_OF_YEAR);
        System.out.println(between);
        for(int i = 0;i<=between;i++){
            result.add(new SimpleDateFormat("yyyy.MM.dd").format(startCalendar.getTime()));
            startCalendar.add(Calendar.DAY_OF_YEAR,1);
        }
        result.forEach((data)->{System.out.println(data);});

    }

    @Test
    public void test3() {
        String timeStr = "2020-02-10 09:00:06";
        LocalDateTime dateTime = LocalDateTime.parse(timeStr,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dateTime);
        long l = dateTime.toEpochSecond(UTC);
        System.out.println(l);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        long l1 = now.toEpochSecond(UTC);
        System.out.println(l1);

        long minite = (l1-l)/60;
        System.out.println(minite);
    }

    /**
     * 字符串转日期
     */
    @Test
    public void test4() {
        //double time = 1584615600000d;// 2020-03-19 19:00:00
        //String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        String time = "1500000000000";
        Timestamp result = new Timestamp(Long.parseLong(time));
        System.out.println(result);
    }

    /**
     * 字符串转时间戳
     */
    @Test
    public void test5() {
        String timeStr = "2020-03-19 19:00:00";
        //DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yy")
        LocalDateTime dateTime = LocalDateTime.parse(timeStr,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dateTime);
        System.out.println(LocalDateTime.from(dateTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

}
