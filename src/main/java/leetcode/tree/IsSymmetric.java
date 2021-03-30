package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 * https://leetcode-cn.com/problems/symmetric-tree/
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 进阶：
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 *
 * @author: dingaimin
 * @date: 2021/2/28 15:03
 */
public class IsSymmetric {

    /**
     * 方法1：递归
     * 如果一个树的左右子树镜像对称，那么这个树就是对称的 -> 两个树在什么情况下互为镜像？
     * 1.两个树的根节点值相同
     * 2.每个树的右子树与另一个树的左子树镜像对称
     *
     * 构造递归函数
     * 通过同步移动两个指针的方法来遍历这个树
     * p 和 q 开始都指向 root
     * p 右移时，q 左移
     * p 左移时，q 右移
     * 每次检查当前 p 和 q 节点的值是否相等，如果相等再判断左右子树是否对称
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    /**
     * 时间复杂度：遍历了树，渐进时间复杂度为 O(n)
     * 空间复杂度：这里的空间复杂度和递归使用的栈空间有关，这里递归层数不超过 n，故渐进空间复杂度为 O(n)。
     *
     * 0ms 100%
     * 36.s MB 89.31%
     *
     * @param p
     * @param q
     * @return
     */
    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        return check2(root, root);
    }

    /**
     * 迭代：借助队列
     * 初始化时将 root 入队两次
     * 每次从队列中取出两个节点进行比较值（队列中两个联系的节点应该是相等的，并且他们的子树互为镜像）
     * 然后将两个节点的左右子节点按相反的顺序插入到队列中
     * 结束条件：队列为空 || 树不对称即从队列中取出两个不相等的连续节点
     *
     * 时间复杂度：O(n)，同「方法一」。
     * 空间复杂度：这里需要用一个队列来维护节点，每个节点最多进队一次，出队一次，队列中最多不会超过 n 个点，故渐进空间复杂度为 O(n)
     *  1ms 28.67%
     *  37.9 MB 6.99
     *
     * @param u
     * @param v
     * @return
     */
    public boolean check2(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<>();
        // 将 root 入队两次
        q.offer(u);
        q.offer(v);

        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }
            // 相反顺序入队
            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);

            q.offer(v.left);
        }
        return true;
    }
}
