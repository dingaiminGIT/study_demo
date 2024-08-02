package leetcode.stack;

import java.util.Stack;

/**
 * 用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * 队列：先进先出
 * 栈：后进先出
 * 使用两个栈，一个栈s1用于元素入栈，一个栈s2用于元素出栈
 * pop() 和 peek() 时
 * 1.如果s2里有元素，直接从s2出栈
 * 2.如果s2里没有元素，先把s1中所有的元素出栈并入栈到s2中，然后从s2出栈
 *
 * 要保证s2为空时，才能把元素从s1中移动到s2中
 *
 * @author: dingaimin
 * @date: 2021/1/11 13:42
 */
public class MyQueue2 {

    /**
     * s1用来push
     */
    private Stack<Integer> s1;
    /**
     * s2用来pop
     */
    private Stack<Integer> s2;

    /** Initialize your data structure here. */
    public MyQueue2() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
