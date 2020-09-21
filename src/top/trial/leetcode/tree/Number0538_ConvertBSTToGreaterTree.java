package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.TreeNodeUtil;

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * <p>
 * 例如：
 * 输入: 原始二叉搜索树:
 * 5
 * /   \
 * 2     13
 * <p>
 * 输出: 转换为累加树:
 * 18
 * /   \
 * 20     13
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/21
 * @Version 1.0
 */
public class Number0538_ConvertBSTToGreaterTree {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        convertBSTOne(root);
        System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(root));
    }

    int val = 0;

    private TreeNode convertBSTOne(TreeNode root) {
        if (root == null)
            return null;
        //反向中续遍历并改值
        unmrd(root);
        return root;
    }

    private void unmrd(TreeNode root) {
        if (root.right != null)
            unmrd(root.right);
        root.val += val;
        val = root.val;
        if (root.left != null)
            unmrd(root.left);
    }
}
