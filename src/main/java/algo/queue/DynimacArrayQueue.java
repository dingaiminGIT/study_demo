package algo.queue;

/**
 * 用数组实现队列-动态
 */
public class DynimacArrayQueue {

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
    public DynimacArrayQueue(int capacity) {
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
            // tail == n && head == 0，表示整个队列都占满
            if (head == 0) {
                   return false;
            }
            // 数据搬移
            for (int i = head; i < tail; i++) {
                items[i-head] = items[i];
            }
            // 搬移后重新更新head tail
            // 当队列的 tail 指针移动到数组的最右边后，如果有新的数据入队，我们将 head到tail之间的数据，整体搬移到数组中 0-tail-head的位置
            tail -= head;
            head = 0;
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
        String item = items[head];
        head++;
        return item;
    }

    public void printAll() {
        for (int i = head; i < tail; i++) {
            System.out.println(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DynimacArrayQueue queue = new DynimacArrayQueue(3);
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
