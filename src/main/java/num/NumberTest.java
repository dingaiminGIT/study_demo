package num;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    public void test2() {
        // sucess
        Long l = 1L;
        double d = (double) l;

        Object o = l;
        // sucess
        long l2 = (long) o;

        // fail
        d = (double) o;
    }

    @Test
    public void test3() {
        JSONObject obj = new JSONObject();
        obj.put("a", 1L);
        Object a = obj.get("a");
        double v = (double) a;
        /*if (a instanceof Long) {
            System.out.println(Double.parseDouble(a+""));
        }*/
        /*long b = 1L;
        double v = (double) b;
        System.out.println(v);*/
    }



    @Test
    public void test() {
        String dt = "2020-10-29";
        String startHour = "01";
        String endHour = "10";

        dt = dt.replaceAll("-", "");
        int start = Integer.parseInt(startHour);
        int end = Integer.parseInt(endHour);

        for (int i = start; i <= end; i++) {
            if (i < 10) {
                System.out.println(dt + "0" + i);
            } else {
                System.out.println(dt + i);
            }
        }
    }
}
