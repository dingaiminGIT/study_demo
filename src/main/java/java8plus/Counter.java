package java8plus;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CountDownLatch;

/**
 * https://jekton.github.io/2018/07/22/java-translation-jep-193-Variable-Handles/
 */
public class Counter {

    private static VarHandle COUNT;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            COUNT = lookup.findVarHandle(Counter.class, "count", int.class);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private int count;

    public void increment() {
        COUNT.getAndAdd(this, 1);
    }

    public int get() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        int threads = 10;
        int num = 10000;
        CountDownLatch latch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                for (int j = 0; j < num; j++) {
                    counter.increment();
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        int count = counter.get();
        System.out.println(count);

    }
}
