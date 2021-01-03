package thread;

import org.junit.jupiter.api.Test;

/**
 * 认识线程池中一个很重要的变量 ctl
 * ctl是一个AtomicInteger类型的变量
 * 通过高3位保存线程池的状态，低29位表示线程的数量
 *
 * Running ：接收新任务并且处理队列中的任务
 * Shutdown：不接收新任务，但是处理队列中的任务
 * Stop：既不接收新任务，也不处理队列中的任务，会中断正在处理的任务
 * Tidying：所有的任务都已终止，工作线程数是0，线程转换到 tidying状态，然后运行 terminated()的钩子方法
 * Terminated：terminated()方法已经完成
 */
public class CtlTest {

    /**
     * COUNT_BITS 29
     */
    private static final int COUNT_BITS = Integer.SIZE - 3;
    /**
     * CAPACITY 29 位 全是1
     */
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    @Test
    public void test() {
        System.out.println(Integer.SIZE);
        System.out.println(COUNT_BITS);
        //
        System.out.println(CAPACITY);

        System.out.println("===============");
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);

        System.out.println("================");
        System.out.println(runStateOf(1));
        System.out.println(workerCountOf(1));
        System.out.println(ctlOf(RUNNING,0));

        System.out.println("================");
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(1 << COUNT_BITS));
        System.out.println(Integer.toBinaryString(1 << COUNT_BITS -1));
        System.out.println(Integer.toBinaryString(CAPACITY)+ " CAPACITY  长度：" + Integer.toBinaryString(CAPACITY).length() + "\n");
        System.out.println(Integer.toBinaryString(~CAPACITY)+ " ~CAPACITY  长度：" + Integer.toBinaryString(~CAPACITY).length() + "\n");

        System.out.println("线程的状态二进制形式==============");
        System.out.println(Integer.toBinaryString(COUNT_BITS) + " COUNT_BITS  长度：" + Integer.toBinaryString(COUNT_BITS).length() + "\n");
        System.out.println(Integer.toBinaryString(RUNNING) + "  RUNNING 长度：" + Integer.toBinaryString(RUNNING).length());
        System.out.println(Integer.toBinaryString(SHUTDOWN) + " SHUTDOWN  长度：" + Integer.toBinaryString(SHUTDOWN).length());
        System.out.println(Integer.toBinaryString(STOP) + "  STOP 长度：" + Integer.toBinaryString(STOP).length());
        System.out.println(Integer.toBinaryString(TIDYING) + " TIDYING  长度：" + Integer.toBinaryString(TIDYING).length());
        System.out.println(Integer.toBinaryString(TERMINATED) + " TERMINATED  长度：" + Integer.toBinaryString(TERMINATED).length());
    }

    /**
     * 线程的状态高3位
     *
     * @param c
     * @return
     */
    private static int runStateOf(int c)  {
        return c & ~CAPACITY;
    }

    /**
     * 线程的数量 低29位  限制了为 2^29-1=536870911
     *
     * @param c
     * @return
     */
    private static int workerCountOf(int c)  {
        return c & CAPACITY;
    }

    /**
     *  线程池中有个控制状态的属性叫 ctl，类型是 AtomicInteger,包含 runState 和 workerCount
     * @param rs  runState 表示当前线程池的状态，是否处于Running Shutdown Stop Tidying Terminate 五种状态
     * @param wc  workerCount 表示当前有效的线程数
     * @return ctl的值 通过高3位为1的 rs 与 低29位的 wc 做 或运算就得到了 ctl这个数
     */
    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    /*public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        *//*
         * Proceed in 3 steps:
         *
         * 1. If fewer than corePoolSize threads are running, try to
         * start a new thread with the given command as its first
         * task.  The call to addWorker atomically checks runState and
         * workerCount, and so prevents false alarms that would add
         * threads when it shouldn't, by returning false.
         *
         * 2. If a task can be successfully queued, then we still need
         * to double-check whether we should have added a thread
         * (because existing ones died since last checking) or that
         * the pool shut down since entry into this method. So we
         * recheck state and if necessary roll back the enqueuing if
         * stopped, or start a new thread if there are none.
         *
         * 3. If we cannot queue task, then we try to add a new
         * thread.  If it fails, we know we are shut down or saturated
         * and so reject the task.
         *//*

        // 获取线程池的状态和线程个数的变量组合值
        int c = ctl.get();
        // 线程数小于核心线程数，要新建线程来执行任务
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }

        // 如果运行到这里，说明要么线程数大于核心线程数，要么上面的addWorker失败
        // 如果线程池处于 running状态，把这个任务添加到任务队列中(这里的两个操作其实是有先后顺序的，这里用 && 连起来，开始愣是没想明白这个条件)
        if (isRunning(c) && workQueue.offer(command)) {
            // 进入到这里，代表任务添加到队列中
            // 这里要进行 double-check 原因是可能从上次检查之后，之前活着的线程死了 或 线程池停了 即线程池处于非 Running 状态，该状态下是会抛弃新任务的
            int recheck = ctl.get();
            // 如果线程池没有运行，从队列中移除前面添加的那个任务并执行拒绝策略
            if (! isRunning(recheck) && remove(command))
                reject(command);
            // 如果线程池处于运行状态(因为上面的if条件不满足，任务队列中的任务也就不会从队列中移除)且线程数为0即线程池为空
            else if (workerCountOf(recheck) == 0)
                // 新建线程，为何要新建线程，因为线程数为0，因为之前的线程可能会停掉
                addWorker(null, false);
        }
        // 如果运行到这里，说明什么？线程池非running 状态 or 队列满了
        // 以最大线程数为界创建线程，如果失败，表示线程达到了最大值，执行拒绝策略
        else if (!addWorker(command, false))
            reject(command);
    }*/

    /**
     * 创建新的worker
     *
     * @param firstTask 提交给线程的任务，可以为null
     * @param core 如果为 true，表示以核心线程数为界创建线程  为false 表示以最大线程数为界创建线程
     * @return
     */
    /*private boolean addWorker(Runnable firstTask, boolean core) {
        retry:
        for (;;) {
            // 获取ctl
            int c = ctl.get();
            // 获取线程池的状态
            int rs = runStateOf(c);

            // 看了老半天都没搞明白的地方
            // 这里的判断条件有点多，拆成 rs>=SHUTDOWN 和 !(rs == SHUTDOWN && firstTask == null &&!workQueue.isEmpty())
            // !(rs == SHUTDOWN && firstTask == null &&!workQueue.isEmpty()) 逆着考虑 ,如下：
            // rs!SHUTDOWN也就是为大于shutdown，为 stop,tidying,terminated
            // firstTask != null
            // workQueue.isEmpty()
            // 如果线程池处于关闭状态，且满足下面条件之一的，不创建worker
            //      线程池处于 stop,tidying,terminated状态
            //      firstTask != null
            //      workQueue.isEmpty()
            // 注意：如果线程池处于shutdown，且 firstTask 为null,同时队列不为空，允许创建worker
            // Check if queue empty only if necessary.
            if (rs >= SHUTDOWN && ! (rs == SHUTDOWN && firstTask == null && ! workQueue.isEmpty()))
                return false;

            for (;;) {
                // 获取工作线程数
                int wc = workerCountOf(c);
                // 工作线程数大于最大容量或者 工作线程数超过 线程数的边界(根据core的值取不同的值) 时 不创建worker
                if (wc >= CAPACITY || wc >= (core ? corePoolSize : maximumPoolSize))
                    return false;
                // 工作线程数+1  通过CAS
                // 这里如果失败，表示有并发操作
                if (compareAndIncrementWorkerCount(c))
                    // 调出循环，执行真正的创建 worker逻辑
                    break retry;
                // 因为存在并发，需要再读取 ctl值进行状态判断
                // Re-read ctl
                c = ctl.get();
                // 如果线程状态发生了变化，回到外部循环
                if (runStateOf(c) != rs)
                    continue retry;
                // 回到内部循环
                // else CAS failed due to workerCount change; retry inner loop
            }
        }

        // 判断条件已经都通过，开始创建worker
        // 是否已经启动worker
        boolean workerStarted = false;
        // 是否添加worker
        boolean workerAdded = false;
        ThreadPoolExecutor.Worker w = null;
        try {
            // 把task包装成worker
            w = new ThreadPoolExecutor.Worker(firstTask);
            // 获取worker中的线程，这个线程是线程工厂创建的，在worker的构造方法中
            final Thread t = w.thread;
            if (t != null) {
                // 获取对象锁
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    // Recheck while holding lock.
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    int rs = runStateOf(ctl.get());

                    // 如果线程池处于Running状态 或者 线程池处于shutdown状态且任务为null
                    if (rs < SHUTDOWN ||
                            (rs == SHUTDOWN && firstTask == null)) {
                        // precheck that t is startable
                        // 检查线程是否为启动状态，如果为启动状态抛异常
                        if (t.isAlive())
                            throw new IllegalThreadStateException();
                        // 把新建的worker添加到worker集中
                        workers.add(w);
                        int s = workers.size();
                        // largestPoolSize记录workers中个数存在过的最大值
                        if (s > largestPoolSize)
                            largestPoolSize = s;
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                // 新建的worker添加成功就启动线程
                if (workerAdded) {
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            // 线程没有启动成功，对上面创建线程的过程做回滚操作
            if (! workerStarted)
                // 回滚操作，比如把worker从workers中移除，把线程数减一
                addWorkerFailed(w);
        }
        return workerStarted;
    }*/

    /**
     * 对创建worker做回滚操作
     *  从workers中移除worker
     *  减少线程数
     *
     * Rolls back the worker thread creation.
     * - removes worker from workers, if present
     * - decrements worker count
     * - rechecks for termination, in case the existence of this
     *   worker was holding up termination
     */
    /*private void addWorkerFailed(ThreadPoolExecutor.Worker w) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            if (w != null)
                workers.remove(w);
            decrementWorkerCount();
            tryTerminate();
        } finally {
            mainLock.unlock();
        }
    }*/

    /*t.start();
    // 创建worker的时候，通过线程工厂 new了一个Thread，ThreadFactor.newThread(task)  ThreadFactory用默认的线程工厂DefaultThreadFactory(为Executors里面的内部类)
    // 通过Thread的构造器创建线程，Thread的构造器有个Runnable类型的target
    public Thread(ThreadGroup group, Runnable target, String name,
                  long stackSize) {
        init(group, target, name, stackSize);
    }
    // 当调用 Thread.start()方法的时候会执行run()方法，run()放发中会执行 target.run,类似代理
    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    // 这样就可以执行我们的 worker的run方法

    // worker的run()方法
    public void run() {
        runWorker(this);
    }*/

    /**
     * Main worker run loop.  Repeatedly gets tasks from queue and
     * executes them, while coping with a number of issues:
     *
     * 1. We may start out with an initial task, in which case we
     * don't need to get the first one. Otherwise, as long as pool is
     * running, we get tasks from getTask. If it returns null then the
     * worker exits due to changed pool state or configuration
     * parameters.  Other exits result from exception throws in
     * external code, in which case completedAbruptly holds, which
     * usually leads processWorkerExit to replace this thread.
     *
     * 2. Before running any task, the lock is acquired to prevent
     * other pool interrupts while the task is executing, and then we
     * ensure that unless pool is stopping, this thread does not have
     * its interrupt set.
     *
     * 3. Each task run is preceded by a call to beforeExecute, which
     * might throw an exception, in which case we cause thread to die
     * (breaking loop with completedAbruptly true) without processing
     * the task.
     *
     * 4. Assuming beforeExecute completes normally, we run the task,
     * gathering any of its thrown exceptions to send to afterExecute.
     * We separately handle RuntimeException, Error (both of which the
     * specs guarantee that we trap) and arbitrary Throwables.
     * Because we cannot rethrow Throwables within Runnable.run, we
     * wrap them within Errors on the way out (to the thread's
     * UncaughtExceptionHandler).  Any thrown exception also
     * conservatively causes thread to die.
     *
     * 5. After task.run completes, we call afterExecute, which may
     * also throw an exception, which will also cause thread to
     * die. According to JLS Sec 14.20, this exception is the one that
     * will be in effect even if task.run throws.
     *
     * The net effect of the exception mechanics is that afterExecute
     * and the thread's UncaughtExceptionHandler have as accurate
     * information as we can provide about any problems encountered by
     * user code.
     *
     * @param w the worker
     */
    // 从队列中获取任务然后执行
    /*final void runWorker(ThreadPoolExecutor.Worker w) {
        Thread wt = Thread.currentThread();
        // 初始化的时候会有 firstTask
        Runnable task = w.firstTask;
        w.firstTask = null;
        // state 置为0，允许中断
        w.unlock(); // allow interrupts
        boolean completedAbruptly = true;
        try {
            // 从任务队列中获取任务
            while (task != null || (task = getTask()) != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted;
                // if not, ensure thread is not interrupted.  This
                // requires a recheck in second case to deal with
                // shutdownNow race while clearing interrupt

                // 如果线程池状态大于或等于stop，即 runStateAtLeast(ctl.get(), STOP) 为 true，这个时候就要确保线程是中断的
                // 不用看||后面的条件，直接判断  !wt.isInterrupted()，因为线程池状态为暂停，要确保线程中断，如果没有中断，就要手动中断线程，即执行wt.interrupt()

                // 如果线程池状态不是stop，即 runStateAtLeast(ctl.get(), STOP) 为 false，就要确保线程没有中断，这样才能在后面执行任务
                // 这时候需要看 || 后面的 (Thread.interrupted() && runStateAtLeast(ctl.get(), STOP)) ，因为要确保线程没有中断，调用Thread.interrupted()清除中断状态，
                // 这里需要再次进行验证线程池的状态，因为可能会有shutdownNow的情况
                if ((runStateAtLeast(ctl.get(), STOP) ||
                        (Thread.interrupted() &&
                                runStateAtLeast(ctl.get(), STOP))) &&
                        !wt.isInterrupted())
                    wt.interrupt();
                try {
                    // 空方法体，子类可以实现，做一些特殊化处理工作
                    beforeExecute(wt, task);
                    Throwable thrown = null;
                    try {
                        // 执行任务
                        task.run();
                    } catch (RuntimeException x) {
                        thrown = x; throw x;
                    } catch (Error x) {
                        thrown = x; throw x;
                    } catch (Throwable x) {
                        thrown = x; throw new Error(x);
                    } finally {
                        // 空方法体，子类可以实现，做一些特殊化处理工作
                        afterExecute(task, thrown);
                    }
                } finally {
                    task = null;
                    // 统计当前worker完成了多少任务
                    w.completedTasks++;
                    w.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            // 执行线程关闭
            processWorkerExit(w, completedAbruptly);
        }
    }*/

    /**
     * Performs blocking or timed wait for a task, depending on
     * current configuration settings, or returns null if this worker
     * must exit because of any of:
     * 1. There are more than maximumPoolSize workers (due to
     *    a call to setMaximumPoolSize).
     * 2. The pool is stopped.
     * 3. The pool is shutdown and the queue is empty.
     * 4. This worker timed out waiting for a task, and timed-out
     *    workers are subject to termination (that is,
     *    {@code allowCoreThreadTimeOut || workerCount > corePoolSize})
     *    both before and after the timed wait, and if the queue is
     *    non-empty, this worker is not the last thread in the pool.
     *
     * @return task, or null if the worker must exit, in which case
     *         workerCount is decremented
     */

    /**
     * 该方法有三种情况
     * 1.一直阻塞到有任务返回 workQueue.take()
     * 2.从队列中取任务超时退出
     * 3.如果发生下面任意一种情况，会返回null
     *  ①线程数量超过最大线程数 调用setMaximumPoolSize方法
     *  ②线程池处于stop，【既不接收新线程，也不执行队列中的任务】
     *  ③线程池处于shutdown，且队列为空 【不接收新线程】
     *  ④超时且超时的worker线程需要终止。如果任务队列非空，
     *  要保证当前worker线程不是线程池中最后一个线程(如果任务为空，当前线程是线程池中的最后一个线程也无妨，毕竟任务队列为空，当前worker线程关闭就关闭了，没影响)
     * @return
     */
    /*private Runnable getTask() {
        boolean timedOut = false; // Did the last poll() time out?

        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // Check if queue empty only if necessary.
            // 1.线程池处于stop
            // 2.线程池处于shutdown，且队列为空
            if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
                // 减少工作线程数
                decrementWorkerCount();
                return null;
            }

            int wc = workerCountOf(c);

            // Are workers subject to culling?
            boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;

            // 这里的判断条件可以看成 if (wc > maximumPoolSize || ((timed && timedOut) && (wc > 1 || workQueue.isEmpty()))
            // 超时且超时的worker线程需要终止。如果任务队列非空，要保证当前worker线程不是线程池中最后一个线程
            // (如果任务为空，当前线程是线程池中的最后一个线程也无妨，毕竟任务队列为空，当前worker线程关闭就关闭了，没影响)
            if ((wc > maximumPoolSize || (timed && timedOut))
                    && (wc > 1 || workQueue.isEmpty())) {
                if (compareAndDecrementWorkerCount(c))
                    return null;
                continue;
            }

            try {
                // 从队列取任务
                Runnable r = timed ?
                        workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                        workQueue.take();
                if (r != null)
                    return r;
                timedOut = true;
            } catch (InterruptedException retry) {
                // 如果worker发生中断，就继续重试
                // 发生中断的原因 setMaximumPoolSize方法,如果线程数大于被设置的maxPoolSize，那么多出的线程就应该关闭
                // 重新进入for循环，这样就会出现返回null的情况
                timedOut = false;
            }
        }
    }*/

    // 参考链接
    // https://juejin.im/entry/59b232ee6fb9a0248d25139a
}
