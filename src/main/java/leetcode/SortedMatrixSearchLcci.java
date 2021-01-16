package leetcode;

/**
 * 排序矩阵查找
 * https://leetcode-cn.com/problems/sorted-matrix-search-lcci/
 * 腾讯微视一面
 *
 * @author: dingaimin
 * @date: 2021/1/13 21:50
 */
public class SortedMatrixSearchLcci {

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,3}, {5,6,7,8}, {9,10,11,12}};
        boolean b = searchMatrix(matrix, 8);
        System.out.println(b);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        // 从右上角切入
        // 如果等于 target，返回 true
        // 如果小于 target，往下走
        // 如果大于 target, 往左走
        if(matrix.length <= 0) {
            return false;
        }
        // 行-宽
        int width = matrix.length;
        // 列-高
        int hight = matrix[0].length;
        int row = 0;
        int col = hight - 1;
        while (row < width && col >= 0) {
            int base = matrix[row][col];
            if(base == target) {
                return true;
            } else if(base < target) {
                // 往下移动
                row++;
            } else {
                // 往左移动
                col--;
            }
        }
        return false;
    }
}
