package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache3 extends LinkedHashMap<Object, Object> {
    private static final long serialVersionUID = 1L;
    protected int maxElements;

    public LRUCache3(int maxSize) {
        super(maxSize, 0.75F, true);
        this.maxElements = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
        return this.size() > this.maxElements;
    }
}
