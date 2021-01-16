package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 最小k个数 - 用大顶堆
 * https://leetcode-cn.com/problems/smallest-k-lcci/
 *
 * @author: dingaimin
 * @date: 2021/1/11 19:26
 */
public class SmallTopK {

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,7,2,4,6,8};
        int[] ints = smallestK(arr, 4);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
            Arrays.asList(ints);
        }
    }

    public static int[] smallestK(int[] arr, int k) {
        if(k == 0){
            return new int[]{};
        }
        // 大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int[] result = new int[k];
        for(int i = 0; i < k; i++){
            maxHeap.offer(arr[i]);
        }
        for(int i = k; i < arr.length; i++){
            if(maxHeap.peek() > arr[i]){
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }

        for(int i = 0; i < k; i++){
            result[i] = maxHeap.poll();
        }

        return result;
    }

}
