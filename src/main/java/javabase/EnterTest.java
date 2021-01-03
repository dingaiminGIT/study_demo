package javabase;

import org.junit.jupiter.api.Test;

public class EnterTest {

    @Test
    public void test1() {
        String a = "abc\n" + "def\n";
        System.out.println(a);
        System.out.println("dddd\rdfdfd");
        System.out.println("4444\r\ndddd");

/*      abc
        def

        dfdfd
        4444
        dddd*/
    }
}
