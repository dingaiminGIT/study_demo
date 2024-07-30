package leetcode.string;

import java.util.Stack;

/**
 * 给定一串数字字符串,迭代消除其中的连续的816
 * 【输入描述】
 * 比如: 818816168166  消除后是空
 * 818166
 * 816
 * <p>
 * 比如: 81818166  消除后是81
 * 【输出描述】
 * 要求: 写出完整的代码实现,对足够极端的情况考虑尽可能优化的算法处理
 * 我用是递归实现的，面试官说可以用栈来实现
 *
 * @author: dingaimin
 * @date: 2021/1/27 21:44
 */
public class Trim816 {

    public static void main(String[] args) {
        String str = "818816168166";
        String trim = Trim816.trim(str);
        System.out.println(trim); // 输出应该是空字符串

        str = "81818166";
        trim = Trim816.trim(str);
        System.out.println(trim); // 输出应该是 "81"
    }

    /**
     * 利用栈的后进先出（LIFO）特性。具体思路如下：
     * <p>
     * 遍历字符串的每个字符。
     * 将字符压入栈中。
     * 每次压入字符后，检查栈顶的三个字符是否为 "816"。（这里可以判断栈的大小是否大于等于 3）
     * 如果是 "816"，则弹出这三个字符。如果不是“816”，按照原来的顺序压栈，继续上面的操作
     * 最后，栈中剩下的字符就是消除 "816" 后的结果。
     *
     * @param str
     * @return
     */
    public static String trimByStack(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            // 字符串从前往后入栈
            stack.push(str.charAt(i));

            // 栈里字符数量够 3 个就出栈判断是否为 816
            if (stack.size() >= 3) {
                char third = stack.pop();
                char second = stack.pop();
                char first = stack.pop();

                // 如果不是816，再入栈
                if (!(first == '8' && second == '1' && third == '6')) {
                    stack.push(first);
                    stack.push(second);
                    stack.push(third);
                }
            }
        }

        // 栈中留下的就是去掉 816后剩下的
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : stack) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /**
     * 用递归实现，直接找 816
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        int i = str.indexOf("816");
        if (i == -1) {
            // 没有找到，直接返回字符串
            return str;
        } else {
            // 找到了，删掉816，把816前后字符串拼接，递归调用trim方法
            String preStr = str.substring(0, i);
            String postStr = str.substring(i + 3);
            return trim(preStr + postStr);
        }
    }

    /**
     * 用递归实现，这个方法找 8，不好，因为没有判断下标是否越界的情况
     *
     * @param str
     * @return
     */
    public static String trim2(String str) {
        int i = str.indexOf("8");
        while (i != -1) {
            // 判断 8 后面是不是16
            char c1 = str.charAt(i + 1);
            char c2 = str.charAt(i + 2);
            if (c1 == '1' && c2 == '6') {
                // 找到了816,要删掉这个816，然后用新字符串递归调用trim方法
                if (i == 0) {
                    // 说明816在字符串首
                    String postStr = str.substring(i + 3);
                    return trim2(postStr);
                } else {
                    // 说明816前面还有字符，需要拼接前后两个部分
                    String preStr = str.substring(i - 1, c2);
                    String postStr = str.substring(i + 3);
                    String strNew = preStr + postStr;
                    return trim2(strNew);
                }

            } else {
                // 往后找816
                String strNew = str.substring(i + 3);
                return trim2(strNew);
            }
        }
        // 没有816
        return str;
    }
}
