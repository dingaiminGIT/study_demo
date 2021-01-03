package async;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class AsyncFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        // 创建Future 任务
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            String result = null;
            try {
                result = doA();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        // 异步执行任务A
        Thread thread = new Thread(futureTask, "threadA");
        thread.start();

        // 执行任务B
        String resultB = doB();

        // 同步等待任务A结束
        String resultA = futureTask.get();


        // 结果
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
