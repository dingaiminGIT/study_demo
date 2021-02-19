package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列2
 * https://leetcode-cn.com/problems/permutations-ii/
 *
 * @author: dingaimin
 * @date: 2021/2/7 21:02
 */
public class PermuteUnique {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> t = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return res;
        }
        boolean[] used = new boolean[n];
        Arrays.sort(nums);
        dfs(nums,0,used);
        return res;
    }

    public void dfs(int[] nums, int depth, boolean[] used) {
        if(depth == nums.length) {
            res.add(new ArrayList<>(t));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            // 保证对于重复数的集合，一定是从左往右逐个填入的
            if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i-1])) {
                continue;
            }
            t.add(nums[i]);
            used[i] = true;
            dfs(nums, depth+1,used);
            // 回溯
            used[i] = false;
            t.remove(t.size()-1);
        }
    }
}
