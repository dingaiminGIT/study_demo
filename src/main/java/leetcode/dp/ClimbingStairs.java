package leetcode.dp;

/**
 * 爬楼梯
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * 动态规划
 *
 * @author: dingaimin
 * @date: 2021/1/13 22:20
 */
public class ClimbingStairs {

    public static int climbStairs(int n) {
        int p = 0;
        int q = 0;
        int r = 1;
        for (int i = 1; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    public static int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
