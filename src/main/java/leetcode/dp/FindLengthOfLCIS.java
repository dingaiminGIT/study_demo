package leetcode.dp;

import java.util.Arrays;

/**
 * 最长连续递增序列
 * https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
 *
 * 给定一个未经排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度。
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 * 示例 1：
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 *
 * 示例 2：
 * 输入：nums = [2,2,2,2,2]
 * 输出：1
 * 解释：最长连续递增序列是 [2], 长度为1。
 *
 * @author: dingaimin
 * @date: 2021/2/13 10:48
 */
public class FindLengthOfLCIS {

    /**
     * 状态参数：不断变化的只有数组的索引，所以备忘录为 dp[i] 表示从位置 0 到位置 i 的最长连续递增序列的长度
     * 初始化状态：只考虑某个特定位置的数字，从开头到它为止的最长上升子序列一定 >=1，所以把备忘录的每一个位置都初始化为1
     * 状态转移方程：dp[i] = 1 + dp[i-1], n[i] > n[i-1]
     *             dp[i] = 1,otherwise
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n];
        // 初始化
        Arrays.fill(dp, 1);

        int res = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i-1]) {
                dp[i] = dp[i-1] + 1;
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    /**
     * 贪心
     * https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/solution/zui-chang-lian-xu-di-zeng-xu-lie-by-leet-dmb8/
     *
     * 对于下标范围 [l,r] 的连续子序列，如果对任意 l<=i<=r 都满足 nums[i] < nums[i+1]，则连续子序列是递增序列
     * 数组 nums 长度是 n，对于 0<l<=r<n-1，如果下标范围[l,r]的连续子序列是递增序列，则考虑 nums[l-1] 和 nums[r+1]
     * 1.如果 nums[l-1] < nums[l]，则将 nums[l-1] 加到 nums[l] 的前面，可以得到更长的连续递增序列
     * 2.如果 nums[r+1] > nums[r]，则将 nums[r+1] 加到 nums[r] 的后面，可以得到更长的连续递增序列
     *
     * 为了得到最长连续递增序列，可以使用贪心的策略得到尽可能长的连续递增序列。
     * 做法是使用记录当前连续递增序列的开始下标和结束下标，遍历数组的过程中每次比较相邻元素，
     * 根据相邻元素的大小关系决定是否需要更新连续递增序列的开始下标
     *
     * 怎么做？
     * start 表示连续递增序列的开始下标，初始时 start = 0，然后遍历数组 nums，进行如下操作
     * 1.如果下标 i > 0 && nums[i] <= nums[i-1]，则说明当前元素小于或等于上一个元素，
     *  则 nums[i-1] 和 nums[i]不可能属于同一个连续递增序列，必须从下标 i 开始一个新的连续递增序列，所以领 start = i
     *  如果 i=0 || nums[i] > nums[i-1] 不更新start 值
     * 2.下标范围 [start,i] 的连续子序列是递增序列，长度 i-start+1，使用当前连续递增序列的长度更新最长连续递增序列的长度
     *
     * 遍历结束之后，即可得到整个数组的最长连续递增序列的长度
     *
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public int findLengthOfLCIS2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int res = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] <= nums[i-1]) {
                start = i;
            }
            res = Math.max(res, i - start +1);
        }
        return res;
    }
}
