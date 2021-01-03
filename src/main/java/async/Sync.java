package async;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Sync {

    public static void main(String[] args) {
        long start = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        doA();
        doB();
        long end = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        System.out.println("耗时：" + (end - start));
    }

    public static void doA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doA");
    }

    public static void doB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doB");
    }
}
