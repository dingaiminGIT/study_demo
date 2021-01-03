package java8plus;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {

    @Test
    public void test3333() {
        String str = "5,21";
        String[] split = str.split(",");
        List<String> list = Arrays.asList(split);
        Set<Integer> set = list.stream().map(tag -> Integer.parseInt(tag)).collect(Collectors.toSet());
        System.out.println(set.contains(5));
        System.out.println(set);

        Set<Integer> set1 = Stream.of(8, 11).collect(Collectors.toSet());
        System.out.println(set1);
        System.out.println(set1.contains(8));
    }

    @Test
    public void test1() {
        // 方法引用语法  ::
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
    }

    @Test
    public void test2() {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(Color.GREEN, 100));
        apples.add(new Apple(Color.GREEN, 170));
        apples.add(new Apple(Color.RED, 180));
        apples.add(new Apple(Color.GREEN, 90));
        apples.add(new Apple(Color.RED, 50));

        // 匿名内部类
        /*apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o2.getWeight().compareTo(o1.getWeight());
            }
        });*/

        //apples.sort((a1, a2) -> {return a1.getWeight().compareTo(a2.getWeight())});
        //apples.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        apples.sort(Comparator.comparing(Apple::getWeight));// 默认是升序


        apples.stream().forEach(System.out::println);

        /*List<Apple> result = filter(apples, apple -> Color.RED.equals(apple.getColor()));
        result.stream().forEach((System.out::println));*/

    }




    @Test
    public void test3() {
        Runnable r1 = () -> System.out.println("Hello World");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World3"));

    }

    @Test
    public void predictTest() {
        IntPredicate evenNumbers = i -> i % 2 == 0;
        System.out.println(evenNumbers.test(1000));
    }

    @Test
    public void ConsumerTest() {
        forEach(Arrays.asList(1,2,3,4,5), i -> System.out.println(i));

        List<String> list = new ArrayList<>();
        list.add("a");

        Consumer<String> b = s -> list.add(s);
    }

    @Test
    public void functionTest() {
        List<Integer> l = map(Arrays.asList("lambdas", "in", "action"), s -> s.length());
        // 7,2,6
        l.stream().forEach(System.out::println);
    }

    @Test
    public void lambdaTest() {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(Color.GREEN, 100));
        apples.add(new Apple(Color.GREEN, 170));
        apples.add(new Apple(Color.RED, 170));
        apples.add(new Apple(Color.RED, 180));
        apples.add(new Apple(Color.GREEN, 90));
        apples.add(new Apple(Color.RED, 50));

        // 第1步：传递代码
//        apples.sort(new AppleComparator());

        // 第2步：使用匿名类
        /*apples.sort(new AppleComparator() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });*/

        // 第3步：使用 Lambda 表达式
        // apples.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // 第3+步：静态辅助方法
        // apples.sort(Comparator.comparing(apple -> apple.getWeight()));

        // 第4步：使用方法引用
        // apples.sort(Comparator.comparing(Apple::getWeight));

        // 如果想逆序怎么办？ reversed 方法
        // apples.sort(Comparator.comparing(Apple::getWeight).reversed());

        // 比较器链
        apples.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));

        apples.stream().forEach(System.out::println);

    }

    @Test
    public void test4() {

    }

    /**
     * 对应 Predicate
     *
     * @param list
     * @param p
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 对应 Consumer
     *
     * @param list
     * @param c
     * @param <T>
     */
    public <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    /**
     * 对应 Function
     *
     * @param list
     * @param f
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    public static void process(Runnable r) {
        r.run();
    }

}

class AppleComparator implements Comparator<Apple> {

    @Override
    public int compare(Apple o1, Apple o2) {
        // 前 compareTo 后 是升序
        return o1.getWeight().compareTo(o2.getWeight());
    }
}
