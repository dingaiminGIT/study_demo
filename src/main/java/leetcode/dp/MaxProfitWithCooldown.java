package leetcode.dp;

/**
 * 最佳买卖股票时机含冷冻期
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * @author: dingaimin
 * @date: 2021/2/12 22:42
 */
public class MaxProfitWithCooldown {

    /**
     * 状态参数：天数、持股状态和是否在冷冻期
     * dp[i] 表示第i天结束后，最大收益
     * dp[i][0] 持有一支股票
     * dp[i][1] 不持有股票，且在冷冻期
     * dp[i][2] 不持有股票，不在冷冻期
     *
     * 初始化
     * 第一天
     * dp[0][0] = -p[0]
     * dp[0][1] = 0
     * dp[0][2] = 0
     *
     * 状态转移方程：
     * dp[i][0] = max(dp[i-1][0], dp[i-2][2] - p[i])  , case1：第 i 天结束后，持有一支股票。可能是当天买入的，那么第i-1天不能持有股票且不在冷冻期，也可能在第 i-1 天就已经持有了
     * dp[i][1] = dp[i-1][0] + p[i-1], case2: 第 i 天结束后，不持有股票，但在冷冻期，说明在当天卖出了股票，说明在第i-1天，必须持有一支股票
     * dp[i][2] = max(dp[i-1][2], dp[i-1][1]), case3: 第 i 天结束后，不持有股票，不在冷冻期。说明第i-1天没有操作。如果处于冷冻期，对应状态 dp[i-1][1];如果不处于冷冻期，对应状态 dp[i-1][2]
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int m = prices.length;
        if (m == 0) {
            return 0;
        }

        int[][] dp = new int[m][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = Math.max(dp[i-1][2] - prices[i], dp[i-1][0]);
            dp[i][1] = dp[i-1][0] + prices[i];
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
        }
        return Math.max(dp[m-1][1], dp[m-1][2]);
    }

    // TODO 写的有问题
    public int maxProfit2(int[] prices) {
        int m = prices.length;
        if (m == 0) {
            return 0;
        }
        int t = 1;
        // 第 i 天结束时，是否持有股票的最大收益
        int[][] dp = new int[m][2];

        // 处理第一天
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 处理后续日期
        for (int i = 1; i < m; i++) {
            // j=0 不持有股票
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            // j=1 持有股票
            dp[i][1] = Math.max(dp[i-1][1], dp[i-t-1 >= 0 ? i-t-1 : 0][0] + prices[i]);
        }
        return dp[m-1][0];
    }
}
