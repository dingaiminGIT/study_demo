package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * @author: dingaimin
 * @date: 2021/1/23 23:18
 */
public class CombinationSum {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //dfs(candidates, target, 0);
        // 方法2：先对数组排序
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return res;
    }

    public void dfs(int[] candidates, int target, int index) {
        // 递归终止条件 target <= 0
        if (target < 0) {
            return;
        }
        // 记录结果
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 搜索
        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            // 递归
            // 因为元素可以重复，所以下标从i开始
            dfs(candidates, target - candidates[i], i);
            // 遍历完没有满足条件，回溯，并移除当前元素
            path.remove(path.size() - 1);
        }
    }

    /**
     * 对数组做升序排序，如果target小于0时，不用往后遍历了，因为后面的元素更大，target 依然会小于0，做剪枝
     *
     * @param candidates
     * @param target
     * @param index
     */
    public void dfs2(int[] candidates, int target, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            // 做剪枝
            if (target - candidates[i] < 0) {
                break;
            }
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], i);
            path.remove(path.size() - 1);
        }
    }
}
