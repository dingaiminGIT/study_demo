package leetcode.dp;

/**
 * 乘积最大子数组
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 * 动态规划
 *
 * https://leetcode-cn.com/problems/maximum-product-subarray/solution/dpfang-fa-xiang-jie-by-yang-cong-12/
 *
 * @author: dingaimin
 * @date: 2021/2/7 22:37
 */
public class MaxProduct {

    /**
     * 乘法需要注意正负号
     * 维护两个变量 max 和 min
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        // 初始化DP
        max[0] = nums[0];
        min[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            max[i] = Math.max(nums[i], Math.max(max[i-1]*nums[i], min[i-1]*nums[i]));
            min[i] = Math.min(nums[i], Math.min(max[i-1]*nums[i], min[i-1]*nums[i]));
            res = Math.max(max[i], res);
        }
        return res;
    }
}
