package javabase;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

public class FloatTest {

    @Test
    public void test2() {
        float a = 14.1f;
        double b = a * 1.0d;
        System.out.println(b);
        System.out.println("------------");

        Float f = new Float(14.1);
        System.out.println(f.floatValue());
        System.out.println(f.doubleValue());
        System.out.println(Double.parseDouble(f.floatValue() + ""));

    }

    @Test
    public void test1() {
        JSONObject object = new JSONObject();
        float a = 1;
        object.put("a", a);

        Double a1 = object.getDouble("a");
        System.out.println(a1);
    }

    @Test
    public void test() {
        float price = 154544;
        System.out.println(price);

        Dog dog = new Dog();
        System.out.println(dog);
    }

    class Dog {
        long a;

        public long getA() {
            return a;
        }

        public void setA(long a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "a=" + a +
                    '}';
        }
    }

    @Test
    public void test111() {
        // System.out.println(1.0 / 0); // Infinity
        // System.out.println(0 / 0); // java.lang.ArithmeticException: / by zero
        // System.out.println(0.0 / 0.0 + 1.0); // NaN
        // System.out.println(Double.doubleToLongBits(Double.NEGATIVE_INFINITY));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(Float.NEGATIVE_INFINITY)));
    }
}
