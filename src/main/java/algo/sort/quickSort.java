package algo.sort;

import java.util.Arrays;

public class quickSort {

    public static void main(String[] args) {
        int[] a = {8,7,10,1,5,6,3,15,2,4};
        System.out.println("排序前 " + Arrays.toString(a));
        quickSort(a, 0, a.length - 1);
        System.out.println("排序后 " + Arrays.toString(a));

    }

    /**
     * 快速排序的递归函数 left,right 为下标
     * 时间复杂度 O(nlogn)
     * 最坏时间复杂度 O(n^2)
     *
     * @param a
     * @param left
     * @param right
     */
    private static void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        // 获取分区点(下标)，分区点左边的数小于分区点对应的数，分区点右边的数大于分区点对应的数
        int q = partition(a, left, right);
        System.out.println("q="+q);
        // 递归对分区点左边的进行排序
        quickSort(a, left, q - 1);
        // 递归对分区点右边的进行排序
        quickSort(a, q + 1, right);
    }

    /**
     * 分区函数：获取分区点，取right
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] a, int left, int right) {
        // 这里取right位置处
        int pivot = a[right];
        System.out.println("pivot " + pivot);
        // 通过下标 i 把a[left...right-1]分成两部分，其中a[left...i-1]都是小于 pivot 的，代表已经处理过了
        // 而 a[i...right-1]的元素都是没有处理的，然后从未处理的部分取一个和pivot比较，如果小，就放到已经处理的部分
        int i = left;
        for (int j = left; j < right; j++) {
            if (a[j] < pivot) {
                // 如果 i==j，就不用交换了
                if (i == j) {
                    i++;
                } else {
                    swap(a, i, j);
                    i++;
                }
            }
        }
        // 交换i与right对应的元素
        swap(a, i, right);
        System.out.println(Arrays.toString(a));
        return i;
    }

    /**
     * 交换数组下标对应的元素
     *
     * @param a
     * @param i
     * @param j
     */
    private static void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i]=temp;
    }

    /**
     * 分区函数：获取分区点，取中间
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    private static int partition2(int[] a, int left, int right) {
        // 这里取中间位置处
        int pivot = a[left + (right-left)/2];
        System.out.println("pivot " + pivot);
        // 通过下标i把a[left]-a[right]分成两部分，其中a[left]-a[i-1]都是小于 pivot 的，代表已经处理过了
        // 而 a[i]-a[right-1]的元素都是没有处理的，然后从未处理的部分取一个和pivot比较，如果小，就放到已经处理的部分
        int i = left;
        for (int j = left; j < right; j++) {
            if (a[j] < pivot) {
                // 如果 i==j，就不用交换了
                if (i == j) {
                    i++;
                } else {
                    swap(a, i, j);
                    i++;
                }
            }
        }
        // 交换i与right对应的元素
        swap(a, i, right);
        System.out.println(Arrays.toString(a));
        return i;
    }


}
