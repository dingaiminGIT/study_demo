package javabase;

public class ATest {



    public static void main(String[] args) {
        /*System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println("ddd");

        System.out.println(Integer.MAX_VALUE);*/

        float a = 0.0f / 0.0f;
        float b = 1.0f;
        float c = Float.NaN;
        System.out.println(a); // NaN
        System.out.println(Float.isNaN(a)); // true
        System.out.println(a != a); // true
        System.out.println(c != c); // true
        System.out.println(Float.NaN != Float.NaN); // true
        System.out.println(0.0f / 0.0f != 0.0f / 0.0f); // true
        System.out.println(Float.isNaN(b)); // false
        System.out.println(b != b); // false
        System.out.println(1.0f != 1.0f); // false
    }
}
