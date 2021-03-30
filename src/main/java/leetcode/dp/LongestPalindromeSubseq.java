package leetcode.dp;

/**
 * 最长回文子序列
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 *
 * @author: dingaimin
 * @date: 2021/2/11 17:06
 */
public class LongestPalindromeSubseq {

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        // 思考
        // 1.先看初始化状态 -> 单个字符一定是它自己的回文
        // 2.再确定状态参数 -> 需要确定子序列的位置：一个是起始位置，一个是终止位置，而且在算法执行过程中，起始和终止位置是变化的，所以当做状态参数
        // 根据上面确定了两个状态参数，那么需要用二位数组定义备忘录 即 DP[i][j]，代表字符串 i...j 中最长回文子序列的长度
        // 3.编写状态转移方程 -> 找子问题 dp[i+1][j-1]   dp[i][j] = dp[i+1][j-1] + 决策，区分 s[i]与s[j]是否相等
        // dp[i][j] = 2 + dp[i+1][j-1] (s[i]==s[j])
        // dp[i][j] = max（dp[i][j-1],dp[i+1][j]) (s[i]!=s[j])
        int[][] dp = new int[n][n];

        // 初始化状态
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 状态转移
        // 外循环代表起始位置(这里要注意，为了让子问题 dp[i+1][j-1]有值，外循环要从后往前遍历)，内循环代表结束位置
        for (int i = n-1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
