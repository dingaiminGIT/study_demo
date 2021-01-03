package javabase;

import org.junit.jupiter.api.Test;

public class IfTest {

    @Test
    public void test1() {
        if (1==1) {
            System.out.println("1");
        } else if (2 ==2 ) {
            System.out.println("2");
        }
    }

    @Test
    public void ifTest() {
        if (true)
            System.out.println("1");
        System.out.println("2");
        System.out.println("2");
    }
}
