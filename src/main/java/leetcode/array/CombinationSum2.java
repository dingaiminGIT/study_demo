package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 加起来和为目标的组合
 * https://www.nowcoder.com/practice/75e6cd5b85ab41c6a7c43359a74e869a?tpId=190&&tqId=35351&rp=1&ru=/activity/oj&qru=/ta/job-code-high-rd/question-ranking
 * 给出一组候选数C 和一个目标数T，找出候选数中起来和等于 T 的所有组合。C 中的每个数字在一个组合中只能使用一次
 * 要求
 * 结果中不能包含重复组合
 * 数组中的每个元素只能用一次
 * 组合之间的排序按照索引从小到大依次比较，小的排在前面，如果索引相同的情况下数值相同，则比较下一个索引
 *
 * @author: dingaimin
 * @date: 2021/1/24 9:45
 */
public class CombinationSum2 {

    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        dfs(num, target, 0);
        return res;
    }

    public void dfs(int[] num, int target, int index) {
        if(target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = index; i < num.length; i++) {
            // 去重
            if(i > index && num[i] == num[i-1]) {
                continue;
            }
            if(target - num[i] < 0) {
                // 剪枝
                break;
            }
            path.add(num[i]);
            // 递归，因为题目要求每个数字只能用一次，所以下标要＋1
            dfs(num, target - num[i], i + 1);
            // 回溯
            path.remove(path.size() - 1);
        }
    }
}
