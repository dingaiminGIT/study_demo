package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtils2 {

    /**
     * 日期格式:yyyy-mm-dd<br>
     * 例如:2005-11-02
     */
    public final static String DATE_PATTERN_LINE = "yyyy-MM-dd";
    /**
     * 日期格式:yyyy-mm<br>
     * 例如:2005-11-02
     */
    public final static String MONTH_PATTERN_LINE = "yyyy-MM";
    /**
     * 日期格式:yyyy/mm/dd<br>
     * 例如:2005/11/02
     */
    public final static String DATE_PATTERN_BIAS = "yyyy/MM/dd";
    /**
     * 日期时间格式(24小时制):yyyy-mm-dd HH:mm:ss<br>
     * 例如:2005-11-02 23:01:01
     */
    public final static String DATETIME24_PATTERN_LINE = "yyyy-MM-dd HH:mm:ss";


    public final static String DATETIME24_PATTERN_LINE2 = "yyyy-MM-dd HH:mm";


    public final static String DATETIME24_PATTERN_LIN_UTC = "yyyy-MM-dd'T'HH:mm";


    public final static String DATETIME24_PATTERN_LIN_UTC_DRUID= "yyyy-MM-dd'T'";
    /**
     * 日期时间格式(12小时制):yyyy-mm-dd hh:mm:ss<br>
     * 例如:2005-11-02 11:01:01
     */
    public final static String DATETIME12_PATTERN_LINE = "yyyy-MM-dd hh:mm:ss";
    /**
     * 日期时间格式(24小时制):yyyy/mm/dd HH:mm:ss<br>
     * 例如:2005/11/02 23:01:01
     */
    public final static String DATETIME24_PATTERN_BIAS = "yyyy/MM/dd HH:mm:ss";
    /**
     * 日期时间格式(12小时制):yyyy/mm/dd hh:mm:ss<br>
     * 例如:2005/11/02 11:01:01
     */
    public final static String DATETIME12_PATTERN_BIAS = "yyyy/MM/dd hh:mm:ss";
    /**
     * 日期时间格式(24小时制):yyyy-mm-dd HH:mm:ss<br>
     * 例如:2005-11-02 23:01:01
     */
    public final static String DATETIME24_NO_LINE = "yyyyMMddHHmmss";


    /**
     * * <p>Description:UTC时间转化为本地时间 </p>
     * * @param utcTime   * @return   * @author wgs   * @date 2018年10月19日 下午2:23:24   *
     */

    public static String utcToLocal(String utcTime,String  localFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utcDate = null;
        try {
            utcDate = sdf.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat localsdf = new SimpleDateFormat(localFormat);
        String localTime = localsdf.format(utcDate.getTime());
        return localTime;
    }

    public static Date addDate(Date dt, long day) throws ParseException {
        long time = dt.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }


    public static List<String> getGapMinuteTonow(int n) throws ParseException {
        List<String> dateList = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_PATTERN_LINE);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_PATTERN_LINE);
        SimpleDateFormat sdf3 = new SimpleDateFormat(DATETIME24_PATTERN_LINE);
        long begintime = sdf2.parse(sdf1.format(date)).getTime();
        long newTimes = date.getTime();

        while (begintime <= newTimes) {
            begintime += 1000 * 60 * n;
            String minFormat = sdf3.format(begintime).split(" ")[1];
            dateList.add(minFormat);
        }
        return dateList;
    }

    public static List<String> getGapMinuteAll(int n) throws ParseException {
        String data1="2019-01-01";
        String data2="2019-01-02";
        SimpleDateFormat dateFormate = new SimpleDateFormat(DateUtils2.DATE_PATTERN_LINE);
        Date dt1 = dateFormate.parse(data1);
        Date dt2 = dateFormate.parse(data2);

        SimpleDateFormat localsdf = new SimpleDateFormat(DATETIME24_PATTERN_LINE);
        List<String> dateList=new ArrayList<>();
        long time = dt1.getTime();
        long newTimes=dt2.getTime();
        while (time<newTimes){
            time+=1000*60*n;
            String minFormat=localsdf.format(time).split(" ")[1];
            dateList.add(minFormat);
        }
        return dateList;
    }

    public static List<String> getGapHourAll() {
       return null;
    }

    public static void main(String[] args) {

    }
}
