package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ThreadTest {

    public int task1() throws InterruptedException {
        Thread.sleep(100);
        return 100;
    }

    public int task2() throws InterruptedException {
        Thread.sleep(300);
        return 300;
    }

    public int task3() throws InterruptedException {
        Thread.sleep(500);
        return 500;
    }

    public int task4() throws InterruptedException {
        Thread.sleep(800);
        return 800;
    }

    public int task5() throws InterruptedException {
        Thread.sleep(2000);
        return 2000;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ThreadTest thread = new ThreadTest();

        // 100ms
        Future<Integer> future1 = ThreadUtils.submit(() -> {
            int i = thread.task1();
            return i;
        });
        // 300ms
        Future<Integer> future2 = ThreadUtils.submit(() -> {
            int i = thread.task2();
            return i;
        });
        // 500ms
        Future<Integer> future3 = ThreadUtils.submit(() -> {
            int i = thread.task3();
            return i;
        });
        // 800ms
        Future<Integer> future4 = ThreadUtils.submit(() -> {
            int i = thread.task4();
            return i;
        });
        // 2000ms
        Future<Integer> future5 = ThreadUtils.submit(() -> {
            int i = thread.task5();
            return i;
        });

        Integer integer5 = future5.get();
        System.out.println(integer5);

        Integer integer1 = future1.get();
        System.out.println(integer1);
        Integer integer2 = future2.get();
        System.out.println(integer2);
        Integer integer3 = future3.get();
        System.out.println(integer3);
        Integer integer4 = future4.get();
        System.out.println(integer4);



        long end = System.currentTimeMillis();

        long time = end - start;
        System.out.println("耗时："+time);

        System.exit(0);
    }
}
