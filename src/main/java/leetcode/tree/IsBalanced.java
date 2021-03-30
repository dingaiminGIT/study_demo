package leetcode.tree;

/**
 * 平衡二叉树
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 * @author: dingaimin
 * @date: 2021/2/22 20:56
 */
public class IsBalanced {

    public boolean isBalanced(TreeNode root) {
        int maxDepth = maxDepth(root);
        return maxDepth != -1;

    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        // 剪枝
        if (left == -1) {
            return -1;
        }
        int right = maxDepth(root.right);
        // 剪枝
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) < 2 ?Math.max(left,right) + 1 : -1;
    }
}
