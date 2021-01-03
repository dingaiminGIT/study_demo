package javabase;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class NumberTest {

    @Test
    public void logTest() {
        // 换底公式
        int n = 8;
        double a = Math.log(n) / Math.log(2);
        System.out.println(a);
    }


    @Test
    public void test0() {
        String a = "0.0";
        double v = Double.parseDouble(a);
        System.out.println(v == 0);
        System.out.println(v);

    }

    @Test
    public void test1() {
        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#0.00");

        long a = 237428734233L;
        System.out.println(a);
        System.out.println((float) a /100.00);
        System.out.println(decimalFormat.format((double) a/100.00));
        System.out.println(decimalFormat.format((a/100.00)));
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");

        for (String a : list) {
            System.out.println(a);
        }
    }

    @Test
    public void test3() {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        // 去掉逗号
        numberFormat.setGroupingUsed(false);
        /*Integer a = new Integer(1343);
        Integer b = new Integer(34343);*/

        int c = 1343;
        int d = 34343;
        Double aDouble = new Double(c);
        Double bDouble = new Double(d);
        String format = numberFormat.format(aDouble/bDouble);
        System.out.println(format);
    }

    @Test
    public void test5555() {
        Integer a = 100;
        int i = a.intValue();
        Integer integer = Integer.valueOf(i);

        long c = 1111L;
        long d = 2222L;

        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        // 去掉逗号
        numberFormat.setGroupingUsed(false);

        String format = numberFormat.format(c / d);
        System.out.println(format);
        Double cost = new Double(12);
        Double win_count = new Double(2);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String format1 = decimalFormat.format((cost * 1.0 / (double) win_count) / 100.00);
        System.out.println(format1);

    }

    @Test
    public void testDsp() {
        double oldOrderbudget = 222574.99;
        double oldCost = 601.01;

        double newOrderbudget = 222253.39;
        double newCost = 897.62;

        System.out.println("cost:" + (newCost - oldCost));
        System.out.println("budget:" + (oldOrderbudget - newOrderbudget));
    }

}
