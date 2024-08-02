package leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长不含重复字符的子字符串
 * https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
 *
 * @author: dingaimin
 * @date: 2021/1/31 20:34
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring test = new LengthOfLongestSubstring();
        String s = "abcabcbb";
        int res = test.lengthOfLongestSubstring(s);
        System.out.println(res);

    }

    /**
     * 双指针（滑动窗口）+哈希表
     *  https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/solution/mian-shi-ti-48-zui-chang-bu-han-zhong-fu-zi-fu-d-9/
     *  哈希表存 字符 s[j] 最后一次出现对应的下标索引
     *  更新左指针：根据上轮左指针 i 与 dic[s[j]]，每轮更新左指针，保证区间 [i+1,j] 内没有重复字符且最大 i = max(i,dict[s[j]])
     *  更新结果res：取上轮res与本轮双指针区间[i+1,j]的宽度即 j-i 中的最大值 res = max(res, j-i)
     *
     *  时间复杂度：O(N) N 为字符串长度
     *  空间复杂度：O(1) 字符的ASCII码范围 0-127 哈希表最多使用 O(128)即O(1)大小的额外空间
     *
     *  执行时间 9ms 击败 31.83%
     *  内存消耗 38.5MB 击败 66.89%
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希表存放 s[j] 最后一次出现的索引
        Map<Character, Integer> map = new HashMap<>();
        // 左指针i，初始化-1;
        // res为最长不含充分福字符的子字符串的长度
        int i = -1, res = 0;
        // 遍历字符串
        for (int j = 0; j < s.length(); j++) {
            // 查看哈希表中是否存在 s[j]
            if (map.containsKey(s.charAt(j))) {
                // 存在就更新左指针 i
                i = Math.max(i, map.get(s.charAt(j)));
            }
            // 记录右指针 j
            map.put(s.charAt(j), j);
            // 更新结果
            res = Math.max(res, j - i);
        }
        return res;
    }
}
