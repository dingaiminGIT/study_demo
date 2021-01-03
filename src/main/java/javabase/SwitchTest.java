package javabase;

import org.junit.jupiter.api.Test;

public class SwitchTest {

    @Test
    public void siwtchTest1() {
        System.out.println("开始");
        Integer open = 1;
        switch (open) {
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            default:
                break;
        }

        System.out.println("switch后");
    }

    public static void main(String[] args) {
        SwitchTest switchTest = new SwitchTest();
        String re = switchTest.re();
        System.out.println(re);

    }

    public String re() {
        System.out.println("开始");
        Integer open = 1;
        boolean key = true;
        switch (open) {
            case 1:
                System.out.println("1");
                if (key) {
                    return "13333";
                }
                break;
            case 2:
                System.out.println("2");
                break;
            default:
                break;
        }
        return "";
    }

    /**
     * 没有执行体，会默认执行下一个case
     *
     */
    @Test
    public void testSwitch() {
        switch (1) {
            case 0:
                System.out.println("0");
                break;
            case 1:
            case 2:
                System.out.println("2");
                break;
            default:
                System.out.println("default");
                break;
        }
    }
}
