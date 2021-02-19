package leetcode.dp;

/**
 * 买卖股票的最佳时机4
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * @author: dingaimin
 * @date: 2021/2/12 21:27
 */
public class MaxProfit4 {

    public static void main(String[] args) {
        MaxProfit4 test = new MaxProfit4();
        int[] prices = new int[]{1,3};

    }

    public int maxProfit(int k, int[] prices) {
        int m = prices.length;
        if (m == 0 || k == 0) {
            return 0;
        }

        int[][][] dp = new int[m][2][k+1];

        // 处理第一天
        // dp[i][j][k] = 0, case1: j = 0 && k = 0
        // 第一天没有买入
        for (int i = 0; i < k + 1; i++) {
            dp[0][0][i] = 0;
        }

        // 第一天不可能已卖出
        for (int i = 0; i < k + 1; i++) {
            dp[0][1][i] = -prices[0];
        }

        // 处理后续日期
        for (int i = 1; i < m; i++) {
            // dp[|i][j][k] = max(dp[i-1][0][k], dp[i-1][1][k-1] + p[i]), case2: j = 0 && k <= k(max)
            dp[i][0][0] = 0;
            for (int k1 = 1; k1 < k + 1; k1++) {
                dp[i][0][k1] = Math.max(dp[i-1][0][k1], dp[i-1][1][k1-1] + prices[i]);
            }
            // dp[i][j][k] = max(dp[i-1][1][k], dp[i-1-t][0][k] - p[i] - c), case3: j = 1 && k < k(max)
            for (int k1 = 0; k1 < k; k1++) {
                dp[i][1][k1] = Math.max(dp[i-1][1][k1], dp[i-1][0][k1] - prices[i]);
            }
            // dp[i][j][k] = -INF, case3: j = 1 && k = k(max)
            dp[i][1][k] = Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        for (int k1 = 1; k1 < k + 1; k1++) {
            max = dp[m-1][0][k1] > max ? dp[m-1][0][k1] : max;
        }
        return max;
    }
}
