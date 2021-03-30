package leetcode.dp;

import java.util.Arrays;

/**
 * 零钱兑换
 * https://leetcode-cn.com/problems/coin-change/
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的
 *
 * @author: dingaimin
 * @date: 2021/1/27 11:13
 */
public class CoinChange {

    /**
     * 自下而上的思考方法
     * F(i) 为组成金额 i 所需要的最少硬币数量
     * 假设在计算 F(i)之前，已经计算出了 F(0)到F(i-1)的答案
     * 则转移方程 F(i) = minF(i-cj) + 1 ,其中 j=0,1,...,i-1
     * cj 代表的是第j枚硬币的面值；即我们枚举最后的一枚硬币的面额是 cj，需要从 i-cj 这个金额的状态 F(i-cj)转移过来即加1
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
