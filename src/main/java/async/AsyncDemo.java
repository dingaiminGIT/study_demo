package async;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AsyncDemo {

    @Test
    public void thread() throws InterruptedException {
        long start = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        // 开启异步单元执行异步任A
        // 匿名类方式
        Thread thread = new Thread("thread-a") {
            @Override
            public void run() {
                try {
                    doA();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        // 执行任务B
        doB();
        // 同步等待线程A运行结束
        // join 方法是判断线程是否活着，活着就等待
        thread.join();
        long end = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        System.out.println("耗时：" + (end - start));
    }

    public static void main(String[] args) throws InterruptedException {
        long start = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        // 异步执行任务A
        Thread thread = new Thread(() -> {
            try {
                doA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "threadA");

        thread.start();

        // 执行任务B
        doB();

        // 同步等待线程A运行结束
        // join 方法是判断线程是否活着，活着就等待
        thread.join();

        long end = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        System.out.println("耗时：" + (end - start));


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
