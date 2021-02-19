package leetcode.str;

import java.util.HashMap;

/**
 * 第一个只出现一次的字符
 * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 *
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 示例:
 *
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "
 *
 * @author: dingaimin
 * @date: 2021/2/17 21:06
 */
public class FirstUniqChar {

     public char firstUniqChar2(String s) {
             // 方法1哈希表
             HashMap<Character, Boolean> map = new HashMap<>(s.length());
             char[] sc = s.toCharArray();
             for(char c : sc) {
                 map.put(c, !map.containsKey(c));
             }
             for(char c : sc) {
                 if(map.get(c)) {
                     return c;
                 }
             }
             return ' ';
     }

    public char firstUniqChar(String s) {
        // 方法2 String 方法
        char[] sc = s.toCharArray();
        for(char c : sc) {
            int start = s.indexOf(c);
            int end = s.lastIndexOf(c);
            if(start == end) {
                return c;
            }
        }
        return ' ';
    }
}
