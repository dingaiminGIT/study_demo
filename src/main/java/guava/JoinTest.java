package guava;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;
import utils.SeriUtils;

import java.util.*;

public class JoinTest {
    @Test
    public void test4() throws Exception {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("dspid", "28,17");
        queryMap.put("a", "1");
        String s = SeriUtils.writeObject(queryMap);
        System.out.println(s);


        StringBuilder param = new StringBuilder();
        System.out.println(param.length());


    }

    @Test
    public void test3() {
        List<Integer> list = new ArrayList<>();
        list.add(28);
        list.add(17);

        String dspIds = Joiner.on(",").join(list);

        JSONObject object = new JSONObject();
        object.put("a", "1");
        object.put("dspid", dspIds);

        System.out.println(object.toJSONString());
    }

    @Test
    public void test1() {
        StringBuffer start = new StringBuffer("begin");
        StringBuffer a = new StringBuffer("AND adxid IN ('");
        Integer[] b = new Integer[]{1,2,3,4};
        //String[] b = new String[]{"1","2","3","4"};
        a.append(Joiner.on("','").join(b)).append("')");
        System.out.println(a);
    }

    @Test
    public void test2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("");
        list.add("2");

        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("4");
        set.add("5");
        set.add("6");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Joiner.on("','").join(list));
        System.out.println(stringBuffer);
    }

    @Test
    public void test() {
        String a = ":";
        //Integer integer = Integer.valueOf(a);
        int i = Integer.parseInt(a);
        System.out.println(i);
        //System.out.println(integer);
    }
 }
