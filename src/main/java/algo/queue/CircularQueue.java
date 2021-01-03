package algo.queue;

/**
 * 循环队列
 * 队列为空判断：head == tail
 * 队列满判断：(tail+1)%n == head
 */
public class CircularQueue {

    /**
     * 数组：items，数组大小：n
     */
    private String[] items;
    private int n = 0;

    /**
     * head队头下标，tail队尾下标
     */
    private int head = 0;
    private int tail = 0;

    /**
     * 申请capacity容量大小的队列
     *
     * @param capacity
     */
    public CircularQueue(int capacity) {
        this.items = new String[capacity];
        this.n = capacity;
    }

    /**
     * 入队
     *
     * @param item
     * @return
     */
    public boolean enqueue(String item) {
        // 队列满：(tail + 1) % n == head
        if ((tail + 1) % n == head) {
            return false;
        } else {
            items[tail] = item;
            tail = (tail + 1) % n;
            return true;
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        if (head == tail) {
            return null;
        } else {
            String item = items[head];
            head = (head + 1) % n;
            return item;
        }
    }

    public void printAll() {
        for (int i = head; i < tail; ++i) {
            System.out.println(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(3);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");

        queue.printAll();

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue("5");
        System.out.println(queue.dequeue());
    }
}
