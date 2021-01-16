package leetcode;

/**
 * 买卖股票的最佳时机
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 * 数组+动态规划
 *
 * @author: dingaimin
 * @date: 2021/1/16 20:54
 */
public class BestTimeToBuyAndSellStock2 {

    /**
     * 方法1：动态规划
     *
     * 定义状态
     * dp[i][0] 表示第i天交易完后手里没有股票的最大利润
     * dp[i][1] 表示第i天交易完后手里有股票的最大利润
     *
     * 转移方程
     * dp[i][0] = max{dp[i-1][0], dp[i-1][1] + prices[i]}  : 即前一天没有股票的收益为 dp[i-1][0] ；前一天有股票，但今天卖了，收益为 dp[i-1][0] + prices[i] 然后取这两个的较大值
     * dp[i][1] = max{dp[i-1][0] - prices[i], dp[i-1]][1]} : 即前一天没有股票，今天买入了，收益为 dp[i-1][0] - prices[i]；前一天有股票，今天没有操作，收益为 dp[i-1][1]
     *
     * 初始状态
     * dp[0][0] = 0
     * dp[0][1] = -prices[0]
     *
     * 重点：全部交易结束后，持有股票的收益一定低于没有股票的收益，即 dp[n-1][0] 的收益大于 dp[n-1][1]，所以最后的答案为 dp[n-1][0]
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][0] - prices[i], dp[i-1][1]);
        }
        return dp[n-1][0];
    }

    /**
     * 注意到上面的状态转移方程中，每一天的状态只与前一天的状态有关，而与更早的状态都无关，因此我们不必存储这些无关的状态
     * 只需要将 dp[i-1][0] 和dp[i−1][1] 存放在两个变量中
     * 通过它们计算出 dp[i][0] 和 dp[i][1] 并存回对应的变量，以便于第 i+1天的状态转移即可
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int dp0 = 0;
        int dp1 = -prices[0];
        for (int i = 1; i < n; i++) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp0 - prices[i], dp1);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }
}
