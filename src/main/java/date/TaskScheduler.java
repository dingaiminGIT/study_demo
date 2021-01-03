package date;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: dingaimin
 * @date: 2020/3/24 14:44
 */
public class TaskScheduler {


    /**
     * IO 线程池
     */
    public static ThreadPoolExecutor ioExecutor;

    static {
        ThreadFactory ioFactory = new ThreadFactoryBuilder().setNameFormat("io-pool-%d").build();
        LinkedBlockingDeque<Runnable> ioTaskQueue = new LinkedBlockingDeque<>(10);
        ioExecutor = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.MINUTES, ioTaskQueue, ioFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
