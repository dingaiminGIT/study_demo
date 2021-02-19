package leetcode.string;

import java.util.HashMap;

/**
 * 第一个只出现一次的字符（美团外卖广告一面）
 * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 *
 * @author: dingaimin
 * @date: 2021/1/11 22:27
 */
public class FindUniqChar {

    public static void main(String[] args) {
        String s = "abaccdeff";
        System.out.println(firstUniqChar(s));
    }

    public static char firstUniqChar(String s) {
        HashMap<Character, Boolean> map = new HashMap<>(s.length());
        char[] sc = s.toCharArray();
        for (char c : sc) {
            map.put(c, !map.containsKey(c));
        }
        for (char c : sc) {
            if (map.get(c)) {
                return c;
            }
        }
        return ' ';
    }

    public static char firtUniqChar(String s) {
        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int firstIndex = s.indexOf(c);
            int endIndex = s.lastIndexOf(c);
            if (firstIndex == endIndex) {
                return c;
            }
        }
        return ' ';
    }

    // 遍历字符串的三种方式
    // 方法1 length(), s.charAt(i)
    public void loopStr1(String s) {
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i));
        }
    }

    // 方法2 length() subString(i,i+1)
    public void loopStr2(String s) {
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.substring(i, i + 1));
        }
    }

    // 方法3 toCharArray()
    public void loopStr3(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
    }

}
