package leetcode.kuaishou;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存
 *
 * 哈希表 + 双向链表实现
 * 哈希表：存储 key 与双向链表节点的映射
 * 双向链表：存储按使用顺序排序的键值对，链表头是最新被访问的，链表尾是最早被访问的
 *
 * @author: dingaimin
 * @date: 2021/1/24 12:11
 */
public class LRUCache {

    /**
     * 哈希表：存储 key 与双向链表节点的映射
     */
    private Map<Integer, DoubleLinkedNode> cache = new HashMap<>();
    /**
     * 当前LRUCache的大小
     */
    private int size;
    /**
     * 当前LRUCache的最大容量
     */
    private int capacity;
    /**
     * 虚拟头结点
     */
    private DoubleLinkedNode head;
    /**
     * 虚拟尾结点
     */
    private DoubleLinkedNode tail;

    /**
     * 初始化 LRUCache
     *
     * @param capacity LRUCache 的最大容量
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new DoubleLinkedNode();
        this.tail = new DoubleLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 获取指定 key 对应的 value
     *
     * @param key 指定的 key
     * @return 如果 LRUCache 中存在key，返回对应的 value，如果 key 不存在，返回 -1
     */
    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        // 首先从哈希表中获取 key 对应的双端链表的节点
        DoubleLinkedNode node = cache.get(key);
        // 如果不存在，返回 -1
        if (node == null) {
            return -1;
        }
        // 如果存在将该节点移动到双端链表的头部，并返回该节点对应的值
        moveToHead(node);
        return node.value;
    }

    /**
     * 往 LRUCache 缓存中添加 key-value 键值对
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        // 添加时要查看 key 是否存在
        DoubleLinkedNode node = cache.get(key);
        // 如果不存在构建节点添加到哈希表中，并将该节点添加到链表头部
        if (node == null) {
            // 构建节点并添加到哈希表中
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            cache.put(key, newNode);
            // 将该节点添加到链表的头部
            addToHead(newNode);
            // LRUCache 保存的节点容量加1
            size++;
            // 判断容量是否超限
            if (size > capacity) {
                // 删除链表中的尾结点
                DoubleLinkedNode tail = removeTail();
                // 删除哈希表中的键值对
                cache.remove(tail.key);
                // size--
                size--;
            }
        } else {
            // 如果 key 存在，更新对应的 value
            node.value = value;
            // 将该节点移动的链表头部
            moveToHead(node);
        }
    }

    /**
     * 从链表中移除当前节点
     *
     * @param node
     */
    private void removeNode(DoubleLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    /**
     * 将双端链表中的节点移动到双端链表的头部
     *
     * @param node
     */
    private void moveToHead(DoubleLinkedNode node) {
        // 首先从链表中将该节点移除：将该节点与前后节点接触关联
        removeNode(node);
        // 再将该节点添加到链表的头部
        addToHead(node);
    }

    /**
     * 将节点添加到链表的头部
     *
     * @param node
     */
    private void addToHead(DoubleLinkedNode node) {
        // 1.该节点的pre指针指向虚拟头结点
        node.pre = head;
        // 2.将该节点的next指针指向虚拟头结点的后继节点
        node.next = head.next;
        // 3.将之前虚拟头结点的后继节点的pre指针指向该节点
        head.next.pre = node;
        // 4.将虚拟头结点的next指针指向该节点
        head.next = node;
    }

    /**
     * 从链表中移除尾结点
     *
     * @return 待移除的节点
     */
    private DoubleLinkedNode removeTail() {
        DoubleLinkedNode tail = this.tail.pre;
        removeNode(tail);
        return tail;
    }


    /**
     * 双向链表：存储按使用顺序排序的键值对，链表头是最新被访问的，链表尾是最早被访问的
     *
     */
    class DoubleLinkedNode{
        /**
         * 键
         */
        int key;
        /**
         * 值
         */
        int value;
        /**
         * 前驱节点
         */
        DoubleLinkedNode pre;
        /**
         * 后继节点
         */
        DoubleLinkedNode next;

        public DoubleLinkedNode() {
        }

        public DoubleLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
