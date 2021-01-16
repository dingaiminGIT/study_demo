package tencent;

/**
 * 腾讯微视一面
 * 一个行列有序的矩阵，写一个算法，判断k是否在矩阵中
 *  思想，从右上角作为切入点
 * 1 2  3   4
 * 5 6  7   8
 * 9 10 11 12
 * @author: dingaimin
 * @date: 2021/1/13 20:35
 */
public class Test {

    public static void main(String[] args) {
        int[][] nums = {{1,2,3,3}, {5,6,7,8}, {9,10,11,12}};
        boolean b = iscontainsKey(nums, 90);
        System.out.println(b);
    }

    public static boolean iscontainsKey(int[][] nums, int target) {
        // 行数
        int n = nums.length;
        // 列数
        int m = nums[0].length;

        int row = 0;
        int col = m - 1;
        while (row < n && col >= 0) {
            int base = nums[row][col];
            if (base == target) {
                return true;
            } else if (base < target) {
                // 往下走
                row++;
            } else {
                // 往左走
                col--;
            }
        }
        return false;
    }

}
