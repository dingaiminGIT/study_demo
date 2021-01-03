package algo;

import java.util.Arrays;

/**
 * LeetCode 242 有效的字母异位词（对应的字母个数一致）
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词
 *
 *
 */
public class AlphabeEctoicWord {

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        /*String s = "rat";
        String t = "car";*/
        AlphabeEctoicWord word = new AlphabeEctoicWord();
        boolean result = word.isAnagram(s, t);
        System.out.println(result);
    }

    /**
     * 用一个数组当做计数器，对其中一个字符串中的字母做＋1操作，对另外一个字符串中的字母做-1操作
     * 然后看这个计数器数组是否各个位置都为0
     * 执行用时 5ms 内存消耗 36.9MB
     * 此方法适用范围更广
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        // 如果两个字符串长度不同，肯定不是异位词
        if (s.length() != t.length()) {
            return false;
        }
        // 计数器数组
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // s.charAt(i) 获取字符串 s 中第 i 位的字符，比如当s="rat",i=0时，s.charAt(0)为 r 'r'-'a'=17
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        // 循环遍历机器数组，判断是否都为0
        // 方法有很多，方法1 遍历数组判断是否为0  方法2 遍历数组，为0对一个计数器+1，最后比较计数器与数组长度是否相等
        for (int i = 0; i < counter.length; i++) {
            if (0 != counter[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 两个字符串分别转成字符数组，然后排序，再挨个比较是否相等
     * 执行用时 4ms 内存消耗 38MB
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram1(String s, String t) {
        // 如果两个字符串长度不同，肯定不是异位词
        if (s.length() != t.length()) {
            return false;
        }
        // 转化为字符数组
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        // 排序
        Arrays.sort(sChars);
        System.out.println(Arrays.toString(sChars));
        Arrays.sort(tChars);

        // 如果两个数组完全相等，就是异位词
        return Arrays.equals(sChars, tChars);
    }
}
