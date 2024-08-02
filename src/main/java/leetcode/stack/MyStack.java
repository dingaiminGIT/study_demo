package leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 *
 * @author: dingaimin
 * @date: 2021/1/8 13:39
 */
public class MyStack {

    /**
     * 栈：后进先出
     * 队列：先进先出
     *
     * 思想：
     * 用两个队列实现栈的操作
     * queue1用于存储栈内的元素
     * queue2 作为入栈操作的辅助队列
     * 入栈操作，先将元素入队到 queue2，然后将 queue1 中全部元素依次出队并入队到 queue2,这个时候 queue2 的队头元素就是刚入栈的队列，再将 queue1 和 queue2 互换，queue1 的元素即位站内元素
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * Queue的常用方法
     * offer(x) 从队尾入队
     * poll() 从队头出队
     * peek() 获取队头的元素，不出队
     * isEmpty() 判断队列是否为空
     */

    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }

}
