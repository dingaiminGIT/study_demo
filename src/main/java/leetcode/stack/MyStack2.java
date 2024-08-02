package leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * 在入栈时，先获取队列中的元素个数，然后将要入栈的元素入队列，再从队列中依次出队 n 个元素，并入队
 * @author: dingaimin
 * @date: 2021/1/8 13:48
 */
public class MyStack2 {

    Queue<Integer> queue;

    public MyStack2() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
       int n = queue.size();
       queue.offer(x);
        for (int i = 0; i < n; i++) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.peek();
    }

    /** Get the top element. */
    public int top() {
        return queue.poll();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
