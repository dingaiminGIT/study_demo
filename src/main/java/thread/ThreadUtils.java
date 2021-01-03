package thread;

import java.util.concurrent.*;

public class ThreadUtils {

    public static final ThreadPoolExecutor executor;
    private static final ScheduledExecutorService scheduledExecutor;

    static {
        executor = new ThreadPoolExecutor(10, 100, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        scheduledExecutor = Executors.newScheduledThreadPool(5);
        //记录Cache信息
        ThreadUtils.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int activeCount = executor.getActiveCount();
                int poolSize = executor.getPoolSize();
                long completeTask = executor.getCompletedTaskCount();
                long allTask = executor.getTaskCount();
                System.out.println("threadpool status active="+activeCount+",poolsize="+poolSize+",completeTask="+completeTask+"alltask="+allTask);
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    public static void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        scheduledExecutor.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }

}
