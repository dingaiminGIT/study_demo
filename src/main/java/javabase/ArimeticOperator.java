package javabase;

import org.junit.jupiter.api.Test;

/**
 * 算数运算符
 */
public class ArimeticOperator {

    @Test
    public void testIadd() {
        int n = 0;
        n = n++;
        System.out.println("n=" + n); // 0 为何不是1？

        int i = 0;
        int i2 = i++;
        int j = 0;
        int j2 = ++j;
        System.out.println("i2=" + i2); // 0
        System.out.println("i=" + i); // 1
        System.out.println("j2=" + j2); // 1
    }

    /**
     * 浮点数精度问题
     */
    @Test
    public void forTest() {
        for (float i = 0; i != 1; i += 0.1) {
            System.out.println("hi," + i);
        }
        /*
        会无限循环，因为i从 0.9000001 直接跳到 1.0000001
        hi,0.0
        hi,0.1
        hi,0.2
        hi,0.3
        hi,0.4
        hi,0.5
        hi,0.6
        hi,0.70000005
        hi,0.8000001
        hi,0.9000001
        hi,1.0000001
        hi,1.1000001
        */
    }

    /**
     * 负数取余数，取整
     */
    @Test
    public void test1() {
        int i = -4;
        System.out.println(i % 2); // 0   -4 / 2 = -2, 余数为 0
        System.out.println(i % 2 == 1 || i % 2 == -1);
    }
}
