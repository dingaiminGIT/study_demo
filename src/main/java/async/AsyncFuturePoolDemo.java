package async;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.*;

public class AsyncFuturePoolDemo {

    private final static int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(PROCESSORS,
            PROCESSORS * 2,
            1, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        // 创建 FutureTask
        FutureTask<String> futureTask = new FutureTask<>(()->{
            String result = null;
            try {
                result = doA();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        // 用线程池执行 FutureTask
        POOL_EXECUTOR.execute(futureTask);

        // 执行任务B
        String resultB = doB();

        // 同步等待FutureTaskA的结果
        String resultA = futureTask.get();

        // resultA:null, resultB:B 为什么 resultA 的结果是 null?
        // FutureTask<String> futureTask = new FutureTask<>(()-> doA()); 改为这样后，输出结果不为null了
        System.out.println("resultA:" + resultA + ", resultB:" + resultB);
        long end = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        System.out.println("耗时：" + (end - start));

    }

    public static String doA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doA");
        return "A";
    }

    public static String doB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doB");
        return "B";
    }
}
