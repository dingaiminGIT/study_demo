package javabase;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BinaryTest {

    @Test
    public void test1() {
        int var = 6;
        System.out.println((var & -var) == var);
        System.out.println(-var);
    }

    @Test
    public void testAtomicInteger() {
        Map<String, AtomicInteger> map = new HashMap<>();
        map.put("1", new AtomicInteger(0));
        AtomicInteger atomicInteger = map.get("2");
        System.out.println(atomicInteger == null);
    }
}
