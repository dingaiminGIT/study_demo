package leetcode.tree;

import java.util.List;

/**
 * 二叉树的遍历
 *
 * 二叉树的中序遍历  左 -> 根 -> 右
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * 前序遍历  根->左->右
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * 后续遍历 左-> 右 -> 根
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * @author: dingaimin
 * @date: 2021/1/13 22:04
 */
public class BinaryTreeOrderTraversal {

    public static void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }

    public static void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    public static void midorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        midorder(root.left, res);
        res.add(root.val);
        midorder(root.right, res);
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}


