package job;

/**
 * @author: dingaimin
 * @date: 2021/1/12 21:18
 */
public class Test {

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 如果数组中不存在目标值，返回 [-1, -1]。
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     */

    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 5;

        int[] result = new int[2];
        int ret = search2(nums, target);
        if (ret == -1) {
            // 数组中没有目标值
            result[0] = -1;
            result[1] = -1;
        } else {
            // 数组中存在目标值，将目标数组倒序，然后再查询
            int[] nums2 = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                nums2[nums.length - i - 1] = nums[i];
            }
            int ret2 = search2(nums2, target);
            if (ret2 == ret) {
                // 说明目标数组中只存在一个target
                result[0] = ret;
                result[1] = -1;
            } else {
                // 说明存在多个
                result[0] = ret;
                result[1] = ret2;
            }
        }

        System.out.println("------");
        // 输出结果
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    /*public static int[] search(int[] nums, int target) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nums.length; i++) {
            sb.append(Integer.toString(nums[i]));
        }
        String s = sb.toString();
        System.out.println(s);
        String tar = Integer.toString(target);
        int i = s.indexOf(s.indexOf(tar.charAt(0)));
        if (i == -1) {
            return new int[]{-1,-1};
        }
        int end = s.lastIndexOf(tar.charAt(0));
        if (i == end) {
            return new int[]{i, -1};
        } else {
            return new int[]{i, end};
        }
    }*/

    /**
     * 只适用于升序数组
     * @param nums
     * @param target
     * @return
     */
    public static int search2(int[] nums, int target) {
        int temp;
        int left = 0;
        int right = nums.length - 1;
        // 循环执行条件
        while (left < right) {
            // 基准节点
            temp = left + (right - left) / 2;
            if (left < right && nums[temp] == target) {
                return temp;
            } else if (left < right && nums[temp] < target) {
                // 基准节点小于目标值，往右边遍历
                left = temp + 1;
            } else {
               right = temp - 1;
            }
        }
        return -1;
    }
}
