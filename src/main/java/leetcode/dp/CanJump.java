package leetcode.dp;

/**
 * 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 *
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 *
 * @author: dingaimin
 * @date: 2021/2/12 19:33
 */
public class CanJump {

    /**
     * 初始化状态：索引为0的位置，肯定能达到，所以是 true，其他位置可以根据状态转移方程计算得来
     * 状态参数：只有数组位置i是变化的，所以状态参数激素是当前的位置i
     * 备忘录：dp[i] 表示能否从出发点达到位置i
     * 状态决策：想知道能否到达位置i,需要逐个看前面的位置，判断能否从位置 i-1,i-2...0跳到位置i上。然后再看 i-1这个位置能否到达
     * 状态转移方程：dp[i] = true ，i=0
     * dp[i] = dp[j]=true && (max(a[j] + j >= i)),i不等于0 && j < i
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return true;
        }

        boolean[] dp = new boolean[n];
        // 初始化状态 0 位置为true，其他位置默认是flase
        dp[0] = true;

        for (int i = 1; i < n; i++) {
            // j < i
            for (int j = 0; j < i; j++) {
                if(dp[j] && j + nums[j] >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n-1];
    }
}
