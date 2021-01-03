package date;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

public class TestT {

    public static void main(String[] args) {
        String timeStr = "1581936383";
        Long updateTime = Long.valueOf(timeStr);
        long now = LocalDateTime.now().toEpochSecond(UTC);
        System.out.println(now);
        long time =  (now - updateTime)/60;
        System.out.println(time);
    }

    @Test
    public void timestamp() throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // String 转 Date
        String str = "2020-2-17 14:17:20";
        System.out.println(sf.parse(str));

        // 时间戳的字符串转Date
        String tsString = "1556788591462";
        Timestamp ts = new Timestamp(Long.parseLong(tsString));
        System.out.println(sf.format(ts));
    }

}
