package leetcode.dp;

/**
 * 买卖股票最大收益模板
 * https://time.geekbang.org/column/article/293557
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润，你最多可以完成 k 笔交易。
 * 附加条件是：每次卖出股票之后 t 天内你无法进行任何交易，同时买入股票的时候会收取 c 元的交易手续费；
 * 你不能同时参与多笔交易，即你必须在再次购买前出售掉之前的股票
 *
 * @author: dingaimin
 * @date: 2021/2/12 21:08
 */
public class MaxProfitTemplate {

    /**
     * k 笔交易和手续费 c ，不影响整个问题的解题框架
     * 存储空间还是三维数组 dp[i][j][k+1] 表示在第 i 天，是否持有股票以及卖了几次股票的情况下，最大利润是多少
     *
     * 边界情况：
     * 1.当天结束时没有持股且当天结束时从未卖出过股票，这种情况利润为 0
     * 2.当天持股，而且卖出过 K 次股票，对于情况不存在的，利润设定为 -INF
     *
     * 状态转移方程
     * dp[i][j][k] = 0, case1: j = 0 && k = 0
     * dp[i][j][k] = max(dp[i-1][0][k], dp[i-1][1][k-1] + p[i]), case2: j = 0 && k <= k(max)
     * dp[i][j][k] = max(dp[i-1][1][k], dp[i-1-t][0][k] - p[i] - c), case3: j = 1 && k < k(max)
     * 解释：当天结束时持有股票，且卖出过 K 次股票。可能是今天买入的；也可能是之前买入的。分两种情况
     * 1.当天买入的，要考虑 t 天的冻结期，t 天内无法交易，所以上次是 (t+1) 天前，买入要考虑手续费
     * 2.前一天买入的，前一天是持股状态，且卖出过 k 次股票
     * dp[i][j][k] = -INF, case3: j = 1 && k = k(max)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int m = prices.length;
        if (m == 0 || k == 0) {
            return 0;
        }
        int c = 0;
        int t = 0;
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
                dp[i][1][k1] = Math.max(dp[i-1][1][k1], dp[i-1-t][0][k1] - prices[i] - c);
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

    public int maxProfit2(int k, int[] prices) {
        int m = prices.length;
        if (m == 0 || k == 0) {
            return 0;
        }
        // dp[i][j][k] 表示第 i 天结束时，是否持有股票的情况下，最大利润是多少
        int[][][] dp = new int[m][2][k+1];

        // 处理第一天
        // 第一天没有买入和第一天有买入
        for(int i = 0; i < k + 1; i++) {
            // case1: j = 0 && k = 0
            dp[0][0][i] = 0;
            // case2： j = 1 && k = 0
            dp[0][1][i] = -prices[0];
        }

        // 处理后续日期
        for(int i = 1; i < m; i++) {
            for(int k1 = 0; k1 < k + 1; k1++) {
                // case3：j=0 && k <= k(max)
                dp[i][0][k1] = k1 > 0 ? Math.max(dp[i-1][0][k1], dp[i-1][1][k1-1] + prices[i]) : 0;
                // case4: j=1 && k < k(max)
                if(k1 < k) {
                    dp[i][1][k1] = Math.max(dp[i-1][1][k1], dp[i-1][0][k1] - prices[i]);
                }
            }
            // case5: j=1 && k = k(max)
            dp[i][1][k] = Integer.MIN_VALUE;
        }

        int max = Integer.MIN_VALUE;
        for(int k1 = 1; k1 < k + 1; k1++) {
            max = dp[m-1][0][k1] > max ? dp[m-1][0][k1] : max;
        }
        return max;
    }
}
