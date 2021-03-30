package leetcode.array;

import java.util.*;

/**
 * 只出现一次的数字
 * https://leetcode-cn.com/problems/single-number/
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 *
 * @author: dingaimin
 * @date: 2021/2/17 21:09
 */
public class SingleNumber {

    public static void main(String[] args) {
        SingleNumber test = new SingleNumber();
        int[] nums = new int[]{4,1,2,1,2};
        int i = test.singleNumber2(nums);
        System.out.println(i);
    }

    /**
     * 利用位运算
     * 异或运算的三个特性
     * 1.任何数和 0 做异或运算，结果仍然是原来的数，即 a^0 = a
     * 2.任何数和其自身做异或运算，结果是 0，a ^ a = 0
     * 3.异或运算满足交换律和结合律,a^b^a = b^a^a=b^(a^a)= b^0=b
     *
     * 因为数组中只有一个数字出现了1次，其他数字都是出现了2次，那么我们可以假设该数组中有 2m+1个数，其中 m 个数出现了 2 次
     * 一个数出现了 1 次，假设 a1、a2、...、am 是出现2次的数，am+1 是出现1次的数，根据上面的三个特性
     * a1^a1^a2^a2^...^am^am^am+1 = (a1^a1)^(a2^a2)^...^(am^am)^am+1 = 0^0^...^am+1 = am+1
     * 所以数组中全部元素做异或运算的结果即为数组中只出现1次的数字
     *
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums) {
        int res = 0;
        for(int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        System.out.println(set.size());
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            return it.next();
        }
        return 0;
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], 2);
            } else {
                map.put(nums[i], 1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return 0;
    }
}
