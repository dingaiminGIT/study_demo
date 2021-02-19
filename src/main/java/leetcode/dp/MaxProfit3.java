package leetcode.dp;

/**
 * 买卖股票的最佳时机3
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * @author: dingaimin
 * @date: 2021/2/12 20:13
 */
public class MaxProfit3 {

    /**
     * 初始化状态：
     *  1.当天结束时没有持股，而且截止到当天结束时没有卖出过股票，利润一定是0
     *  2.当天持股，而且卖出过2次股票，这种情况是不存在的，即终止条件
     *
     *  问题转换：前一天的利润 + 当天的决策；当天结束时是否持有股票，以及当天买卖股票确定当天结束时的总收益
     *  每天结束时总利润取决于：1.前一天的总利润 2.当天结束时是否持有股票 3.当天是否买进或卖出股票
     *
     *  买卖股票次数有限制 2 次。对于上面的第3个因素可以进一步具体化：1.未卖出过股票 2.卖出过1次 3.卖出过2次
     *
     * 状态参数：天数、当天结束时是否持有股票、股票卖出的次数
     * 即 根据上面的三个状态参数，再结合前一天赚取的总李运，就可以得到当天的最优解了
     *
     * 状态存储空间：dp[i][j][k] 表示在第 i 天，是否持有（j=0表示未持有，j=1表示持有）
     *              以及卖出的次数（k=0表示卖出过1次，k=1表示卖出过1次，k=2表示卖出过2次）
     *
     * 状态转移方程：
     * dp[i][j][k] = 0, case1:j=0 && k=0 表示当天结束时未持股，且没有卖出过股票，收益肯定为0
     * dp[i][j][k] = max(dp[i-1][0][1], dp[i-1][1][0] + p[i]), case1:j=0 && k=1 表示当前结束时未持股，但卖出过1次
     * dp[i][j][k] = max(dp[i-1][0][2], dp[i-1][1][1] + p[i]), case1:j=0 && k=2 表示当前结束时未持股，但卖出过2次
     * dp[i][j][k] = max(dp[i-1][1][0], dp[i-1][0][0] - p[i]), case1:j=1 && k=0 表示当天结束时持股，卖出过0次
     * dp[i][j][k] = max(dp[i-1][1][1], dp[i-1][0][1] - p[i]), case1:j=1 && k=1 表示当天结束时持股，卖出过1次
     * dp[i][j][k] = -INF, case1:j=1 && k=2 表示当天结束时持股，卖出过2次
     *
     * 87ms 28.71%
     * 57.9MB 24.85%
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int m = prices.length;

        // dp[i][j][k] 表示在第 i 天，是否持有（j=0表示未持有，j=1表示持有） 以及卖出的次数（k=0表示卖出过1次，k=1表示卖出过1次，k=2表示卖出过2次）
        int dp[][][] = new int[m][2][3];
        // 处理第一天
        // 第一天没有买入
        dp[0][0][0] = 0;
        dp[0][0][1] = 0;
        dp[0][0][2] = 0;

        // 第一天不可能已卖出
        dp[0][1][0] = -prices[0];
        dp[0][1][1] = -prices[0];
        dp[0][1][2] = -prices[0];

        // 处理后续日期
        for (int i = 1; i < m; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Math.max(dp[i-1][0][1], dp[i-1][1][0] + prices[i]);
            dp[i][0][2] = Math.max(dp[i-1][0][2], dp[i-1][1][1] + prices[i]);
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][0][0] - prices[i]);
            dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][1] - prices[i]);
            dp[i][1][2] = 0;
        }

        return Math.max(dp[m-1][0][1], dp[m-1][0][2]);
    }

    public int maxProfit2 (int[] prices) {
        int m = prices.length;
        if(m == 0) {
            return 0;
        }

        int k = 2;
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
