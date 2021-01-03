package btrace;

public class SleepTest {

    public static void main(String[] args) {
        while (true)  {
            SleepTest.sleep();
        }
    }

    public static void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
