package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 * https://leetcode-cn.com/problems/permutations/
 *
 * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
 *
 * @author: dingaimin
 * @date: 2021/2/7 20:38
 */
public class Permute {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> t = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return res;
        }
        boolean[] used = new boolean[n];
        dfs(nums,0,used);
        return res;
    }

    public void dfs(int[] nums, int depth, boolean[] used) {
        if(depth == nums.length) {
            res.add(new ArrayList<>(t));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(used[i]) {
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
