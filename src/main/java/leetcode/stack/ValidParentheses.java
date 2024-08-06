package leetcode.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    public static void main(String[] args) {
        String s = "()[]{}";
        System.out.println(isValid2(s));
    }

    /**
     * // 方法1 用栈，将左括号入栈，遇到右括号，看栈顶元素是否匹配
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        int n = s.length();
        // 如果是奇数，直接返回
        if ((n & 1) == 1) { // 也可以用 n%2=1
            return false;
        }

        // 存放右括号-> 左括号的映射
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        // 栈
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 如果是右括号，如果栈为空或者栈顶元素不是和该右括号匹配的左括号，返回false
            if (map.containsKey(c)) {
                if (stack.isEmpty() || !stack.peek().equals(map.get(c))) {
                    return false;
                }
                // 如果匹配，就将栈顶的左括号出栈
                stack.pop();
            } else {
                // 将左括号压栈
                stack.push(c);
            }
        }
        // 如果匹配的话，最后栈应该是空的，如果不是空的，说明不匹配
        return stack.isEmpty();
    }

    /**
     * 方法2
     * 若为 左括号，则往栈中存右括号
     * 若为 右括号，如果栈为空或者该右括号与从栈顶取出的不一样，返回 false
     *
     * @param s
     * @return
     */
    public static boolean isValid2(String s) {
        int n = s.length();
        // 如果是奇数，直接返回
        if ((n & 1) == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
                // 走到下面说明是右括号
            } else if (stack.isEmpty() || c != stack.pop()) {
                // 如果栈为空，说明没有匹配的左括号，或者该右括号与从栈顶取出的不一样，返回 false
                return false;
            }
        }
        return stack.isEmpty();
    }
}
