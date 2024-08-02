package leetcode.array;

/**
 * 二分查找变体
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 二分查找的四个变体
 * https://blog.csdn.net/qq_42815754/article/details/89057370
 *
 * @author: dingaimin
 * @date: 2021/1/12 22:08
 */
public class BinarySearch2 {

    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;
        int m = searchFist(nums, target);
        int n = searchLast(nums, target);
        int[] ret = new int[]{m, n};
        for (int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
        }
    }

    public static int searchFist(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                // 走到这里，说明基准值与目标值相等，需要判断 middle 是否已经是数组的第一位了或者middle前一位是否等于 target
                if (middle == 0 || nums[middle - 1] != target) {
                    return middle;
                }
                // 往左遍历
                right = middle - 1;
            }
        }
        return -1;
    }

    public static int searchLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] < target) {
                // 大于中间值，缩小范围到中间值的右边
                left = middle + 1;
            } else if (nums[middle] > target) {
                // 小于中间值，缩小范围到中间值的左边
                right = middle - 1;
            } else {
                // 走到这里，说明与中间值相等，需要判断 middle 是否已经是数组中最后一个元素了，或者 middle 下标对应的值是否等于 target
                if (middle == nums.length - 1 || nums[middle + 1] != target) {
                    return middle;
                }
                // 往右遍历
                right = middle + 1;
            }
        }
        return -1;
    }
}
