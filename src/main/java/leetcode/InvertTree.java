package leetcode;

/**
 * 翻转二叉树
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 *
 * @author: dingaimin
 * @date: 2021/1/22 21:51
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
