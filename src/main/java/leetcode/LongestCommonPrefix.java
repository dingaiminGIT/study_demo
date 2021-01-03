package leetcode;

/**
 * 最长公共前缀
 * https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = new String[]{"flower","flow","flight"};
        String res = longestCommonPrefix4(strs);
        System.out.println(res);
    }


    /**
     * 方法1 横向扫描
     * 依次遍历字符串数组中的每个字符串，对于每个遍历到的字符串，更新最长公共前缀，当遍历完所有字符串后，即得到最长公共前缀
     * 时间复杂度 O(mn) 其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量，最坏情况下，字符串中的所有字符串的每个字符都要比较一下
     * 空间复杂度 O(1) 使用额外空间的复杂度为常数
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    /**
     * 求两个字符串的公共最长前缀
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 方法2：纵向遍历
     * 从前往后遍历所有字符串的列，比较相同列上的字符串是否相同，如果相同则进行下一列的比较，如果不相同则当前列不属于公共前缀，当前列之前的部分是公共前缀
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        // 遍历第一行的字符串的各个列
        for (int i = 0; i < length; i++) {
            // 获取第一行的字符串的第i列的字符
            char c = strs[i].charAt(i);
            // 遍历其他行的字符串，并获取第i列的值进行比较
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }


    /**
     * 分治递归
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix4(String[] strs)  {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }
    }

    public static String longestCommonPrefix(String[] strs, int start, int end)  {
        // 终止条件
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String left = longestCommonPrefix(strs, start, mid);
            String right = longestCommonPrefix(strs, mid + 1, end);
            return longestCommonPrefix(left, right);
        }
    }

    /**
     * 逆向思维：把字符串 a 与当前的 str 匹配，如果 Str 不包含以a为前缀的字符串，则不断减少a的长度，直到str 包含以a为前缀的字符串为止
     * https://leetcode-cn.com/problems/longest-common-prefix/solution/javashuang-bai-by-dong-men/
     * @param strs
     * @return
     */
    public static String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if ("".equals(prefix)) {
                return "";
            }
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() -1 );
            }
        }
        return prefix;
    }
}
