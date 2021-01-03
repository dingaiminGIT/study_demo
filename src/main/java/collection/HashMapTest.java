package collection;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class HashMapTest {

    @Test
    public void computeTest() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("3", 3);
        map.put("2", 2);

        // computeIfPresent 只有在指定的 key 存在时才进行计算并将结果放到 map 中
        map.computeIfPresent("1", (k, v) -> v + 1);
        map.computeIfPresent("1", (k, v) -> v + 1);
        // computeIfAbsent 只有在指定的 key 不存在时才进行计算并将结果放到 map 中
        map.computeIfAbsent("3", k -> Integer.valueOf(4));
        map.computeIfAbsent("4", k -> Integer.valueOf(4));
        // compute 不管指定的 key 存在与否，多会进行计算然后放到 map 中
        map.compute("5", (k, v) -> {
            if (v == null) {
                return 0;
            } else {
                return v + 1;
            }
        });
        map.compute("5", (k, v) -> {
            if (v == null) {
                return 0;
            } else {
                return v + 1;
            }
        });
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    @Test
    public void mapToList() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);

        List<String> list1 = new ArrayList<>(map.keySet());
        list1.forEach(System.out::println);

        List<Integer> list2 = new ArrayList<>(map.values());
        list2.forEach(System.out::println);


        List<String> keyList = map.keySet().stream()
                .collect(Collectors.toList());

        List<Integer> valueList = map.values().stream()
                .collect(Collectors.toList());

        keyList.forEach(System.out::println);
        valueList.forEach(System.out::println);
    }

    @Test
    public void sortTest() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("3", 3);
        map.put("2", 2);

        List<String> keyList = map.entrySet().stream() // 将 Map 转成 Stream
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // 按 value 逆序排序
                .map(e -> e.getKey()) // 映射
                .collect(Collectors.toList()); // 转成 List

        keyList.forEach(System.out::println);

    }
}
