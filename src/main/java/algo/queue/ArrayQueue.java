package algo.queue;

/**
 * 用数组实现队列
 */
public class ArrayQueue {

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
     * 申请一个大小为capacity的数组
     *
     * @param capacity
     */
    public ArrayQueue(int capacity) {
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
        // 如果tail == n 表示队列已经满了
        if (tail == n) {
            return false;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        // 如果head == tail 表示队列为空
        if (head == tail) {
            return null;
        }
        String item = items[head++];
        return item;
    }

    public void printAll() {
        for (int i = head; i < tail; i++) {
            System.out.println(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");

        queue.printAll();

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

    }
}
