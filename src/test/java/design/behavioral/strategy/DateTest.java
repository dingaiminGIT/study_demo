package design.behavioral.strategy;

import date.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    public static Date getDateByString(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
        }
        return date;
    }

    @Test
    public void test1() {
        String start = "2018-07-02";
        String end = "2018-07-05";
        Date startDate = getDateByString(start, "yyyy-MM-dd");
        System.out.println(startDate);
        Date endDate = getDateByString(end, "yyyy-MM-dd");
        System.out.println(endDate.getTime() - startDate.getTime() > 2*24*60*60*1000);

    }

    @Test
    public void test2() {
        long time = System.currentTimeMillis();
        System.out.println(time);

        Date date = new Date();
        long time1 = date.getTime();
        System.out.println(time1);


    }

    @Test
    public void test3() {
        String start = "2019-05-07 09";
        String end = "2019-05-07 14";

        long stime = DateUtils.getDateByString(start, "yyyy-MM-dd HH").getTime();
        long etime = DateUtils.getDateByString(end, "yyyy-MM-dd HH").getTime();

        System.out.println(stime);
        System.out.println(etime);
    }

    @Test
    public void test5555() {
        String time = "2018-08-10 08:14";
        Date startDate = getDateByString(time, "yyyy-MM-dd HH:mm");
        System.out.println(startDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String format = sdf.format(startDate);
        System.out.println(format);

    }

    @Test
    public void test() {
        int a = 302817;
        System.out.println(a*1024);
    }
}
