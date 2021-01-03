package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：三个线程，要求实现桉序打印 A > B > C...
 * A 打印 5 次，B 打印 10 次，C 打印 15 次
 * 按照上面的顺序，再来
 * .... 3 轮
 *
 */
public class ShareResource {

    private int number = 1;
    Lock lock = new ReentrantLock();
    private static int totalLoop = 3;

    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public static void main(String[] args) {
        final ShareResource sr = new ShareResource();

        new Thread(() -> {
            for (int i = 0; i < totalLoop; i++) {
                sr.loopA(i);
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < totalLoop; i++) {
                sr.loopB(i);
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < totalLoop; i++) {
                sr.loopC(i);
            }
        }, "C").start();
    }



    public void loopA(int totalLoop) {
        lock.lock();
        try {
            // 判断该线程是否可以执行
            while (number != 1) {
                condition1.await();
            }
            // 执行
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i +"\t总轮次" + totalLoop);
            }
            // 通知下一个线程来接手执行
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(int totalLoop) {
        lock.lock();
        try {
            // 判断该线程是否可以执行
            while (number != 2) {
                condition2.await();
            }
            // 执行
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t总轮次" + totalLoop);
            }
            // 通知下一个线程接手执行
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(int totalLoop) {
        lock.lock();
        try {
            // 判断该线程是否可以执行
            while (number != 3) {
                condition3.await();
            }
            // 执行
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t总伦次" + totalLoop);
            }
            // 通知下一个线程来接手执行
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
