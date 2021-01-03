package dsp;

public class MemeryTest {

    public static void main(String[] args) {
        int QPS = 80000;
        int count = QPS * 60 * 60 * 24;
        System.out.println(count);

        int count2 = 200000000;
        System.out.println(count2 * 33 / 1024/ 1024);

    }
}
