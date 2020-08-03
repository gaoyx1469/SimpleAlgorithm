package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.TreeNodeUtil;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 * <p>
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/2
 * @Version 1.0
 */
public class Number0114_FlattenBinaryTreeToLinkedList {

    @Test
    public void solution() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        flattenBinaryTreeToLinkedList(root);
        System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(root));
    }

    /**
     * 原地转换为右侧单链的
     *
     * @param root TreeNode
     */
    private void flattenBinaryTreeToLinkedList(TreeNode root) {

        TreeNode haveLeftSubTreeNode;

        while ((haveLeftSubTreeNode = haveLeftSubTree(root)) != null) {
            if (haveLeftSubTreeNode.right != null) {
                TreeNode rightNode = getRightestNode(haveLeftSubTreeNode.left);
                rightNode.right = haveLeftSubTreeNode.right;
            }
            haveLeftSubTreeNode.right = haveLeftSubTreeNode.left;
            haveLeftSubTreeNode.left = null;
        }
    }

    /**
     * 寻找节点中的最右叶节点
     *
     * @param node TreeNode
     */
    private TreeNode getRightestNode(TreeNode node) {

        if (node.right != null)
            return getRightestNode(node.right);
        else
            return node;
    }

    /**
     * 找寻存在左子树的节点
     *
     * @param root TreeNode
     * @return TreeNode
     */
    private TreeNode haveLeftSubTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode currNode = root;
        while (currNode.left != null || currNode.right != null) {
            if (currNode.left != null) {
                return currNode;
            } else {
                currNode = currNode.right;
            }
        }
        return null;
    }
}
