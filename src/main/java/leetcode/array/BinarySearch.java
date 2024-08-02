package leetcode.array;

/**
 * 二分查找
 * https://leetcode-cn.com/problems/binary-search/
 *
 * @author: dingaimin
 * @date: 2021/1/11 22:12
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6};
        //System.out.println(search(nums, 6));
        System.out.println(search(nums, 0, nums.length - 1, 9));
    }

    public static int search(int[] nums, int left, int right, int target) {
        if (nums[left] > target || nums[right] < target) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            return search(nums, 0, mid - 1, target);
        } else {
            return search(nums, mid + 1, nums.length - 1, target);
        }
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
