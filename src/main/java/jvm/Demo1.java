package jvm;

public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);
        while (true) {
            load();
        }

    }

    private static void load() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data = null;
        Thread.sleep(1000);
    }

}
