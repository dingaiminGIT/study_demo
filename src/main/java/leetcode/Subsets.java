package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 * https://leetcode-cn.com/problems/subsets/
 *
 * @author: dingaimin
 * @date: 2021/2/7 16:27
 */
public class Subsets {
    List<Integer> t = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return res;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            res.add(new ArrayList<>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }

    public static void main(String[] args) {
        Subsets test = new Subsets();
        int[] nums = new int[]{1,2,3,4,5};
        List<List<Integer>> res = test.subsets(nums);
        for (int i = 0; i < res.size(); i++) {
            List<Integer> r = res.get(i);
            if (r.size() != 2) {
                continue;
            }
            for (int j = 0; j < r.size(); j++) {
                System.out.print(r.get(j));
            }
            System.out.println();
        }
    }
}
