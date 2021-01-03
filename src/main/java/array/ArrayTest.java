package array;

public class ArrayTest {

    public static void main(String[] args) {
        // 3 行 2 列的二位数据
        int[][] a = new int[][]{{1,2},{3,4},{5,6}};
        int row = a.length;
        System.out.println("行数：" + row);
        int c = a[0].length;
        System.out.println("列数：" + c);
        System.out.println((Math.min(row, c) + 1) / 2);

        int[] d = new int[5];
        System.out.println(d.length);
    }
}
