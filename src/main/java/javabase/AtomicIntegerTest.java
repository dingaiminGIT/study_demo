package javabase;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    @Test
    public void absTest() {
        AtomicInteger idx = new AtomicInteger();
        Integer[] arrray = new Integer[]{1,2,3,4};

        for (int i = 0; i < arrray.length; i++) {
            Integer integer = arrray[Math.abs(idx.incrementAndGet() % arrray.length)];
            System.out.println(integer);
        }
    }
}
