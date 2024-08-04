package leetcode.tree;

/**
 * 108. 将有序数组转换为二叉搜索树  简单
 *
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/?envType=study-plan-v2&envId=top-100-liked
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return helper(nums, 0, nums.length - 1);
    }

    /**
     * 递归：选择数组的中间节点作为根节点，然后递归的对左右子数组进行相同的操作
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // 选择中国节点作为根节点
        int mid = left + (right-left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        // 递归构建左右子树
        node.left = helper(nums, left, mid - 1);
        node.right = helper(nums, mid+1, right);
        return node;
    }

    private void inOrderPrint(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderPrint(root.left);
        System.out.print(root.val + " ");
        inOrderPrint(root.right);
    }

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        TreeNode root = sortedArrayToBST.sortedArrayToBST(nums);
        sortedArrayToBST.inOrderPrint(root);
    }
 }
