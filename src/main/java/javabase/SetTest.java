package javabase;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

    @Test
    public void test1() {
        Set<String> set = new HashSet<>(12);
        set.add("1");
        System.out.println(set.size());

    }

}
