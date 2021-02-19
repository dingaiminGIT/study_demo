package leetcode.tree;

import java.util.*;

/**
 *  二叉树的锯齿形层序遍历
 *  https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 *  给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * @author: dingaimin
 * @date: 2021/2/16 16:50
 */
public class ZigzagLevelOrder {

    /**
     * BFS + 双端队列
     * 维护一个变量记录是从左往右还是从右往左
     * 如果从左往右，就将每次遍历的元素插入到双端队列的末尾
     * 如果从右往左，就将每次遍历的元素插入到双端队列的头部
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            // 双端队列
            Deque<Integer> level = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    level.addLast(curNode.val);
                } else {
                    level.addFirst(curNode.val);
                }
                // 遍历左右子节点
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            res.add(new LinkedList<>(level));
            isOrderLeft = !isOrderLeft;
        }
        return res;
    }
}
