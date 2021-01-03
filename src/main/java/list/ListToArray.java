package list;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListToArray {

    @Test
    public void listToArrayTest() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Object[] array = list.toArray();

        String[] text = new String[3];
        String[] strings = list.toArray(text);


    }

}
