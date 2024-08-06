package leetcode.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 *
 * @author: dingaimin
 * @date: 2021/2/15 20:18
 */
public class LowestCommonAncestor2 {

    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    /**
     * 后序遍历 DFS
     *
     * https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-ii-er-cha-shu-de-zui-jin-gong-gon-7/
     *
     * 时间复杂度 O(n) 7ms 99.98%
     * 空间复杂度 O(n) 39.4 MB 93%
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
       // 递归终止条件
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        // left 和 right 同时为空，说明 root 的左右子树都不包含 p,q，返回null
        if (left == null && right == null) {
            return null;
        }
        // left 为空，right 不为空，返回 right
        if (left == null) {
            return right;
        }
        // right 为空，left 不为空，返回 left
        if (right == null) {
            return left;
        }
        // left != null && right != null，说明 p,q 分别在 root 的异侧，所以 root 就是最近公祖先，然后 root
        return root;

    }

    /**
     * 在 2 个基础上优化下写法
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        // 递归终止条件
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor3(root.left, p, q);
        TreeNode right = lowestCommonAncestor3(root.right, p, q);

        // 如果左右都能找到p或q，说明当前节点就是最近公共祖先
        if (left != null && right != null) {
            return root;
        }
        // 如果只有一个能找到p或q，那么最近公共祖先就是能找到的那个节点
        return left == null ? right : left;

    }

    /**
     * 存储父节点
     * 用哈希表存储所有节点的父节点，然后利用节点的父节点信息从 p 节点不断往上跳，并记录已经访问过的节点
     * 再从 q 节点不断往上跳，如果碰到已经访问过的节点，那么这个节点就是最近公共祖先
     *
     * 时间复杂度 O(n) 11ms 14.97%
     * 空间复杂度 O(n) 39.9 MB 47.61%
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 存储父节点
        dfs(root);
        while (p != null) {
            // 记录访问过的父节点
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    public void dfs(TreeNode root) {
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
    }
}
