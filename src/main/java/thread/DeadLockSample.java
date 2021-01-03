package thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeadLockSample extends Thread {
    private String first;
    private String second;
    public DeadLockSample(String name, String first, String second) {
    	super(name);
    	this.first = first;
    	this.second = second;
    }

    @Override
    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
            Thread.sleep(1000L);
            synchronized (second) {
                System.out.println(this.getName() + " obtained: " + second);
            }
            } catch (InterruptedException e) {
            // Do nothing
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {

        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        Runnable dlCheck = () -> {
            long[] threadIds = mxBean.findDeadlockedThreads();
            if (threadIds != null) {
                ThreadInfo[] threadInfos = mxBean.getThreadInfo(threadIds);
                System.out.println("Detected deadlock threads:");

                for (ThreadInfo thread :threadInfos) {
                    System.out.println(thread.getThreadName());
                }
            }
        };

        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        schedule.scheduleAtFixedRate(dlCheck, 5L, 10L, TimeUnit.SECONDS);

    	String lockA = "lockA";
    	String lockB = "lockB";
    	DeadLockSample t1 = new DeadLockSample("Thread1", lockA, lockB);
    	DeadLockSample t2 = new DeadLockSample("Thread2", lockB, lockA);
    	t1.start();
    	t2.start();
    	t1.join();
    	t2.join();
    }
}
