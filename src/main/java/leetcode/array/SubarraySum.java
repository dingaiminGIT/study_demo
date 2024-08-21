package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/QTMn0o/
 * LCR 010. 和为 K 的子数组
 *
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 *
 * 示例 1：
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * 示例 2：
 *
 * 输入:nums = [1,2,3], k = 3
 * 输出: 2
 */
public class SubarraySum {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1,-1,0}, 0));
    }

    public static int subarraySum(int[] nums, int k) {
        // 存储前缀和及其出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 初始化，前缀和为 0，次数为 1
        prefixSumCount.put(0, 1);

        // 当前元素的前缀和
        int currentSum = 0;
        int res = 0;

        for(int num : nums) {
            // 计算当前的前缀和
            currentSum += num;
            // 如果前缀和减去 k 的结果在 map 中出现过，则表示存在连续子数组的和为 k
            if(prefixSumCount.containsKey(currentSum - k)) {
                res += prefixSumCount.get(currentSum - k);
            }

            // 更新 prefixSumCount中当前前缀和的出现次数
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }
        return res;
    }

    /**
     * 提交时不通过
     * [1,-1,0] k=0，预期结果是 3，因为 1，-1，0这个也算，这里不是说只能连续两个元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumError(int[] nums, int k) {
        int left = 0;
        int right = 1;

        int length = nums.length - 1;

        List<List<Integer>> res  = new ArrayList<>();

        while(right <= length) {
            int temp = nums[left] + nums[right];
            if(temp == k) {
                List<Integer> tem = new ArrayList<>();
                tem.add(nums[left]);
                tem.add(nums[right]);
                res.add(tem);
            }
            left++;
            right++;
        }
        // 考虑同一个元素
        int index = 0;
        while (index <= length) {
            if (nums[index] == k) {
                List<Integer> tem = new ArrayList<>();
                tem.add(nums[index]);
                res.add(tem);
            }
            index++;
        }
        return res.size();
    }

}
