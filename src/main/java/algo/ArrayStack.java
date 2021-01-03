package algo;

/**
 * 用数组实现栈
 */
public class ArrayStack {

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
    private int n;

    /**
     * 申请一个大小为n的栈
     *
     * @param n 栈的大小
     */
    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 入栈操作
     *
     * @param item
     * @return
     */
    public boolean push(String item) {
        // 如果栈已经满了(即数组空间不够)，返回false
        if (count == n) {
            return false;
        }

        // 将入栈的item插入到数组下标为 count的地方，count +1
        items[count] = item;
        ++count;
        return true;
    }

    /**
     * 出栈
     *
     * @return
     */
    public String pop() {
        // 如果栈为空，返回 null
        if (count == 0) {
            return null;
        }

        // 返回下标为 count -1 的元素，并将栈中元素个数减一
        String tmp = items[count-1];
        --count;
        return tmp;
    }


    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        for (int i = 0; i < 5; i++) {
            String item = String.valueOf(++i);
            arrayStack.push(item);
        }

        System.out.println(arrayStack.pop());
    }
}
