package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: dingaimin
 * @date: 2021/3/4 15:06
 */
public class RightSideView {

    List<Integer> res = new ArrayList<>();

    /**
     * https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/jian-dan-bfsdfs-bi-xu-miao-dong-by-sweetiee/
     * BFS，利用 BFS 进行层序遍历，记录每层的最后一个元素
     *
     * 时间复杂度：O(N) 每个节点都被入队出队了一次
     * 空间复杂度：O(N) 使用了额外的队列空间
     *  2 ms 19.08%
     *  37.2MB 32.09%
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // BFS 用队列 根->左->右
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // 将当前层最后一个节点放到结果中
                if (i == size-1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    /**
     * DFS 根->右->左，这样可以保证每层最都最先访问最右边的节点
     * 时间复杂度：O(N) 每个节点都访问一次
     * 空间复杂度：O(N) 非平衡二叉树，二叉树的深度最少为 logN，最坏情况是N，所以递归使用额栈空间是 O(N) 的
     * 1 ms 98.54%
     * 37.3 MB 19.08%
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    public void dfs(TreeNode root, int depth) {
        // 递归终止条件
        if (root == null) {
            return;
        }

        // 根 -> 右 -> 左
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中
        if (depth == res.size()) {
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }
}
