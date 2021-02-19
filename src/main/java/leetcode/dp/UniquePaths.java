package leetcode.dp;

/**
 * 不同路径
 * 难度：中等
 * https://leetcode-cn.com/problems/unique-paths/
 *
 * @author: dingaimin
 * @date: 2021/2/11 18:09
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        // 1.初始化状态
        // 第1行和第1列。其中第1行只能从左侧的格子往右走一步；第1列的格子只能从上面的格子往下走一步，
        // 所以第一行和第一列的格子只存在一条路径

        // 2.状态参数
        // 行数i和列数j是状态参数

        // 3.状态定义和状态转移方程
        // dp[i][j] 表示第i行、第j列的路径数量
        // dp[i][j] = 1,i=0 && j=0 即第一行和第一列
        // dp[i][j] = dp[i-1][j] + dp[i][j-1] ，i不等于0或j不等于0

        int[][] dp = new int[m][n];

        // 初始化状态
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
