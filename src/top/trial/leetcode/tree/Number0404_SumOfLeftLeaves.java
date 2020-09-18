package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 计算给定二叉树的所有左叶子之和。
 * <p>
 * 示例：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-left-leaves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/19
 * @Version 1.0
 */
public class Number0404_SumOfLeftLeaves {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int expected = 24;
        int result = getSumOfLeftLeaves(root);
        Assert.assertEquals(expected, result);
    }

    int sum = 0;

    private int getSumOfLeftLeaves(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return sum;
        addLeftLeaves(root, false);
        return sum;
    }

    private void addLeftLeaves(TreeNode root, boolean flag) {
        if (root.left == null && root.right == null) {//叶子结点
            if (flag)
                sum += root.val;
        } else {
            if (root.left != null)
                addLeftLeaves(root.left, true);
            if (root.right != null)
                addLeftLeaves(root.right, false);
        }
    }

}
