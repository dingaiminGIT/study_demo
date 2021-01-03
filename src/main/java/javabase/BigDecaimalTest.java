package javabase;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BigDecaimalTest {

    @Test
    public void test() {
        BigDecimal bigDecimal = new BigDecimal(0);
        System.out.println(bigDecimal.intValue() == 0);

        System.out.println("02".compareTo("02") > 0);
    }

    @Test
    public void test1() {
        JSONObject object = new JSONObject();
        object.put("COST", new BigDecimal(0.12));
        BigDecimal cost = object.getBigDecimal("COST");
        System.out.println(cost);
        System.out.println(object.getInteger("COST"));
    }


}
