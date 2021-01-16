package leetcode;

import java.util.Stack;

/**
 * 用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * 队列：先进先出
 * 栈：后进先出
 * 要想满足队列的FIFO特性，需要用到两个栈。一个用来反转元素的入队顺序，另一个用来存储元素的最终顺序
 *
 * @author: dingaimin
 * @date: 2021/1/11 12:51
 */
public class MyQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    /**
     * 队列是FIFO，栈是LIFO，所以最新压入的元素必须得放到栈底
     * 需要先把s1中所有元素移动到 s2中，接着把新元素压入s2，最后再把s2中的所有元素弹出到s1中
     *
     * @param x
     */
    public void push(int x) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s2.push(x);

        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return s1.pop();
    }

    /** Get the front element. */
    public int peek() {
        return s1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty();
    }
}
