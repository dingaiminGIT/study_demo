package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier的用法和CountDownLatch用法相似，区别是：CyclicBarrier可以重用，而后者不行
 * 回环栅栏
 *
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int n = 100;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n);

        for (int i = 0; i < n; i++) {
            new Writer(cyclicBarrier).start();
        }

        try {
            // 等待CyclicBarrier回环
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        // 重用CyclicBarrier
        for (int i = 0; i < n; i++) {
            new Writer(cyclicBarrier).start();
        }
    }

    static class Writer extends Thread{
        CyclicBarrier barrier;

        public Writer(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "写数据");
            try {
                Thread.sleep(200);
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据结束");

                // 处于barrier状态
                // return the arrival index of the current thread,where index{getParties()-1} indicates the first to arrive and zero indicates the last to arrive
                // 当非最后一个线程调用await方法时，会循环，等待index=0，然后返回0，并唤醒其他等待线程  如果是最后一个线程调用，则返回0，并唤醒所有等待的线程
                barrier.await();
                System.out.println("已经breakBarrier，signal "+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            // 当所有线程处于 barrier状态后，同时执行后续操作
            System.out.println("通知线程" + Thread.currentThread().getName() + "，目前所有线程都完成了写入数据，可以执行后续任务");
        }
    }
}
