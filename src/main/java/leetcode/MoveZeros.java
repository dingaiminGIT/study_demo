package leetcode;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *  https://leetcode-cn.com/problems/3sum/
 * 示例
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class MoveZeros {

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeros(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void moveZeros(int[] nums) {
        // j 是记录非 0 元素的下标
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 把非 0 元素放到 j 对应的下标上
                nums[j] = nums[i];
                // i 和 j 下标不相等就把下标 i 对应的元素置为 0，因为 J 表示非 0 下标
                if (i != j) {
                    nums[i] = 0;
                }
                // 非 0 下标+1
                j++;
            }
        }
    }

    /**
     * 两次遍历法
     * 用两个指针 i 和 j，j 表示非 0 元素的下标
     *
     * @param nums
     */
    public static void moveZeros2(int[] nums) {
        // j 是记录非 0 元素的下标
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 把非 0 元素放到 j 对应的下标上
                nums[j] = nums[i];
                // i 和 j 下标不相等就把下标 i 对应的元素置为 0，因为 J 表示非 0 下标
                if (i != j) {
                    nums[i] = 0;
                }
                // 非 0 下标+1
                j++;
            }
        }
    }

}
