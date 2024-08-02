package leetcode.array;

/**
 * 合并两个有序数组
 * https://leetcode-cn.com/problems/merge-sorted-array/
 *
 * @author: dingaimin
 * @date: 2021/1/16 20:14
 */
public class MergeSortedArray {

    /**
     * 方法1：双指针|从前往后
     * 将 num1 的数组复制一份，然后分别对比 num1_copy 和 nums2 中的值，放到 nums1 中
     * 时间复杂度 : O(n + m)O(n+m)。
     * 空间复杂度 : O(m)O(m)。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] num1Copy = new int[m];
        System.arraycopy(nums1, 0, num1Copy, 0, m);
        int p1 = 0;
        int p2 = 0;
        int p = 0;
        while (p1 < m && p2 < n) {
            nums1[p++] = num1Copy[p1] < nums2[p2] ? num1Copy[p1++] : nums2[p2++];
        }
        // 还剩下元素，直接添加到 nums1 的后面
        if (p1 < m) {
            System.arraycopy(num1Copy, p1, nums1, p, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p, m + n - p1 - p2);
        }
    }

    /**
     * 方法2：双指针|从后往前
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        // 将nums2剩下的元素移动到nums1中
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
