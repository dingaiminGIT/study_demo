package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU-Cache
 * 用 LinkedHashMap 实现
 * 通过一个 HashMap 用了存放 key-value
 * 用 LinkedHashMap 维护 key 的顺序，且设置访问重排序即某个元素被访问后，被放到前面
 * @Author 白牙
 */
public class LruCache {

    private final Map<Object, Object> cache = new HashMap<>();
    private Map<Object, Object> keyMap;
    private Object eldestKey;

    public int getSize() {
        return cache.size();
    }

    public void setSize(final int size) {
        keyMap = new LinkedHashMap<Object, Object>(size, 0.75F, true) {

            /**
             * put 和 putAll 方法调用时会调用 removeEldestEntry 方法
             *
             * @param eldest
             * @return
             */
            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                boolean tooBig = size() > size;
                if (tooBig) {
                    eldestKey = eldest.getKey();
                }
                return tooBig;
            }
        };
    }

    public void putObject(Object key, Object value) {
        cache.put(key, value);
        cycleKeyList(key);
    }

    public Object getObject(Object key) {
        // 对 key 进行重排序
        keyMap.get(key);
        return cache.get(key);
    }

    public Object removeObject(Object key) {
        return cache.remove(key);
    }

    public void clear() {
        cache.clear();
        keyMap.clear();
    }

    private void cycleKeyList(Object key) {
        keyMap.put(key, key);
        if (eldestKey != null) {
            cache.remove(eldestKey);
            eldestKey = null;
        }
    }

    public static void main(String[] args) {
        LruCache cache = new LruCache();
        cache.setSize(5);

        for (int i = 0; i < 5; i++) {
            cache.putObject(i, i);
        }
        System.out.println(cache.getObject(0));
        cache.putObject(5, 5);
        System.out.println(cache.getObject(1));
        System.out.println(cache.getSize());
    }
}
