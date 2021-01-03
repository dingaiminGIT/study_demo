package thread;

import com.alibaba.csp.sentinel.util.TimeUtil;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadPoolTest {

    @Test
    public void oom1() throws InterruptedException {
        //ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        // 循环 1 亿次，向线程池中提交任务
        for (int i = 0; i < 100000000; i++) {
            pool.execute(() -> {
                String payLoad = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();

                System.out.println("-----");
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(payLoad);
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);
    }
}
