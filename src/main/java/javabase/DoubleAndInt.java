package javabase;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DoubleAndInt {

    @Test
    public void test1() {
        double a = 3.1;
        Integer b = 1;
        System.out.println(a > b);
        a = b + 1;
        System.out.println(a);

        System.out.println(b == 1);
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        List<String> list2 = new ArrayList<>();
        list2.add("3");
        list2.add("a");

        list.removeAll(list2);

        for (String into: list) {
            System.out.println(into);
        }

        // 不能边遍历list,再remove
        /*for (String info : list) {
            if ("a".equals(info)) {
                list.remove(info);
            }
        }

        for (String a : list) {
            System.out.println(a);
        }
*/
    }

    @Test
    public void test3() {
        String a = "dfdfdf";
        String b = "a";
        if (b.contains(a)) {
            System.out.println(a);
        }
    }

    @Test
    public void test4() {
        Double a = 12.34;
        System.out.println(a*100);
    }

    @Test
    public void test99() {
        Double a = 3.26707270863E7;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    }

}
