package leetcode.tree;

/**
 * 98. 验证二叉搜索树 中等难度
 * https://leetcode.cn/problems/validate-binary-search-tree/description/?envType=study-plan-v2&envId=top-100-liked
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左
 * 子树
 * 只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer lower, Integer upper) {
        if (root == null) {
            return true;
        }
        int val = root.val;
        // 检查当前节点是否满足条件
        if(lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }
        // 递归检查左右子树
        return isValidBST(root.left, lower, val) && isValidBST(root.right, val, upper);
    }

    public static void main(String[] args) {
        IsValidBST validator = new IsValidBST();

        // 构建一个示例二叉树
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        // 验证二叉树是否是有效的二叉搜索树
        boolean isValid = validator.isValidBST(root);
        System.out.println("Is the tree a valid BST? " + isValid);
    }

}
