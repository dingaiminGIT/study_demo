package leetcode.num;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        lists.stream().forEach(System.out::println);
    }

    /**
     * 双指针夹逼
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        if(nums == null || length < 3) {
            return Collections.emptyList();
        }
        // 双指针方法的前提是先对数组排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList();
        for(int i = 0; i < nums.length - 2; i++) {
            // 如果 nums[i] > 0,直接退出，因为nums有序，第一个就大于0了，后面的一定大于0，相加肯定大于0
            if(nums[i] > 0) {
                break;
            }
            // 已经将nums[i-1]所以组合加入到了结果中，所以直接跳过nums[i],否则只会得到重复结果
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            // 高低指针
            int lo = i + 1;
            int hi = nums.length - 1;
            while(lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum < 0) {
                    // 和小于 0 ，移动低位指针，并跳过重复值
                    while(lo < hi && nums[lo] == nums[++lo]);
                } else if (sum > 0) {
                    // 和大于 0，移动高位指针，并跳过重复值
                    while(lo < hi && nums[hi] == nums[--hi]);
                } else if (sum == 0) {
                    // 找到组合，移动高低指针，并跳过重复值
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo] == nums[++lo]);
                    while (lo < hi && nums[hi] == nums[--hi]);
                }
            }
        }
        return res;
    }

    /**
     * 暴力解法，没有去重
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        int length = nums.length;
        if(nums == null || length < 3) {
            return Collections.emptyList();
        }

        List<List<Integer>> list = new ArrayList();
        for(int i = 0;i < length - 2; i++) {
            for(int j = i+1;j < length - 1; j++) {
                for(int k = j+1; k < length; k++) {
                    if(nums[i] + nums[j] + nums[k] == 0) {
                        list.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    }
                }
            }
        }
        return list;
    }




}
