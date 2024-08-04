package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角 简单
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * https://leetcode.cn/problems/pascals-triangle/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class Triangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        if (numRows == 0) {
            return result;
        }

        // 第一行固定的 1
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        result.add(firstRow);

        // 从第二行开始生成
        for (int i = 1; i<numRows;i++) {
            List<Integer> preRow = result.get(i-1);
            List<Integer> currentRow = new ArrayList<>();

            // 每行的第一个元素是 1
            currentRow.add(1);

            // 中间的元素是前一行两个相邻元素的和
            for (int j = 1; j < i; j++) {
                currentRow.add(preRow.get(j-1) + preRow.get(j));
            }
            // 每行最后一个元素是 1
            currentRow.add(1);
            result.add(currentRow);
        }
        return result;
    }

    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> result = generate(numRows);
        for (List<Integer> row : result) {
            System.out.println(row);
        }
        // Expected output:
        // [1]
        // [1, 1]
        // [1, 2, 1]
        // [1, 3, 2, 1]
        // [1, 4, 6, 4, 1]
    }
}
