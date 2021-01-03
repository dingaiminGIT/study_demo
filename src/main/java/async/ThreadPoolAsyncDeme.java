package async;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.*;

public class ThreadPoolAsyncDeme {

    private final static int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(PROCESSORS,
            PROCESSORS * 2,
            1, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void submitTest() throws ExecutionException, InterruptedException {
        Future<Result> submit = POOL_EXECUTOR.submit(() -> {
            System.out.println("ddd");
        }, new Result("result"));

        Result result = submit.get();
        System.out.println(result);
    }

    public static void main(String[] args) throws InterruptedException {
        long start = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        // 用线程池异步执行A
        POOL_EXECUTOR.submit(ThreadPoolAsyncDeme::doA);

        //doB();

        POOL_EXECUTOR.submit(ThreadPoolAsyncDeme::doB);

        // 等待A执行结束
        long end = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        System.out.println("耗时：" + (end - start));

        String name = Thread.currentThread().getName();
        System.out.println(name);

        // 挂起当前线程，不会执行后续操作
        Thread.currentThread().join();

        System.out.println("after");
    }

    public static void doA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doA");
    }

    public static void doB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doB");
    }
}

class Result {
    private String str;

    public Result(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Result{" +
                "str='" + str + '\'' +
                '}';
    }
}
