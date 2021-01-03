package javabase;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StringBuilderTest {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("a").append("\001");
        sb.append("b").append("\001");
        System.out.println(sb.toString());
    }

    @Test
    public void jsonTest() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);

        StringBuilder sb = new StringBuilder();
        sb.append(Joiner.on(",").join(list));
        System.out.println(sb);

        String s = sb.toString();
        String[] split = s.split(",");
        for (String a : split) {
            System.out.println(a);
        }
    }

    @Test
    public void testsss() {
        int val = 3;
        System.out.println((val & -val) == val);
    }

    @Test
    public void test2() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("1");
        System.out.println(stringBuilder.toString());

        String a = "222";
        System.out.println(a);
    }

    @Test
    public void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("a").append("\u0001");
        sb.append("b").append("\u0001");
        System.out.println(sb.toString());
    }

    @Test
    public void stringBuilderTest() {
        long start = System.currentTimeMillis();
        String date = "2019-02-22";
        String hr = "14";
        String adxId = "1";
        String mediaId = "22";
        String slotId = "33";
        String cityId = "44";

        for (int i = 1; i < 1000; i++) {
            /*StringBuilder baseKey = new StringBuilder();
            baseKey.append(date).append("_").append(hr).append("_").append(adxId).append("_").append(mediaId).append("_").append(slotId).append("_").append(cityId);

            StringBuilder reqKey = new StringBuilder();
            reqKey.append("req_").append(baseKey);

            StringBuilder bidFloorKey = new StringBuilder();
            bidFloorKey.append("bidFloor_").append(baseKey);*/

            StringBuffer baseKey = new StringBuffer();
            baseKey.append(date).append("_").append(hr).append("_").append(adxId).append("_").append(mediaId).append("_").append(slotId).append("_").append(cityId);

            StringBuffer reqKey = new StringBuffer();
            reqKey.append("req_").append(baseKey);

            StringBuffer bidFloorKey = new StringBuffer();
            bidFloorKey.append("bidFloor_").append(baseKey);

           /* String baseKey = date + "_" + hr + "_" + adxId + "_" + mediaId + "_" + slotId + "_" + cityId;
            String reqKey = "req_" + baseKey;
            String bidFloorKey = "bidFloor_" + baseKey;*/

        }


        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(time);
    }
}
