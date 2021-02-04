package leetcode.kuaishou;

import java.util.Stack;

/**
 * 包含 min 函数的栈，要求时间复杂度尽量小
 * 面试过程中，我给的方案时间复杂度较高，面试完查了下资料，发现用两个栈来实现比较优雅
 *
 * @author: dingaimin
 * @date: 2021/1/24 18:40
 */
public class MinStack {

    /**
     * 存储所有的元素，保证 push、pop、top 函数的正常逻辑
     */
    Stack<Integer> stack1;
    /**
     * 存储 stack1 中非严格降序的元素，stack1中的最小元素始终对应 stack2 的栈顶元素
     * 这样在调用 min 函数时，只需要返回 stack2 的栈顶元素
     */
    Stack<Integer> stack2;

    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * 将元素 x 压入stack1
     * 还要保证 stack2 的非严格降序
     * 有两种情况需要将 x 压入 stack2
     * 1.stack2 为空
     * 2.x小于等于stack2的栈顶元素
     *
     * @param x
     */
    public void push(int x) {
        stack1.add(x);
        if (stack2.isEmpty() || x < stack2.peek()) {
            stack2.add(x);
        }
    }

    /**
     * 从 stack1 中弹出栈顶元素
     * 还要保证 stack1 和 stack2 元素的一致性
     * 有一种情况需要将 stack2 的栈顶元素出栈
     * 1.stack1弹出的元素等于 stack2的栈顶元素
     */
    public void pop() {
        Integer item = stack1.pop();
        if (item.equals(stack2.peek())) {
            stack2.pop();
        }
    }

    /**
     * 直接返回 stack1 的栈顶元素
     *
     * @return
     */
    public int top() {
        return stack1.peek();
    }

    /**
     * 直接返回 stack2 的栈顶元素
     *
     * @return
     */
    public int min() {
        return stack2.peek();
    }

}
