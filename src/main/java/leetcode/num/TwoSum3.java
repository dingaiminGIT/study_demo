package leetcode.num;

import java.util.HashSet;
import java.util.Set;

/**
 * 有序数组int类型，1,1,2,2,3,4,4,5
 * 输入 target，找到想加等于 target 的所有组合情况，不能重复
 *
 * 示例 target 6
 * 输出 1,8  3,7
 *
 * @author: dingaimin
 * @date: 2021/1/21 19:03
 */
public class TwoSum3 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,2,3,4,4,5};
        twoSum(nums, 6);
    }


    public static void twoSum(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            if ((nums[low] + nums[high] == target) && !set.contains(nums[low])) {
                System.out.println("满足条件的下标为 " + (low + 1) + "," + (high + 1));
                low++;
                high--;
                set.add(nums[low]);
                set.add(nums[high]);
            } else if (nums[low] + nums[high] > target) {
                high--;
            } else {
                low++;
            }
        }
    }
}
