package jvm;

import java.time.Instant;

/**
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+EliminateAllocations
 */
public class OnStack {

    public static void alloc() {
        User user = new User(1, "baiya");
    }

    public static void main(String[] args) {
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < 100_000_000; i++) {
            alloc();
        }
        long end = Instant.now().toEpochMilli();
        System.out.println("耗时：" + (end - start));
    }
}
