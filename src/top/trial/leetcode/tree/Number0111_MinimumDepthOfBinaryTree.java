package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/21
 * @Version 1.0
 */
public class Number0111_MinimumDepthOfBinaryTree {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int expected = 2;
        int result = minDepthOne(root);
        Assert.assertEquals(expected, result);
    }

    private int minDepthOne(TreeNode root) {
        int result = 0;
        if (root == null)
            return result;
        Deque<TreeNode> list1 = new LinkedList<>();
        Deque<TreeNode> list2 = new LinkedList<>();
        list1.push(root);
        result++;
        while (!list1.isEmpty() || !list2.isEmpty()) {
            if (list2.isEmpty()) {
                if (haveLeaves(list1, list2))
                    result++;
                else
                    break;
            } else if (haveLeaves(list2, list1)) {
                result++;
            } else
                break;
        }

        return result;
    }

    private boolean haveLeaves(Deque<TreeNode> list1, Deque<TreeNode> list2) {
        while (!list1.isEmpty()) {
            TreeNode node = list1.poll();
            if (node.left == null && node.right == null)
                return false;
            if (node.left != null)
                list2.push(node.left);
            if (node.right != null)
                list2.push(node.right);
        }
        return true;
    }
}
