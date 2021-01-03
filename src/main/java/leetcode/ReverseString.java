package leetcode;

/**
 * 反转字符串
 * https://leetcode-cn.com/problems/reverse-string/
 */
public class ReverseString {

    public static void main(String[] args) {
        char[] s = new char[]{'h','e','l','l','o'};
        reverseString(s);
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]);
        }
    }

    public static void reverseString(char[] s) {
        int head = 0;
        int tail = s.length - 1;
        while (head <= tail) {
            //swap
            char temp = s[head];
            s[head] = s[tail];
            s[tail] = temp;
            head++;
            tail--;
        }
    }
}
