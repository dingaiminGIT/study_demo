package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *
 * @author: dingaimin
 * @date: 2021/2/17 19:46
 */
public class BuildTree {

    private Map<Integer, Integer> indexMap;

    /**
     * 递归
     * 主要根据二叉树的前序遍历和中序遍历的特点来找规律
     * 前序遍历：根节点，左子树的前序遍历结果，右子树的前序遍历结果
     * 中序遍历：左子树的中序遍历结果，根节点，右子树的中序遍历结果
     *
     * 关键点：从中序遍历的结果中定位到根节点，这样就可以从根节点分成左右子树两个部分，就可以知道左右子树中的节点的数目了，然后就可以去前序遍历的结果中定位所有的左右子树
     * 然后再递归左右子树，再将左右子树连接到根节点上
     *
     * 技巧：用哈希表来帮助我们定位中序遍历结果中的根节点，key：节点的值，value ：节点在中序遍历结果中出现的位置
     *
     * 时间复杂度： O(n)，其中 n 是树中节点个数
     * 空间复杂度：O(n) 除去返回的答案需要的 O(n) 空间之外，我们还需要使用 O(n) 的空间存储哈希映射，以及 O(h)（其中 h 是树的高度）的空间表示递归时栈空间。这里 h < n，所以总空间复杂度为 O(n)
     *  2 ms 97.74%
     *  38.7 MB 35.46%
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 用哈希表存储中序遍历结果中的节点的值和节点在中序遍历结果中出现的位置，便于我们定位中序遍历结果中的根节点
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    /**
     *
     * @param preOrder
     * @param inOrder
     * @param preOrderLeft
     * @param preOrderRight
     * @param inOrderLeft
     * @param inOrderRight
     * @return
     */
    public TreeNode buildTreeHelper(int[] preOrder, int[] inOrder, int preOrderLeft, int preOrderRight, int inOrderLeft, int inOrderRight) {
        // 递归终止条件
        if (preOrderLeft > preOrderRight) {
            return null;
        }

        // 前序遍历的第一个元素就是根节点
        int preOrderRoot = preOrderLeft;
        // 从中序遍历结果中定位根节点的下标
        Integer inorderRoot = indexMap.get(preOrder[preOrderRoot]);

        // 创建根节点
        TreeNode root = new TreeNode(preOrder[preOrderRoot]);
        // 左子树节点的数量
        int leftSubTreeSize = inorderRoot - inOrderLeft;
        // 递归构造左子树
        // 前序遍历 [preOrderLeft + 1, preOrderLeft + leftSubTreeSize]
        // 中序遍历 [inOrderLeft, inorderRoot - 1]
        root.left = buildTreeHelper(preOrder, inOrder, preOrderLeft + 1, preOrderLeft + leftSubTreeSize, inOrderLeft, inorderRoot - 1);

        // 递归构造右子树
        // 前序遍历 [preOrderLeft + leftSubTreeSize + 1, preOrderRight]
        // 中序遍历 [inorderRoot + 1, inOrderRight]
        root.right = buildTreeHelper(preOrder, inOrder, preOrderLeft + leftSubTreeSize + 1, preOrderRight, inorderRoot + 1, inOrderRight);

        return root;
    }
}
