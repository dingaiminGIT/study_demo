package leetcode.array;

/**
 * 盛水最多的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * @author: dingaimin
 * @date: 2021/1/29 16:46
 */
public class MaxWater {

    /**
     * 双指针
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, area);
            if(height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
