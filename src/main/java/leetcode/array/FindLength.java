package leetcode.array;

/**
 * 高德广告 1 面
 * 718. 最长重复子数组 中等
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 * 示例 2：
 *
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/description/
 */
public class FindLength {

    /**
     * 动态规划
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        // 数组的+1是为了初始化边界条件。dp[i][j]通常用来存储以nums1[i-1]和nums2[j-1]结尾的最长公共子数组的长度。当i=0或j=0时，dp[i][j]应该为0，因为没有公共子数组是以数组的第一个元素之前的位置结尾的。因此，数组需要+1，以便dp[0][j]和dp[i][0]能够正确地表示这种边界情况
        int[][] dp = new int[n + 1][m + 1];
        int maxLen = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return maxLen;
    }
}
