package leetcode;

import java.util.Arrays;

/**
 * 下一个排列
 * 小红书考了
 * https://leetcode-cn.com/problems/next-permutation/
 *
 * @author: dingaimin
 * @date: 2021/2/4 18:42
 */
public class NextPermutation {

    public static void main(String[] args) {
        NextPermutation test = new NextPermutation();
        //int[] nums = new int[]{1,4,3,2,1};
        int[] nums = new int[]{4,3,2,1};
        test.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 1.将左边的较小数与一个右边的较大数交换，以能够让当前排列变大，从而得到一个排列
     * 2.同时我们要让这个较小数尽量靠右，而较大数尽可能小。当交换完后，较大数右边的数需要按照升序重新排列
     * 这样就可以保证新排列大于原来，且使变大的幅度尽可能小
     *
     * 具体地，我们这样描述该算法，对于长度为 n 的排列 a：
     * 1.首先从后向前查找第一个顺序对 (i,i+1)，满足 a[i] < a[i+1]。这样「较小数」即为 a[i]。此时 [i+1,n)必然是下降序列。
     * 2.如果找到了顺序对，那么在区间 [i+1,n) 中从后向前查找第一个元素 j 满足 a[i] < a[j]。这样「较大数」即为 a[j]。
     * 3.交换 a[i]与 a[j]，此时可以证明区间 [i+1,n) 必为降序。我们可以直接使用双指针反转区间 [i+1,n) 使其变为升序，而无需对该区间进行排序
     *
     * 如果在步骤 1 找不到顺序对，说明当前序列已经是一个降序序列，即最大的序列，我们直接跳过步骤 2 执行步骤 3，即可得到最小的升序序列
     *
     *
     *
     * 假设当前序列 a 的长度为 n
     * - 第一次从后往前遍历这个序列，找第一个**顺序对(i,i+1)**，使其满足**a[i]<a[i+1]**，这样**较小数**为a[i]，且[i+1,n) 一定是降序排列
     * - 如果找到了顺序对，进行第二次遍历，就从区间[i+1,n) 中从后往前找第一个元素 j 满足 a[i] < a[j]，这样**较大数**就是 a[j]
     * - 交换 a[i] 与 a[j]，注意交换后 [i+1,n) 依然是降序，因为交换前 [i+1,n)就是降序，交换前 a[j] > a[j+1] <= a[i]，所以交换后，a[j] 依然大于 a[j+1]
     * - 因为[i+1,n)是降序，直接使用**双指针**反转区间[i+1,n)，使其变为升序
     * - 如果在第一次遍历时没有找到顺序对（即 i 为-1），说明当前序列已经是降序序列了，那就直接执行反转区间[i+1,n)即可
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 找第一个顺序对(i,i+1)
        while (i >= 0 && nums[i] >= nums[i+1]) {
            i--;
        }
        System.out.println(i);
        // [i+1,n)下降序列
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            // 找到了 nums[j] > nums[i]
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
