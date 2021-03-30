package leetcode.dp;

/**
 * 回文子串
 * https://leetcode-cn.com/problems/palindromic-substrings/
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 示例 1：
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 * 示例 2：
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 * @author: dingaimin
 * @date: 2021/2/8 9:43
 */
public class CountSubstrings {

    public int countSubstrings(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }

        int res = 0;
        // 状态定义：dp[i][j] 所对应的值是子串 i...j 是否是回文子串（true or false）
        boolean[][] dp = new boolean[n][n];

        // 初始化
        for (int i = 0; i < n; i++) {
            // 单个子串一定是回文子串
            dp[i][i] = true;
            res++;
        }
        // 状态转移方程
        // dp[i][j] = dp[i+1][j-1],s[i]==s[j]，这个状态转移方程是缺少情况的 j-i < 2
        // 比如 aaa，开始i=0,j=1,根据状态转移方程 dp[i][j] = dp[i+1][j-1],s[i]==s[j]，dp[0][1] = dp[1][0] 这个是不存在的，
        // 但 aa 是回文子串，所以需要加上 j-i<2,
        // dp[i][j] = false,s[i]不等于s[j]
        // 外层循环代表结束位置，内能循环代表起始位置
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                // 遍历位置 起始位置0到结束位置1，起始位置0到结束位置2...起始位置n-1到结束位置n
                if(s.charAt(i) == s.charAt(j)) {
                    // 转化成子问题
                    dp[i][j] = dp[i+1][j-1] || j-i < 2;
                }
                if (dp[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }
}
