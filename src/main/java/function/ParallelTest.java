package function;

import java.util.Collections;
import java.util.stream.LongStream;

public class ParallelTest {

    // a^b
    public long pow(final int a, final int  b){
        //return LongStream.range(0, b).reduce(1, (acc, x) -> a * acc); // 1073741824
        return LongStream.range(0, b).parallel().reduce(1, (acc, x) -> a * acc); // 32
        //return Collections.nCopies(b,a).parallelStream().reduce(1, (acc, x) -> acc * x); // 1073741824
    }

    public long pow2(final int a, final int  b){
        //return LongStream.range(0, b).reduce(1, (acc, x) -> a * acc); // 1073741824
        //return LongStream.range(0, b).parallel().reduce(1, (acc, x) -> a * acc); // 32
        return Collections.nCopies(b,a).parallelStream().reduce(1, (acc, x) -> acc * x); // 1073741824
    }

    public static void main(String[] args) {
        ParallelTest parallelTest = new ParallelTest();
        long pow = parallelTest.pow(2, 30);
        System.out.println(pow);

        long pow2 = parallelTest.pow2(2, 30);
        System.out.println(pow2);

    }
}
