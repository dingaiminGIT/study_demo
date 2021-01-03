package javabase;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class RegexTest {

    @Test
    public void test1() {
        String content = "02";
        String pattern = "^[1-9]\\d*$";
        //String pattern = "^\\+?[1-9][0-9]*$";
        boolean matches = Pattern.matches(pattern, content);
        System.out.println(matches);
    }

    @Test
    public void test2() {

    }

}
