package thread;

/**
 * 线程的中断机制
 * Thread.interrupt()的作用不是中断线程，而是通知线程应该中断了
 *      1.如果线程处于被阻塞状态，线程立即退出被阻塞状态，同时抛出InterruptedException异常
 *      2.如果线程处于正常运行状态，会将标志位设为true。被设置中断标志的线程继续正常工作，不受影响
 * Thread.interrupt()不能真正中断线程，需要被调用的线程自己进行配合
 *      1.正常运行时，循环检查本线程的中断标志位，如果被设置了中断标志就自行停止线程
 *      2.在调用阻塞方法时处理异常InterruptedException
 *
 * 静态方法Thread.interrupted() 获取线程的中端状态，同时会清除中断标志位，传入true
 *
 * 实例方法isInterrupted() 获取线程的中断状态，不会清除中断标志位，传入false
 *
 * 问题：什么场景下，需要在catch里面中断线程？
 * 答案：如果不能抛出InterruptedException，但是想告诉调用者这里发生了中断，就需要在catch块里重置中断状态 interrupt
 *
 * 问题：为什么在抛出InterruptedException会清除中断状态
 * 答案：没有官方的答案，有网友给的解释是：一个中断应该只被处理一次，如果你catch了这个异常，说明你能处理这个异常，不希望调用者看到这个异常
 *
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Worker());
        t.start();

        Thread.sleep(200);
        t.interrupt();

        System.out.println("Main thread stopped");

    }

    public static class Worker implements Runnable {

        @Override
        public void run() {
            System.out.println("Worker started");

            try {
                //
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // Worker被中断了，但是isInterrupted返回了 false，这是因为 sleep方法会抛出InterruptedException异常，抛出异常后，中断状态会被清除
                System.out.println("Worker isInterrupted : " + Thread.currentThread().isInterrupted());

                // 调用 interrupt中断
                Thread currentThread = Thread.currentThread();
                currentThread.interrupt();
                System.out.println("after call interrupt");
                System.out.println("Worker isInterrupted : " + Thread.currentThread().isInterrupted());
                // interrupted 获取线程的中断状态，并重置了中断状态
                System.out.println("call interrupted : " + Thread.interrupted());
                System.out.println("Worker isInterrupted : " + Thread.currentThread().isInterrupted());
            }

            System.out.println("Worker stopped");
        }
    }
}
