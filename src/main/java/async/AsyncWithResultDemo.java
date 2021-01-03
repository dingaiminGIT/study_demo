package async;


import java.util.concurrent.*;

public class AsyncWithResultDemo {
    private final static int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(PROCESSORS,
            PROCESSORS * 2,
            1, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future future = POOL_EXECUTOR.submit((Callable) AsyncWithResultDemo::doA);

        // 同步等待结果
        System.out.println(future.get());
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
}
