package leetcode;

/**
 * 找到数组中第 k 大的数
 * https://www.nowcoder.com/practice/e016ad9b7f0b45048c58a9f27ba618bf?tpId=117&&tqId=35010&&companyId=898&rp=1&ru=/company/home/code/898&qru=/ta/job-code-high/question-ranking
 *
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
 * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
 *
 * 输入
 * [1,3,5,2,2],5,3
 *
 * 输出
 * 2
 *
 * 快速排序：挖坑填数 + 分治
 *
 * @author: dingaimin
 * @date: 2021/1/16 16:54
 */
public class ArrayBigTopK {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,2,2};
        int topK = findTopK(nums, 0, 4, 3);
        System.out.println(topK);
    }

    /**
     * 用快速排序排成降序
     *
     * @param nums
     * @param left
     * @param right
     * @param k
     * @return
     */
    public static int findTopK(int[] nums, int left, int right , int k) {
        if (left <= right) {
            int partition = partition(nums, left, right);
            if (partition == k - 1) {
                // 如果分区点位等于 k-1，说明第k大的值就是 partition 这个位置
                return nums[partition];
            } else if (partition < k - 1) {
                // 往右边找
                return findTopK(nums, partition + 1, right, k);
            } else {
                // 往左边找
                return findTopK(nums, left, partition - 1, k);
            }
        }
        return -1;
    }

    public static int partition(int[] nums, int left, int right) {
        // 取第一个为基准数
        int base = nums[left];
        // 从数组两向中间移动
        while (left < right) {
            // 从右边找比基准数大的数
            while (left < right && nums[right] <= base) {
                right--;
            }
            // 从右边找到了比基准值大的数，所以将该值填到第一个坑位即left坑位
            nums[left] = nums[right];

            // 从左边找比基准值小的数
            while (left < right && nums[left] >= base) {
                left++;
            }
            // 从左边找到了比基准值小的数，填上面那个坑位即 right 坑位
            nums[right] = nums[left];

        }
        // 把基准数填到最新的坑位上即 left 坑位
        nums[left] = base;
        return left;
    }
}
