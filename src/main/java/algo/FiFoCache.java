package algo;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * FiFo-Cache
 *
 * 通过一个 LinkedList 来维护 key 的顺序
 * 当往 cache 中添加元素时，把 key 添加到 keyList 的最后，然后判断 keyList 的大小是否大于设置的大小
 * 如果大于就将 keyList 的头元素删掉，并从 cache 中删除
 *
 * @Author 白牙
 */
public class FiFoCache {

    private final Map<Object, Object> cache = new HashMap<>();
    private final Deque<Object> keyList = new LinkedList<>();
    private int size;

    public int getSize() {
        return cache.size();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void putObject(Object key, Object value) {
        cycleKeyList(key);
        cache.put(key, value);
    }

    public Object getObject(Object key) {
        return cache.get(key);
    }

    public Object removeObject(Object key) {
        return cache.remove(key);
    }

    public void clear() {
        cache.clear();
        keyList.clear();
    }

    private void cycleKeyList(Object key) {
        keyList.addLast(key);
        if (keyList.size() > size) {
            Object oldestKey = keyList.removeFirst();
            cache.remove(oldestKey);
        }
    }

    public static void main(String[] args) {
        FiFoCache cache = new FiFoCache();
        cache.setSize(5);

        for (int i = 0; i < 5; i++) {
            cache.putObject(i, i);
        }

        System.out.println(cache.getObject(0));
        cache.putObject(5, 5);
        System.out.println(cache.getSize());

    }
}
