package function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }

        long begin = System.currentTimeMillis();
        // 普通for循环
        for (Integer a: list
             ) {
            a.toString();
        }
        long end = System.currentTimeMillis();
        System.out.println("普通forEach用时：" + (end-begin));

        long begin11 = System.currentTimeMillis();
        // 普通for循环
        for (Integer a: list
             ) {
            a.toString();
        }
        long end11 = System.currentTimeMillis();
        System.out.println("普通forEach用时：" + (end11-begin11));

        long begin12 = System.currentTimeMillis();
        // 普通for循环
        for (Integer a: list
             ) {
            a.toString();
        }
        long end12 = System.currentTimeMillis();
        System.out.println("普通forEach用时：" + (end12-begin12));

        long begin2 = System.currentTimeMillis();
        // lambda
        list.forEach(i ->{i.toString();});
        long end2 = System.currentTimeMillis();
        System.out.println("lambda.forEach用时：" + (end2-begin2));

        long begin21 = System.currentTimeMillis();
        // lambda
        list.forEach(i ->{i.toString();});
        long end21 = System.currentTimeMillis();
        System.out.println("lambda.forEach用时：" + (end21-begin21));

        long begin22 = System.currentTimeMillis();
        // lambda
        list.forEach(i ->{i.toString();});
        long end22 = System.currentTimeMillis();
        System.out.println("lambda.forEach用时：" + (end22-begin22));

        /*long begin3 = System.currentTimeMillis();
        // stream
        list.stream().forEach(i -> i.toString());
        //list.stream().parallel();
        long end3 = System.currentTimeMillis();
        System.out.println("stream用时：" + (end3-begin3));

        long begin4 = System.currentTimeMillis();
        // 多stream
        list.parallelStream().forEach(i -> i.toString());
        long end4 = System.currentTimeMillis();
        System.out.println("多stream用时：" + (end4-begin4));*/

    }
}
