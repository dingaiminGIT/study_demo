package collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        iterator.remove();// 报错，只允许移除一次

    }

    @Test
    public void Iterator2() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        Iterator<String> iterator = list.iterator();
        Iterator<String> iterator2 = list.iterator();
        iterator.next();
        iterator.remove();
        iterator2.next();
    }
}
