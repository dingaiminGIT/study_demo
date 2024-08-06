package leetcode.array;

/**
 * 42 接雨水  困难
 * https://leetcode.cn/problems/trapping-rain-water/
 */
public class Trap {

    public static void main(String[] args) {
        int[] height = {5, 5, 1, 7, 1, 1, 5, 2, 7, 6};
        System.out.println(trap(height)); // 输出 23
    }

    /**
     * 由于我们是逐个位置计算雨水量的，所以不需要考虑长度的问题。
     * 每个位置上的雨水量是独立计算的，最终的总雨水量是所有位置上雨水量的累加。
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        // 两个指针
        int left = 0;
        int right = height.length - 1;
        // 左边最大高度
        int leftMax = 0;
        // 右边最大高度
        int rightMax = 0;
        int totalWarter = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    // 计算的是「每个位置」上可以接的雨水量
                    // 对于每个位置 i，可以接的雨水量是由该位置的左右两边的最高柱子决定的。
                    // 具体来说，当前位置 i 上可以接的雨水量等于 min(leftMax, rightMax) - height[i]，
                    // 其中 leftMax 是位置 i 左边的最高柱子高度，rightMax 是位置 i 右边的最高柱子高度。
                    // 计算当前位置可以接的雨水量，并加到总雨水量中
                    totalWarter += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    totalWarter += rightMax - height[right];
                }
                right--;
            }
        }
        return totalWarter;
    }
}
