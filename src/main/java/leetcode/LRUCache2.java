package leetcode;

import java.util.HashMap;
import java.util.Map;


/**
 * https://leetcode-cn.com/problems/lru-cache/
 */
public class LRUCache2 {
    /**
     * 双向链表
     */
    class DLinkedNode{
        int key,value;
        DLinkedNode pre,next;
        public DLinkedNode(){};
        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size,capacity;
    private DLinkedNode head,tail;

    public LRUCache2(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 伪头部和尾部
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if(node == null) {
            return -1;
        }
        // key 存在，移动到头部
        removeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        // 添加时需要先查看key是否存在，如果不存在就添加,添加完后，要判断是否达到容量，如果达到要移除尾部节点;如果存在，要修改值，并将其移动到双向链表的头部
        DLinkedNode node = cache.get(key);
        // key不存在，添加，并判断是否达到容量，达到要移除尾部节点
        if(node == null) {
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if(size > capacity) {
                // 删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的key-value对
                cache.remove(tail.key);
                size--;
            }
        } else {
            // key 存在，修改值
            node.value = value;
            // 移动到链表头部
            removeToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        // 将节点移动到链表的头部
        // 1.将当前节点的pre指向head
        // 2.将当前节点的next指向head.next
        // 3.将之前head节点后面的节点的pre指向当前节点
        // 4.将head节点的next指向当前节点
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void removeToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.pre;
        removeNode(res);
        return res;
    }
}
