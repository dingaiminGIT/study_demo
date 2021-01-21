package leetcode;

/**
 * 两数之和2-输入有序数组
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * @author: dingaimin
 * @date: 2021/1/21 18:59
 */
public class TwoSum2 {

    public int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        while(low < high) {
            if(numbers[low] + numbers[high] == target) {
                return new int[]{low+1, high+1};
            } else if(numbers[low] + numbers[high] > target) {
                high--;
            } else {
                low++;
            }
        }
        return new int[]{-1, -1};
    }
}
