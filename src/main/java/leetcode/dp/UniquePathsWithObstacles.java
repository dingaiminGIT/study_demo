package leetcode.dp;

/**
 * 不同路径2 带障碍物
 * https://leetcode-cn.com/problems/unique-paths-ii/
 *
 * @author: dingaimin
 * @date: 2021/2/12 16:42
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        UniquePathsWithObstacles test = new UniquePathsWithObstacles();
        int[][] nums = {{1,0}};
        int res = test.uniquePathsWithObstacles(nums);
        System.out.println(res);
    }

    /**
     * 和不同路径1的区别在于多了障碍物，那么有障碍物的格子路径总数是0
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 行数
        int m = obstacleGrid.length;
        // 列数
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        // 初始化状态
        for(int i = 0; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : 1;
            // 第一列只要出现障碍物，下面的肯定是0
            if(obstacleGrid[i][0] == 1) {
                break;
            }
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : 1;
            // 第一行只要出现障碍物，后面的肯定是0
            if(obstacleGrid[0][j] == 1) {
                break;
            }
        }

        // 状态转移方程
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
