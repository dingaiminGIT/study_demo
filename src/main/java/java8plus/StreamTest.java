/*
package java8plus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {

    private List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)
    );

    private List<Dish> specialMenu = Arrays.asList(
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER)
    );

    private List<String> words = Arrays.asList("Modern","Java","In","Action");

    private List<Integer> numbers = Arrays.asList(1,2,3,4,5);

    @Test
    public void test1() {
        List<String> threeHighCaloricDishNames =
                menu.stream()
                    .filter(dish -> dish.getCalories() > 300)
                    .map(Dish::getName)
                    .limit(3)
                    .collect(Collectors.toList());

        threeHighCaloricDishNames.stream().forEach(System.out::println);
    }

    */
/**
     * 从菜单中选出热量小于320卡的菜肴
     * 使用 filter 需要遍历整个流中的数据,对其中的每一个元素执行谓词判断
     *
     *//*

    @Test
    public void sliceBeforeTest() {
        List<Dish> filterList =
                specialMenu.stream()
                        .filter(dish -> dish.getCalories() < 320)
                        .collect(Collectors.toList());

        filterList.stream().forEach(System.out::println);
    }

    */
/**
     * 发现初始列表中的元素已经按照热量进行了排序操作，用 takeWhile 操作可以对流进行分片
     * 遇到第一个不符合要求的元素时就停止处理
     *//*

    @Test
    public void sliceTest() {
        List<Dish> sliceList =
                specialMenu.stream()
                        .takeWhile(dish -> dish.getCalories() < 320)
                        .collect(Collectors.toList());

        sliceList.stream().forEach(System.out::println);

        System.out.println("==================");

        List<Dish> sliiceMenu2 =
                specialMenu.stream()
                        .dropWhile(dish -> dish.getCalories() < 320)
                        .collect(Collectors.toList());

        sliiceMenu2.stream().forEach(System.out::println);

    }

    @Test
    public void limitTest() {
        List<Dish> dishes = specialMenu
                                .stream()
                                .filter(dish -> dish.getCalories() > 300)
                                .limit(3)
                                .collect(Collectors.toList());

        dishes.stream().forEach(System.out::println);
    }

    @Test
    public void skipTest() {
        List<Dish> dishes = specialMenu
                                .stream()
                                .filter(dish -> dish.getCalories() > 300)
                                .skip(2)
                                .collect(Collectors.toList());

        dishes.stream().forEach(System.out::println);
    }

    @Test
    public void mapTest() {
        List<String> dishNames = menu.stream()
                                     .map(Dish::getName)
                                     .collect(Collectors.toList());

        dishNames.stream().forEach(System.out::println);
    }

    @Test
    public void mapTest2() {
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        dishNameLengths.stream().forEach(System.out::println);
    }

    */
/**
     * 把 ["Hello","World"] --> ["H","e","l","l","o","W","o","r","l","d"]
     *//*

    @Test
    public void flatMapTest() {
        List<String> str = words.stream()
                                .map(word -> word.split(""))
                                .flatMap(Arrays::stream)
                                .distinct()
                                .collect(Collectors.toList());

        str.stream().forEach(System.out::println);
    }

    @Test
    public void flatMapTest2() {
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);
        List<int[]> pairs = numbers1.stream()
                                    .flatMap(i -> numbers2.stream()
                                                            .map(j -> new int[]{i,j}))
                                    .collect(Collectors.toList());

        pairs.stream().forEach(System.out::println);
    }

    @Test
    public void optionalTest() {
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println(dish.getName()));
    }

    @Test
    public void reduceTest() {
        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }

    @Test
    public void reduceTest2() {
        Integer sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // 重载方法，不需要初始值，返回是 Optional
        Optional<Integer> sum2 = numbers.stream().reduce(Integer::sum);
    }

    */
/*@Test
    public void IntStreamTest() {
        IntStream.rangeClosed(1, 100)
                    .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                    .boxed()
                    .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a*a + b*b)});
    }*//*

}
*/
