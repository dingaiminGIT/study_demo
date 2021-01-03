package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadPools {

    private static ThreadPoolExecutor ioExecutor;
    private static ScheduledExecutorService scheduledExecutor;
    private static RejectedExecutionHandler rejectHandler = (r, executor)-> {
        throw new RejectedExecutionException("拒绝");
    };


    public static void init() {
        SynchronousQueue<Runnable> taskQueue = new SynchronousQueue<>();

        ThreadFactory ioFactory = new ThreadFactoryBuilder().setNameFormat("dmp-io-pool-%d").build();
        ThreadFactory scheduledFactory = new ThreadFactoryBuilder().setNameFormat("dmp-schedule-pool-%d").build();

        ioExecutor = new ThreadPoolExecutor(10,20,1L,TimeUnit.MINUTES, taskQueue, ioFactory, rejectHandler);
        scheduledExecutor = Executors.newScheduledThreadPool(1, scheduledFactory);

        //线程池监控日志
        ThreadPools.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("activeThread="+ioExecutor.getActiveCount() + " poolSize="+ioExecutor.getPoolSize()
                        +" completeTask="+ioExecutor.getCompletedTaskCount() + " allTask="+ioExecutor.getTaskCount());
                ThreadPoolExecutor scheduled = (ThreadPoolExecutor) scheduledExecutor;
            }

            @Override
            public String toString() {
                return "Task-线程池监控日志";
            }
        }, 1, TimeUnit.MINUTES);
    }

    public static void scheduleAtFixedRate(Runnable command, long period, TimeUnit unit) {
        scheduledExecutor.scheduleAtFixedRate(command, 0, period, unit);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return ioExecutor.submit(task);
    }
}
