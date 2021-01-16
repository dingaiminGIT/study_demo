package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        int[] result = twoSum2(nums, target);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    /**
     * 暴力解法-两层for循环
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1 ; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }

        }
        return new int[0];
    }

    /**
     * 两遍哈希表
     * 一遍先把 nums 数组中的值和索引都放入到哈希表中
     * 第二遍遍历，判断 target - 哈希表的元素是否在哈希表中存在，同时要注意不能是起本身
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];

        // 第一次遍历，将值和索引放到哈希表中
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        // 第二次遍历，判断 target - num[i] 是否在哈希表中，注意不能是其本身
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (map.containsKey(num) && map.get(num) != i) {
                result[0] = map.get(num);
                result[1] = i;
                return result;
            }
        }

        return new int[0];

    }

    /**
     * 一次遍历哈希表
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (map.containsKey(num) && map.get(num) != i) {
                result[0] = map.get(num);
                result[1] = i;
                return result;
            } else {
                map.put(nums[i], i);
            }
        }

        return new int[0];

    }
}
