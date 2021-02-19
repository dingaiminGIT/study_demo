package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * 二叉搜索树与二叉树的区别
 * https://www.cnblogs.com/williamjie/p/11081096.html
 * 二叉搜索树的左根右有大小关系
 *
 * @author: dingaimin
 * @date: 2021/2/15 19:31
 */
public class LowestCommonAncestor {

    /**
     * 两次遍历分别找 p 和 q
     * 从根节点开始遍历，如果当前节点就是 p，那么成功找到了节点
     * 如果当前节点的值小于 p 的值，说明 p 在当前节点的右子树中，所以将当前节点移动到其右节点
     * 如果当前节点的值大于 p 的值，说明 p 在当前节点的左子树中，所以将当前节点移动到其左节点
     *
     * 在查找节点的同时，要记录经过的节点，即从根节点到被访问及诶单的路径
     * 对于 q 同 p
     *
     * 找到了从根节点到 p 和 q 的路径之后，p 和 q 的最近公共祖先就是从根节点到它们路径上的分岔点即最后一个相同的节点
     *
     * 时间复杂度 O(n)  6ms 99.7%
     * 空间复杂度 o(n)  39MB 96.78%
     *
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = getPath(root, p);
        List<TreeNode> pathQ = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < pathP.size() && i < pathQ.size(); i++) {
            if (pathP.get(i) == pathQ.get(i)) {
                ancestor = pathP.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    public List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<>();
        TreeNode node = root;
        while (node != target) {
            // 记录路径
            path.add(node);
            if (target.val > node.val) {
                // 找右子树
                node = node.right;
            } else {
                node = node.left;
            }
        }
        path.add(node);
        return path;
    }

    /**
     * 一次遍历
     * 从根节点开始遍历
     * 如果当前节点的值大于 p 和 q 的值，说明 p 和 q 在当前节点的左子树中，将当前节点移动到它的左子节点
     * 如果当前节点的值小于 p 和 q 的值，说明 p 和 q 在当前节点的右子树中，将当前节点移动到它的右子节点
     * 否则，说明当前节点就是分岔点即最后一个相同的节点
     *
     * 时间复杂度 ： O(n)  6ms 99.70%
     * 空间复杂度：O(1)  39.3 MB 53.16%
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (ancestor.val > p.val && ancestor.val > q.val) {
                ancestor = ancestor.left;
            } else if (ancestor.val < p.val && ancestor.val < q.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }
}
