package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 54 题 旋转矩阵
 * https://leetcode-cn.com/problems/spiral-matrix/
 *
 */
public class spiralOrder {

    public static void main(String[] args) {
        // 3 行 2 列的二位数据
//        int[][] a = new int[][]{{1,2},{3,4},{5,6}};
        int[][] a = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

        List<Integer> list = spiralOrder3(a);
        list.stream().forEach((item)-> System.out.println(item));

    }

    /**
     * 方法 1 ：模拟整个遍历过程
     * 时间复杂度 O(N) 需要遍历二位数组中的所有元素
     * 空间复杂度 O(N) 需要用到两个数组 seen 和 result
     *
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList();
        }
        int row = matrix.length;
        int column = matrix[0].length;

        List result = new ArrayList(row * column);

        // 方向数组
        // 行方向 0：右,1：下,0：左，-1：上
        int[] dr = {0,1,0,-1};
        // 列方向 1: 右，0：下，-1：左，0：上
        int[] dc = {1,0,-1,0};
        int r = 0, c = 0, di = 0;

        // 标记第 r 行 c 列的单元格被访问过了
        boolean[][] seen = new boolean[row][column];

        // 遍历整个二维数组即矩阵
        for (int i = 0; i < row * column; i++) {
            // 将单元格对应的元素放到结果集中
            result.add(matrix[r][c]);
            // 标记 r 行 c 列的单元格被访问过了，这个判断主要用在要换圈的时候，因为如果没有这个限制，它就不会缩小圈
            seen[r][c] = true;
            // 下一步移动的候选位置
            int nr = r + dr[di];
            int nc = c + dc[di];

            // 做边界与是否已经访问过的判断
            if (nr >= 0 && nr < row && nc >= 0 && nc < column && !seen[nr][nc]) {
                r = nr;
                c = nc;
            } else {
                // 说明到了边界了，需要换方向了
                di = (di + 1) % 4;
                r = r + dr[di];
                c = c + dc[di];
            }
        }
        return result;
    }

    /**
     * 方法 2 ：按层遍历
     *
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder2(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList();
        }
        // rowMin 表示当前层行的最小下标 rowMax 表示当前层行的最大下标
        int rowMin = 0, rowMax = matrix.length - 1;
        // columnMin 表示当前层列的最小下标 columnMax 表示当前层列的最大下标
        int columnMin = 0, columnMax = matrix[0].length - 1;
        // (rowMin,columnMin) 代表当前层的左上角的坐标  (rowMax,columnMax) 表示当前层右下角的坐标
        List result = new ArrayList(matrix.length * matrix[0].length);

        while (rowMin <= rowMax && columnMin <= columnMax) {
            // 遍历当前层的上面边的所有元素 行坐标不变，列坐标 column 从 columnMin 到 columnMax
            for (int column = columnMin; column <= columnMax; column++) {
                result.add(matrix[rowMin][column]);
            }
            // 遍历当前层的右面边的所有元素 列坐标不变，行坐标 row 从 rowMin + 1 到 rowMax
            for (int row = rowMin + 1; row <= rowMax; row++) {
                result.add(matrix[row][columnMax]);
            }
            // 如果当前层有 4 条边即满足条件 rowMin < rowMax && columnMin < columnMax，还要遍历下面边和左面边的所有元素
            if (rowMin < rowMax && columnMin < columnMax) {
                // 遍历当前层的下面边的所有元素 行坐标不变，列坐标从 columnMax -1 到 columnMin + 1
                for (int column = columnMax - 1; column > columnMin; column--) {
                    result.add(matrix[rowMax][column]);
                }
                // 遍历当前层左面边的所有元素 列坐标不变，行坐标从 rowMax 遍历到 rowMin + 1
                for (int row = rowMax; row > rowMin; row--) {
                    result.add(matrix[row][columnMin]);
                }
            }
            // 遍历一层后，遍历下一层，需要更新 rowMin、rowMax、columnMin、columnMax 的坐标
            rowMin++;
            rowMax--;
            columnMin++;
            columnMax--;
        }
        return result;
    }

    public static List<Integer> spiralOrder3(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList();
        }
        // rowMin 表示当前层行的最小下标 rowMax 表示当前层行的最大下标
        int rowMin = 0, rowMax = matrix.length - 1;
        // columnMin 表示当前层列的最小下标 columnMax 表示当前层列的最大下标
        int columnMin = 0, columnMax = matrix[0].length - 1;
        // (rowMin,columnMin) 代表当前层的左上角的坐标  (rowMax,columnMax) 表示当前层右下角的坐标
        List result = new ArrayList(matrix.length * matrix[0].length);

        while (rowMin <= rowMax && columnMin <= columnMax) {
            // 遍历当前层的上面边的所有元素 行坐标不变，列坐标 column 从 columnMin 到 columnMax
            for (int column = columnMin; column <= columnMax; column++) {
                result.add(matrix[rowMin][column]);
            }
            // 遍历当前层的右面边的所有元素 列坐标不变，行坐标 row 从 rowMin + 1 到 rowMax
            for (int row = rowMin + 1; row <= rowMax; row++) {
                result.add(matrix[row][columnMax]);
            }
            // 如果当前层有 4 条边即满足条件 rowMin < rowMax && columnMin < columnMax，还要遍历下面边和左面边的所有元素
            if (rowMin < rowMax && columnMin < columnMax) {
                // 遍历当前层的下面边的所有元素 行坐标不变，列坐标从 columnMax -1 到 columnMin
                for (int column = columnMax - 1; column >= columnMin; column--) {
                    result.add(matrix[rowMax][column]);
                }
                // 遍历当前层左面边的所有元素 列坐标不变，行坐标从 rowMax -1  遍历到 rowMin + 1
                for (int row = rowMax - 1; row > rowMin; row--) {
                    result.add(matrix[row][columnMin]);
                }
            }
            // 遍历一层后，遍历下一层，需要更新 rowMin、rowMax、columnMin、columnMax 的坐标
            rowMin++;
            rowMax--;
            columnMin++;
            columnMax--;
        }
        return result;
    }
}

