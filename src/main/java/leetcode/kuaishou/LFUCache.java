package leetcode.kuaishou;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LFUCache 最不经常使用缓存
 *
 * @author: dingaimin
 * @date: 2021/1/24 13:16
 */
public class LFUCache {

    /**
     * 当前LFUCache的最大容量
     */
    private int capacity;

    /**
     * 最小频率
     */
    private int minFreqency;
    /**
     * key:键值对的key
     * value:存放的是缓存的节点在 freqencyMap 中的节点
     */
    private Map<Integer, Node> keyMap;
    /**
     * 频率-缓存的链表，且保证链表中的从链表头到链表尾的插入时间是有序的
     */
    private Map<Integer, LinkedList<Node>> freqencyMap;


    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreqency = 0;
        this.keyMap = new HashMap<>();
        this.freqencyMap = new HashMap<>();
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        // 如果 keyMap 中不存在指定的 key ，返回 -1
        if (!keyMap.containsKey(key)) {
            return -1;
        }
        Node node = keyMap.get(key);
        int value = node.value;
        int freqency = node.freqency;
        // 从 freqencyMap 中删除该节点，然后插入到 freqency 对应的链表中
        freqencyMap.get(freqency).remove(node);

        // 链表为空的话，从哈希表中删除，且更新 minFreqency
        if(freqencyMap.get(freqency).size() == 0) {
            freqencyMap.remove(freqency);
            if(minFreqency == freqency) {
                minFreqency = minFreqency + 1;
            }
        }

        // 插入到 freqency+1的链表中
        LinkedList<Node> list = freqencyMap.getOrDefault(freqency + 1, new LinkedList<>());
        list.offerFirst(new Node(key, value, freqency + 1));
        freqencyMap.put(freqency + 1, list);
        keyMap.put(key, freqencyMap.get(freqency + 1).peekFirst());
        return value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        // 缓存中不包含 key-value，添加
        if (!keyMap.containsKey(key)) {
            // 判断缓存是否满了
            if (keyMap.size() == capacity) {
                // 缓存满了，需要删除最近最少使用的缓存，所以这里取尾节点
                Node node = freqencyMap.get(minFreqency).peekLast();
                keyMap.remove(node.key);
                // 移除尾节点
                freqencyMap.get(minFreqency).pollLast();
                // 如果最小频率对应的链表是空，就移除这个链表
                if (freqencyMap.get(minFreqency).size() == 0) {
                    freqencyMap.remove(minFreqency);
                }
            }
            // 缓存未满
            // 新创建的初始频率是1
            LinkedList<Node> list = freqencyMap.getOrDefault(1, new LinkedList<>());
            // 为了保证插入时间有序，插入到头结点
            list.offerFirst(new Node(key, value, 1));
            freqencyMap.put(1, list);
            keyMap.put(key, freqencyMap.get(1).peekFirst());
            // 更新最小频率
            minFreqency = 1;
        } else {
            // 缓存中存在，类似 get 操作，但需要更新其value
            Node node = keyMap.get(key);
            int freqency = node.freqency;
            // 移除该节点
            freqencyMap.get(freqency).remove(node);
            if (freqencyMap.get(freqency).size() == 0) {
                freqencyMap.remove(freqency);
                if (minFreqency == freqency) {
                    minFreqency = minFreqency + 1;
                }
            }
            LinkedList<Node> list = freqencyMap.getOrDefault(freqency + 1, new LinkedList<>());
            list.offerFirst(new Node(key, value, freqency + 1));
            freqencyMap.put(freqency + 1, list);
            keyMap.put(key, freqencyMap.get(freqency + 1).peekFirst());
        }
    }

    class Node {
        int key;
        int value;
        /**
         * 键值对对应的频率
         */
        int freqency;

        public Node(int key, int value, int freqency) {
            this.key = key;
            this.value = value;
            this.freqency = freqency;
        }
    }
}
