package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{

    // 线程池最大限制数
    private static final int MAX_WORKER_NUMBERS = 10;
    // 线程池默认的数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    // 线程池最小的数量
    private static final int MIN_WORKER_NUMBERS = 1;
    // 这是一个工作线程，将会向里面插入工作
    private final LinkedList<Job> jobs = new LinkedList<>();
    // 工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    // 工作者线程的数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    // 线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        initializeWorkers(num);
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            // 添加一个工作，然后进行通知
            synchronized (jobs) {
                jobs.addLast(job);
                // 用了notify而不是notifyAll，因为能够确定有工作线程被唤醒，
                // 获得更小的开销-避免将等待队列中的线程全部移动到阻塞队列中
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker: workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            // 限制新增的Worker数量不能超过最大值
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond worknum");
            }
            // 按照给定的数量停止Worker
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    // 初始化线程工作者
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    // 工作者，负责消费任务
    class Worker implements Runnable {

        // 是否工作
        private volatile boolean running = true;
        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    // 如果工作列表是空，就wait
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            // 感知到外部对WorkerThread的中断操作，返回
                            return;
                        }
                    }

                    // 取出一个job
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        // 忽略Job执行中的异常
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
