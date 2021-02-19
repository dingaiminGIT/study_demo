package leetcode.dp;

import java.util.Arrays;

/**
 * 最长递增子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *  
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 * @author: dingaimin
 * @date: 2021/2/13 16:18
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        LengthOfLIS test = new LengthOfLIS();
        int[] nums = new int[]{0,1,0,3,2,3};
        int res = test.lengthOfLIS(nums);
        System.out.println(res);
    }

    /**
     * 1.状态参数：当前遍历的索引位置
     * 2.备忘录：dp[i] 表示从位置 0 到位置 i 的最长连续递增子序列的长度
     * 3.初始化：如果只考虑某个特定位置的数字，从开头到它为止的最长上升序列一定 >= 1，所以可以都初始化成 1
     * 4.状态转移方程：dp[i] = max(dp[i], dp[j] + 1),i > j && nums[i] > nums[j]
     *
     * 85ms 11.64%
     * 38.1MB 46.38%
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        // 初始化
        Arrays.fill(dp, 1);

        // 记录答案的值
        int res = 1;

        // 外层循环决策
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    // 这里不能是 dp[i] = dp[j] + 1，因为内层循环会多次，可能会把 dp[i] 由之前的一个较大值变成教小值
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    res = Math.max(dp[i], res);
                }
            }
        }
        return res;
    }
}
