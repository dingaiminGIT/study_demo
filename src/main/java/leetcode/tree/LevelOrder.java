package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/die-dai-di-gui-duo-tu-yan-shi-102er-cha-shu-de-cen/
 * vhttps://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * @author: dingaimin
 * @date: 2021/1/16 18:12
 */
public class LevelOrder {

    /**
     * 广度优先遍历（根->左->右） + 队列保存每层的元素（FIFO）
     *
     * 时间复杂度：每个点进队出队各一次，故渐进时间复杂度为 O(n)。
     * 空间复杂度：队列中元素的个数不超过 n 个，故渐进空间复杂度为 O(n)。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点放入到队列中，然后不断遍历队列
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            // 获取当前节点队列的长度，这个长度相当于当前这一层的节点个数
            int currentSize = queue.size();
            // 将队列中的元素的都拿出来（即获取这一层的节点），放到临时的list 即 level 中
            // 如果节点的左/右子树不为空，也放入到队列中
            for (int i = 1; i <= currentSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 将临时的 list 即 level 加入到最终的返回结果中
            ret.add(level);
        }
        return ret;
    }
}
