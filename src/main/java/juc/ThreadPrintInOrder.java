package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 京东零售黄金流3面,京东零售营销 1 面,字节定向 1 面，
 * 一个数组，三个线程，按序打印
 */
public class ThreadPrintInOrder {

    /**
     * 线程
     */
    static volatile int threadFlag = 1;
    static ReentrantLock lock = new ReentrantLock();
    static Object monitor = new Object();

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};
        printSynchronized(nums);
    }

    /**
     * 用 synchronized 实现
     *
     * @param num
     */
    public static void printSynchronized(int[] num) {
        AtomicInteger index = new AtomicInteger(0);
        int length = num.length;

        Thread thread1 = new Thread(() -> {
            while(index.get() < length) {
                synchronized (monitor) {
                    if (threadFlag != 1) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(threadFlag == 1 && index.get() < length) {
                        System.out.println(Thread.currentThread().getName() + ":" + num[index.get()]);
                        index.incrementAndGet();
                        threadFlag = 2;
                        monitor.notifyAll();
                    }
                }
            }
        }, "线程 1");

        Thread thread2 = new Thread(() -> {
            while(index.get() < length) {
                synchronized (monitor) {
                    if (threadFlag != 2) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(threadFlag == 2 && index.get() < length) {
                        System.out.println(Thread.currentThread().getName() + ":" + num[index.get()]);
                        index.incrementAndGet();
                        threadFlag = 3;
                        monitor.notifyAll();
                    }
                }
            }
        }, "线程 2");

        Thread thread3 = new Thread(() -> {
            while(index.get() < length) {
                synchronized (monitor) {
                    if (threadFlag != 3) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(threadFlag == 3 && index.get() < length) {
                        System.out.println(Thread.currentThread().getName() + ":" + num[index.get()]);
                        index.incrementAndGet();
                        threadFlag = 1;
                        monitor.notifyAll();
                    }
                }
            }
        }, "线程 3");

        thread1.start();
        thread2.start();
        thread3.start();


    }

    /**
     * 通过 Lock + volatile变量
     *
     * @param num
     */
    public static void print2(int[] num) {
        AtomicInteger index = new AtomicInteger(0);
        int length = num.length;

        Thread thread1 = new Thread(() -> {
            while(index.get() < length) {
                lock.lock();
                try{
                    if(threadFlag == 1 && index.get() < length) {
                        System.out.println(Thread.currentThread().getName() + ":" + num[index.get()]);
                        index.incrementAndGet();
                        threadFlag = 2;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }, "线程 1");

        Thread thread2 = new Thread(() -> {
            while(index.get() < length) {
                lock.lock();
                try{
                    if(threadFlag == 2 && index.get() < length) {
                        System.out.println(Thread.currentThread().getName() + ":" + num[index.get()]);
                        index.incrementAndGet();
                        threadFlag = 3;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }, "线程 2");

        Thread thread3 = new Thread(() -> {
            while(index.get() < length) {
                lock.lock();
                try{
                    if(threadFlag == 3 && index.get() < length) {
                        System.out.println(Thread.currentThread().getName() + ":" + num[index.get()]);
                        index.incrementAndGet();
                        threadFlag = 1;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }, "线程 3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 通过 Semaphore 实现
     *
     * @param num
     */
    public static void print(int[] num) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(0);

        AtomicInteger index = new AtomicInteger(0);
        int length = num.length;

        Thread thread1 = new Thread(() -> {
            while (index.get() < length) {
                try {
                    semaphore1.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (index.get() < length) {
                    System.out.println(Thread.currentThread().getName() + ":" + num[index.get()]);
                    index.incrementAndGet();
                    semaphore2.release();
                }
            }
        }, "线程 1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (index.get() < length) {
                try {
                    semaphore2.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (index.get() < length) {
                    System.out.println(Thread.currentThread().getName() + ":" + num[index.get()]);
                    index.incrementAndGet();
                    semaphore3.release();
                }
            }
        }, "线程 2");
        thread2.start();

        Thread thread3 = new Thread(() -> {
            while(index.get() < length) {
                try {
                    semaphore3.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (index.get() < length) {
                    System.out.println(Thread.currentThread().getName() + ":" + num[index.get()]);
                    index.incrementAndGet();
                    semaphore1.release();
                }
            }
        }, "线程 3");
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 通过 Semaphore 实现，这版有问题
     *
     * @param num
     */
    public static void printSemaphore(int[] num) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(0);

        AtomicInteger index = new AtomicInteger(0);
        Thread thread1 = new Thread(new MyTask(index, num, semaphore1, semaphore2), "线程 1");
        Thread thread2 = new Thread(new MyTask(index, num, semaphore2, semaphore3), "线程 2");
        Thread thread3 = new Thread(new MyTask(index, num, semaphore3, semaphore1), "线程 3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static class MyTask implements Runnable {

        private AtomicInteger index;
        private int[] num;
        private Semaphore semaphore;
        private Semaphore postSemaphore;

        public MyTask(AtomicInteger index, int[] num, Semaphore semaphore, Semaphore postSemaphore) {
            this.index = index;
            this.num = num;
            this.semaphore = semaphore;
            this.postSemaphore = postSemaphore;
        }

        @Override
        public void run() {
            while(index.get() < num.length) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (index.get() < num.length) {
                    System.out.println(Thread.currentThread().getName() + ":" + num[index.get()]);
                    index.incrementAndGet();
                    postSemaphore.release();
                }
            }
        }
    }

}
