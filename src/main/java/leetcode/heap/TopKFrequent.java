package leetcode.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素 难度：中等
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * https://leetcode.cn/problems/top-k-frequent-elements/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class TopKFrequent {

    /**
     * 使用哈希表和最小堆来实现
     * 统计频率：使用哈希表统计每个元素的出现频率。
     * 维护前 k 高频元素：使用一个大小为 k 的最小堆来维护频率最高的 k 个元素。
     * 返回结果：从最小堆中提取结果。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
        for (int key : map.keySet()) {
            minHeap.offer(key);
            // 如果堆的大小超过 k，则移除堆顶元素（频率最小的元素）
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = topKFrequent(nums, k);
        System.out.println("Top " + k + " frequent elements: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
        // Expected output: [1, 2] (order may vary)
    }
}
