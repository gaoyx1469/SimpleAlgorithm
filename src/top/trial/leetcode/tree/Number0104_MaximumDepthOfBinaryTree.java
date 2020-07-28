package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/28
 * @Version 1.0
 */
public class Number0104_MaximumDepthOfBinaryTree {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int expected = 3;
        int result = maximumDepthOfBinaryTree(root);
        Assert.assertEquals(expected, result);
    }

    /**
     * 深度优先搜索和广度优先搜索
     *
     * @param root TreeNode
     * @return int
     */
    private int maximumDepthOfBinaryTree(TreeNode root) {
        if (root != null) {
            return getMaxDeep(root, 1);
        }
        return 0;
    }

    private int getMaxDeep(TreeNode root, int i) {

        int left = 0;
        int right = 0;

        if (root.left != null)
            left = getMaxDeep(root.left, i + 1);

        if (root.right != null)
            right = getMaxDeep(root.right, i + 1);

        return Math.max(i, Math.max(left, right));

    }
}
