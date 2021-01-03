package javabase;

import org.junit.jupiter.api.Test;

public class ThreeTest {

    @Test
    public void test() {

        int local1 = 1;
        int local2 = 0;
        int local3 = 0;
        int local = local3 == 0 ? (local2 == 0 ? local1 : local2) : local3;
        System.out.println(local);
    }
}
