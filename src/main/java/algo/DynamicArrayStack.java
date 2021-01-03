package algo;

import java.util.Arrays;

/**
 * 用数组实现支持动态扩容的栈
 */
public class DynamicArrayStack {

    /**
     * 数组
     */
    private String[] items;

    /**
     * 栈中元素个数
     */
    private int count;

    /**
     * 栈的大小
     */
    private int size;

    public DynamicArrayStack(int size) {
        items = new String[size];
        this.size = size;
        this.count = 0;
    }

    /**
     * 入栈（支持扩容）
     *
     * @param item
     * @return
     */
    public boolean push(String item) {
        // 如果栈空间满了，需要扩容，这里以2倍的容量扩容
        if (count == size) {
            int newSize = 2 * size;
            // 把原来数组中的数据复制到新的数组中
            String[] newItems = Arrays.copyOf(items, newSize);
            this.items = newItems;
        }

        // 将元素 item 插入到数组中，下标为 count，并把栈中元素个数count加1
        items[count] = item;
        count++;
        return true;
    }

    /**
     * 出栈
     *
     * @return 返回栈顶的数据
     */
    public String pop() {
        // 如果栈为空，返回null
        if (count == 0) {
            return null;
        }

        // 返回下标为 count-1 的元素，并把栈中元素个数count减1
        String tmp = items[count -1];
        count--;
        return tmp;
    }

    public static void main(String[] args) {
        DynamicArrayStack arrayStack = new DynamicArrayStack(5);
        for (int i = 0; i < 10; i++) {
            String item = String.valueOf(++i);
            arrayStack.push(item);
        }

        System.out.println(arrayStack.pop());
    }
}
