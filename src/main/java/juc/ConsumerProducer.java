package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConsumerProducer {
    public static final String EXIT_MSG = "good bye";

    public static void main(String[] args) {
        BlockingQueue<String> queues = new ArrayBlockingQueue<>(3);

        Producer producer = new Producer(queues);
        Consumer consumer = new Consumer(queues);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Producer implements Runnable {
        private BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(5);
                    String msg = "Message" + i;
                    System.out.println("Produced new item: " +msg);
                    queue.put(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Time to say good bye");
            try {
                queue.put(EXIT_MSG);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String msg;
            try {
                while (!EXIT_MSG.equalsIgnoreCase((msg = queue.take()))) {
                    System.out.println("Consumer item: " +msg);
                    Thread.sleep(10);
                }
                System.out.println("got exit message, bye");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
