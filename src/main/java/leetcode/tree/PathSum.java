package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 *
 * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 *
 * @author: dingaimin
 * @date: 2021/1/23 21:45
 */
public class PathSum {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum);
        return res;
    }

    /**
     * 先序遍历 + 递归 + 回溯
     *
     *     递归参数：当前节点root和当前的目标值tar
     *
     *     递归终止条件：节点root为空，直接返回
     *
     *     递归的内容
     *     路径更新：将当前节点root.val的值加入到路径path中
     *     更新目标值tar：tar = tar - root.val
     *     记录路径：1.root为叶子节点；2.路径节点的值想加为sum值即tar为0
     *     先序遍历：递归左右子树
     *     路径恢复：当该节点的左右子树都遍历完，却不满足条件，就要向上回溯了，回溯前，要把当前节点从path中移除，比如示例中的7这个节点，需要被移除掉
     *
     * @param root
     * @param tar
     */
    public void dfs(TreeNode root, int tar) {
        if(root == null) {
            return;
        }
        // 将当前节点的值加入到路径path中
        path.add(root.val);
        // 更新目标值
        tar = tar - root.val;
        // 满足条件的路径的记录
        // ①root为叶子节点
        // ②路径和等于目标值
        if(tar == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList(path));
        }
        // 递归左右子树
        dfs(root.left, tar);
        dfs(root.right, tar);
        // 左右子树遍历完，不符合条件，要向上回溯，回溯前将当前节点删除掉
        path.remove(path.size() - 1);
    }
}
