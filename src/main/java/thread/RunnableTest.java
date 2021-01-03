package thread;

public class RunnableTest {

    public static void main(String[] args) {
        Acceptor acceptor = new Acceptor();
        acceptor.run();
    }

    static class Acceptor implements Runnable {

        @Override
        public void run() {
            System.out.println("acceptor");
            System.out.println(Thread.currentThread().getName());
            Handler handler = new Handler();
            handler.run();
        }
    }

    static class Handler implements Runnable {

        @Override
        public void run() {
            System.out.println("处理IO读写事件");
            System.out.println(Thread.currentThread().getName());
        }
    }
}
