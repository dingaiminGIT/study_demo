package juc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch用在一个线程需要等待多个线程的执行结果
 *
 */
public class CountDownLatchDemo {
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        // 两个人比赛跑步
        CountDownLatch latch = new CountDownLatch(2);
        Runner runner1 = new Runner("博尔特", latch, 7000);
        Runner runner2 = new Runner("苏炳添", latch, 8000);
        runner1.start();
        runner2.start();
        // 等待两个运动员都跑完
        latch.await();
        System.out.println("运动员都跑完了，时间"+ sdf.format(new Date()));
    }

    static class Runner extends Thread{
        String runnerName;
        CountDownLatch latch;
        int runTime;
        public Runner(String runnerName,CountDownLatch latch,int runTime) {
            this.runnerName = runnerName;
            this.latch = latch;
            this.runTime = runTime;
        }

        public void run() {
            System.out.println(runnerName+" run begin at " +sdf.format(new Date()));
            // 开始跑步
            doRun();
            System.out.println(runnerName+" run end at " + sdf.format(new Date()));
            // 运动员跑步完成，计数器减一
            latch.countDown();
        }

        private void doRun() {
            try {
                Thread.sleep(runTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}