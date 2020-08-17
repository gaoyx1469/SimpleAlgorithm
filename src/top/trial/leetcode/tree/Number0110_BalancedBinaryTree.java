package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/17
 * @Version 1.0
 */
public class Number0110_BalancedBinaryTree {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Assert.assertTrue(isBalancedTwo(root));
    }

    /**
     * 先看子树是否平衡，若子树不平衡，则整棵树不平衡
     *
     * @param root TreeNode
     * @return boolean
     */
    private boolean isBalancedTwo(TreeNode root) {
        return treeLevel(root) >= 0;
    }

    private int treeLevel(TreeNode root) {
        if (root == null)
            return 0;
        int leftLevel = treeLevel(root.left);
        int rightLevel = treeLevel(root.right);
        if (leftLevel < 0 || rightLevel < 0)
            return -1;
        if (Math.abs(leftLevel - rightLevel) >= 2)
            return -1;
        return Math.max(rightLevel, leftLevel) + 1;
    }

    /**
     * 左右子树高度相差不超过2.且左右子树是二叉平衡树，感觉有重复计算
     *
     * @param root TreeNode
     * @return boolean
     */
    private boolean isBalancedOne(TreeNode root) {

        if (root == null)
            return true;
        else
            return Math.abs(getLevel(root.left) - getLevel(root.right)) <= 1 && isBalancedOne(root.left) && isBalancedOne(root.right);
    }

    private int getLevel(TreeNode root) {

        if (root == null)
            return 0;
        else
            return Math.max(getLevel(root.left), getLevel(root.right)) + 1;
    }


}
