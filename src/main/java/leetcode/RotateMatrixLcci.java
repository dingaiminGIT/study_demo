package leetcode;

/**
 * 旋转数组
 * https://leetcode-cn.com/problems/rotate-matrix-lcci/
 */
public class RotateMatrixLcci {

    public static void main(String[] args) {
        int[][] a = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

        System.out.println("旋转前");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println("\n");
        }

        rotate4(a);
        System.out.println("旋转后");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return;
        }

        // 新建一个与原来数组一样大小的数组
        int[][] newMatrix = new int[n][matrix[0].length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[j][n - i - 1] =  matrix[i][j];
            }
        }

        System.arraycopy(newMatrix, 0, matrix, 0, newMatrix.length);
    }

    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return;
        }

        for (int row = 0; row < n / 2; row++) {
            for (int col = 0; col < (n + 1) / 2; col++) {
                // 把 4 个位置的元素互换，注意顺序
                int temp = matrix[n - col -1][row];
                matrix[n - col -1][row] = matrix[n - row -1][n - col -1];
                matrix[n - row -1][n - col -1] = matrix[col][n-row-1];
                matrix[col][n-row-1] = matrix[row][col];
                matrix[row][col] = temp;
            }
        }
    }

    public static void rotate3(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return;
        }

        // 以对角线进行翻转
        for (int row = 0; row < n; row++) {
            for (int col = row + 1; col < n; col++) {
                int temp = matrix[col][row];
                matrix[col][row] = matrix[row][col];
                matrix[row][col] = temp;
            }
        }
        
        // 对每一行以中点进行翻转
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n / 2; col++) {
                int temp = matrix[row][n - col - 1];
                matrix[row][n - col - 1] = matrix[row][col];
                matrix[row][col] = temp;
            }
        }
    }

    public static void rotate4(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return;
        }


        for (int row = 0; row < n; row++) {
            // 以对角线进行翻转
            for (int col = row + 1; col < n; col++) {
                int temp = matrix[col][row];
                matrix[col][row] = matrix[row][col];
                matrix[row][col] = temp;
            }

            // 对每一行以中点进行翻转
            for (int col = 0; col < n / 2; col++) {
                int temp = matrix[row][n - col - 1];
                matrix[row][n - col - 1] = matrix[row][col];
                matrix[row][col] = temp;
            }
        }
    }
}

