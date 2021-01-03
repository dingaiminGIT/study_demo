package settest;

import org.junit.jupiter.api.Test;

import java.util.*;

public class SetTest {

    @Test
    public void removeTest() {
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            it.remove();
            System.out.println(it.next());
        }
    }

    @Test
    public void testsort() {
        Integer[] dimensions= new Integer[]{1,3,2,4};
        List<Integer> list = Arrays.asList(dimensions);
        Set<Integer> set = new TreeSet<>(new MyComparator());
        set.addAll(list);

        for (Iterator<Integer> iterator =set.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
    }




    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            break;
        }
    }
}

class MyComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}