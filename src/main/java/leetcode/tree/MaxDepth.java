package leetcode.tree;

import apple.laf.JRSUIUtils.Tree;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * @author: dingaimin
 * @date: 2021/2/22 21:02
 */
public class MaxDepth {

    /**
     * DFS
     * 时间复杂度 ：O(n)，n 为二叉树节点的个数，每个节点在递归总只被遍历一次
     * 空间复杂度：O(height)，height 为二叉树的高度，递归函数需要栈空间，栈空间取决于递归的深度
     * 0ms 100.00%
     * 38.4 MB 61.36%
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * BFS
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            // 将队列中所有节点都拿出来扩展
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    /**
     * 用栈实现
     * 3ms 击败 9.76%
     * 41.57MB，击败63.45%
     *
     * @param root
     * @return
     */
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();

        stack.add(root);
        depths.add(1);
        int max = 1;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            int depth = depths.pop();
            if (cur.left == null && cur.right == null) {
                max = Math.max(max, depth);
            }
            if (cur.left != null) {
                stack.add(cur.left);
                depths.add(depth+1);
            }
            if (cur.right != null) {
                stack.add(cur.right);
                depths.add(depth+1);
            }
        }
        return max;
    }
}
