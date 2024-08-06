package leetcode.tree;

/**
 * 543. 二叉树的直径 简单
 * https://leetcode.cn/problems/diameter-of-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class DiameterOfBinaryTree {

    int nodeCount;

    public int diameterOfBinaryTree(TreeNode root) {
        nodeCount = 1;
        depth(root);
        // 直径为路径经过的节点数-1
        return nodeCount - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 左儿子为根的子树的深度
        int lNodeCount = depth(node.left);
        // 右儿子为根的子树的深度
        int rNodeCount = depth(node.right);

        // 计算d_node即L+R+1 并更新ans
        nodeCount = Math.max(nodeCount, lNodeCount + rNodeCount + 1);
        // 返回该节点为根的子树的深度
        return Math.max(lNodeCount, rNodeCount) + 1;
    }

}
