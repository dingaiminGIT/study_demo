package leetcode.heap;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素 中等
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class FindKthLargest {

    public static int findKthLargest(int[] nums, int k) {
        // 用堆实现
        // 创建一个小顶堆，默认是小顶堆，如果创建大顶堆，PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minHead = new PriorityQueue<>();
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 如果堆未满，直接插入
            if (minHead.size() < k) {
                minHead.add(nums[i]);
            } else {
                // 如果堆已满，如果堆顶元素小于当前元素，删除堆顶元素，插入当前元素
                if (minHead.peek() < nums[i]) {// peek方法不移除堆顶元素
                    minHead.poll();// 移除
                    minHead.add(nums[i]);
                }
            }
        }
        return minHead.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int result = findKthLargest(nums, k);
        System.out.println("The " + k + "th largest element is: " + result); // Expected: 5
    }
}
