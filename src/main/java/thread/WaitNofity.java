package thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 模拟线程的等待与通知
 *
 * @author: dingaimin
 * @date: 2021/1/16 11:32
 */
public class WaitNofity {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            // 加锁，拥有 lock 的Monitor
            synchronized (lock) {
                // 当条件不满足时，继续 wait ,同时释放了 lock 的锁
                while (flag) {
                    System.out.println(Thread.currentThread() + " flag is true. waiting @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        // Object.wait() 方法会释放锁，同时进入 waiting 状态
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 当条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            // 加锁 拥有 lock 的 Monitor
            synchronized (lock) {
                // 获取到 lock 的锁，然后进行通知，通知时不会释放 lock 的锁
                // 直到当前线程释放 lock 锁后，WaitThread 才能从 wait 方法中返回
                System.out.println(Thread.currentThread() + " hold lock notify @  " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }

            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock notify @  " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }

}
