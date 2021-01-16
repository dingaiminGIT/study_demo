package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class HashMapNotSafe {

    public static void main(String[] args) {
        final Map<Integer, String> map = new HashMap<>();
        final Integer targetKey = 0b1111_1111_1111_1111;
        final String targetValue = "v";

        map.put(targetKey, targetValue);

        new Thread(() -> IntStream.range(0, targetKey).forEach(key -> map.put(key, "somevalue"))).start();

        while (true) {
            if (null == map.get(targetKey)) {
                throw new RuntimeException("HashMap is not thread safe");
            }
        }
    }
}
