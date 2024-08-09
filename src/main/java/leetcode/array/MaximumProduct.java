package leetcode.array;

import java.util.Arrays;

/**
 * 628. 三个数的最大乘积
 * 蔚来汽车 1 面
 * https://leetcode.cn/problems/maximum-product-of-three-numbers/description/
 */
public class MaximumProduct {

    /**
     * 先将数组排序，如果数组中全是非负数，那最大的三个数的乘积就是最大值
     * 如果数组中有负数，那最大的三个数乘积可能是负数，也可能是正数，取最大值即可
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0]*nums[1]*nums[n-1], nums[n-3]*nums[n-2]*nums[n-1]);
    }

    /**
     * 在方法一中，我们实际上只要求出数组中最大的三个数以及最小的两个数，
     * 因此我们可以不用排序，用线性扫描直接得出这五个数
     *
     * @param nums
     * @return
     */
    public int maximumProduct2(int[] nums) {
        // 初始化最小值为最大值：确保在遍历数组时，任何元素都能比它们小，从而正确地更新最小值。
        // 初始化最大值为最小值：确保在遍历数组时，任何元素都能比它们大，从而正确地更新最大值
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }

            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}
