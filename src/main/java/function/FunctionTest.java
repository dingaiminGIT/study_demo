package function;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class FunctionTest {

    public static void main(String[] args) {
        int a = 1;
        Function<Integer, String> fun = (x) -> String.valueOf(x);
        String res = fun.apply(10);
        System.out.println(res);
    }

    @Test
    public void test() {
        int a = 1;
        Function<Integer, Integer> fun = (x) -> x + 10;
        Integer res = fun.apply(11);
        System.out.println(res);
    }
}
