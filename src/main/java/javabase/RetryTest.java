package javabase;

import org.junit.jupiter.api.Test;

public class RetryTest {

    public static void main(String[] args) {
        retry:
        for (;;) {
            System.out.println("for loop");

            break retry;
        }
    }

    @Test
    public void test() {
        int retryCount = 0;
        boolean callSwitch = true;
        while (callSwitch && retryCount <= 2) {
            try {
                System.out.println("调用");
                if (retryCount <= 1) {
                    System.out.println(1/0);
                }
                callSwitch = false;
                System.out.println("不用重试");
            } catch (Exception e) {
                callSwitch = true;
                retryCount++;
                System.out.println("重试，" + retryCount);
            }
        }
    }
}
