package juc;

import java.util.concurrent.Semaphore;

/**
 * Semaphore相当于一个计数器，在操作的时候需要先获取许可，线程执行完后，在再释放，后续线程可以继续使用
 * 底层原理：利用AQS
 * 应用场景：限流 Hystrix的原理就是利用线程池或信号量
 * 学习指南：可以通过对Semaphore的学习进而学习Hystrix，深化理解
 *
 */
public class SemaphoreSample {

    public static void main(String[] args) {
        System.out.println("开始");
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SemaphoreWorker(semaphore));
            thread.start();
        }
    }
}

class SemaphoreWorker implements Runnable {

    private String name;
    private Semaphore semaphore;

    public SemaphoreWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            log("is waiting for a permit");
            semaphore.acquire();
            log("acquire a permit");
            log("executed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log("release a permit");
            semaphore.release();
        }
    }

    private void log(String msg) {
        if (name == null) {
            name = Thread.currentThread().getName();
        }
        System.out.println(name + " " +msg);
    }
}
