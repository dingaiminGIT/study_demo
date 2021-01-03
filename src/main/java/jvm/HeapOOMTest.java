package jvm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 面试题：线程创建了多个子线程，其中一个抛出 OOM 了，其他的线程状态是什么？
 * 一个线程OOM后，该线程占用的资源会释放掉（这里为什么会释放呢？），进程中的其他线程照常运行
 *
 */
public class HeapOOMTest {

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            List<Object> list = new ArrayList<>();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                list.add(new Object());
            }
        }).start();

        new Thread(() -> {
            int i = 0;
            while (true) {
                System.out.println("线程1 i:" + i++);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        int j = 0;
        while (true) {
            System.out.println("main j:" + j++);
            System.out.println(LocalDateTime.now());
            Thread.sleep(2000);
        }

    }

}
