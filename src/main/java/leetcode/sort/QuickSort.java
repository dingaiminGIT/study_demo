package leetcode.sort;

/**
 * 快速排序是一个递归的思想
 * 方法：挖坑填数 + 分治法
 * https://blog.csdn.net/MoreWindows/article/details/6684558?utm_source=app
 *
 * @author: dingaimin
 * @date: 2021/1/11 14:10
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = new int[]{5,4,6,9,1};
        sort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static int partition(int a[], int low, int high) {
        // 取第一个值作为基准值
        int key = a[low];
        // 从数组两端向中间移动
        while (low < high) {
            while (low < high && a[high] >= key) {
                // 从后往前找比基准值小的数
                high--;
            }
            // 把比基准值小的数移动到低端
            a[low] = a[high];
            while (low < high && a[low] <= key) {
                // 从前往后找比基准值大的数
                low++;
            }
            // 把比基准值大的数移动到高端
            a[high] = a[low];
        }
        a[low] = key;
        return low;
    }

    public static void sort(int a[], int low, int high) {
        if (low >= high) {
            return;
        }
        int p = partition(a, low, high);
        sort(a, low, p - 1);
        sort(a, p + 1, high);
    }
}
