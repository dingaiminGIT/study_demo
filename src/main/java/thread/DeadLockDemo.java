package thread;

public class DeadLockDemo {

    public static void main(String[] args) {
        String lock1 = "lock1";
        String lock2 = "lock2";

        new Thread(new HoldLockThread(lock1, lock2), "thread1").start();
        new Thread(new HoldLockThread(lock2, lock1), "thread2").start();
    }

}

class HoldLockThread implements Runnable {

    private String lock1;
    private String lock2;

    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "\t 线程持有锁:" + lock1 + "\t 尝试获取锁：" + lock2);

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "\t 线程持有锁:" + lock2 + "\t 尝试获取锁：" + lock1);
            }
        }
    }
}
