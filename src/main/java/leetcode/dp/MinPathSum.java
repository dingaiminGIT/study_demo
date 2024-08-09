package leetcode.dp;

/**
 * 64. 最小路径和
 * 快手商业化架构团队 1 面改版了，改成了最大路径和
 * https://leetcode.cn/problems/minimum-path-sum/description/
 */
public class MinPathSum {

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        /*定义状态 dp[i][j] 表示到达 grid[i][j] 的最小路径和
        只能向右或向下走，换句话说，当前单元格 (i,j) 只能从左方单元格 (i−1,j) 或上方单元格 (i,j−1) 走到，
        因此只需要考虑矩阵左边界和上边界。
        走到当前单元格 (i,j) 的最小路径和 = “从左方单元格 (i−1,j) 与 从上方单元格 (i,j−1) 走来的
        两个最小路径和中较小的 ” + 当前单元格值 grid[i][j] 。具体分为以下 4 种情况：
        当左边和上边都不是矩阵边界时： 即当i!=0, j!=0时，dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j] ；
        当只有左边是矩阵边界时： 只能从上面来，即当i=0,j!=0时， dp[i][j]=dp[i][j−1]+grid[i][j] ；
        当只有上边是矩阵边界时： 只能从左面来，即当i!=0,j=0时， dp[i][j]=dp[i−1][j]+grid[i][j] ；
        当左边和上边都是矩阵边界时： 即当i=0,j=0时，其实就是起点， dp[i][j]=grid[i][j]；*/

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                } else if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}
