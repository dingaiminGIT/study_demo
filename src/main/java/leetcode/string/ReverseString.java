package leetcode.string;

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

    /**
     * 反转字符串
     * @param str string字符串
     * @return string字符串
     */
    public String solve (String str) {
        char[] arr = str.toCharArray();
        int len = str.length();
        for(int i = 0; i < len; i++) {
            arr[i] = str.charAt(len - 1 -i);
        }
        return new String(arr);
    }

    /**
     * 原地交换，空间复杂度为 O(1)
     * 反转字符串
     * @param str string字符串
     * @return string字符串
     */
    public String solve2 (String str) {
        char[] arr = str.toCharArray();
        int n = str.length();
        for(int i = 0; i < n / 2; i++) {
            char t = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = t;
        }
        return new String(arr);
    }
}
