package javabase;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void test() {
        long start = 1562663671205L;
        long end = 1562663671224L;
        long time = end - start;
        System.out.println(time);
    }

    @Test
    public void test2() {
        long now = System.currentTimeMillis();
        System.out.println(now);
        now = now / (60 * 1000); // 当前分钟
        System.out.println(now);
    }
}
