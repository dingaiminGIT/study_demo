package thread;

/**
 * Daemon线程是一种支持型线程，主要被用作程序中后台调度以及支持性工作
 * Thread.setDaemon(true)将线程设置成Daemon线程
 * 当Java虚拟机中没有非Daemon线程，虚拟机需要退出，所有Daemon线程需要终止
 * 所以不能依靠finally块中的内容来确保执行关闭或清理资源的操作
 */
public class Daemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("DaemonThread finally run");
            }
        }
    }
}
