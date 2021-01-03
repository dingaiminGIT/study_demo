package algo.queue;

/**
 * 基于链表实现的队列
 */
public class LinkedListQueue {

    /**
     * 队头和队尾
     */
    private Node head = null;
    private Node tail = null;

    /**
     * 入队
     *
     * @param value
     */
    public void enqueue(String value) {
        // 队列中是空，第一次添加
        if (tail == null) {
            Node new_node = new Node(value, null);
            head = new_node;
            tail = new_node;
        } else {
            tail.next = new Node(value, null);
            tail = tail.next;
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        // 如果队列为空
        if (head == null) {
            return null;
        } else {
            String data = head.data;
            head = head.next;
            // 判断出队后是否只有一个Node
            if (head == null) {
                tail = null;
            }
            return data;
        }

    }

    public void pringAll() {
        Node p = head;
        while (p != null) {
            System.out.println(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListQueue linkedListQueue = new LinkedListQueue();
        linkedListQueue.enqueue("1");
        linkedListQueue.enqueue("2");
        linkedListQueue.enqueue("3");

        linkedListQueue.pringAll();

        System.out.println(linkedListQueue.dequeue());
        System.out.println(linkedListQueue.dequeue());
    }

    private static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }
    }

}
