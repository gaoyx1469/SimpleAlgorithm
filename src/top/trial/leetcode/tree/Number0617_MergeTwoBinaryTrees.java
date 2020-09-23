package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.TreeNodeUtil;

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p>
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1   3
 * /                           \   \
 * 5                             4   7
 * 输出:
 * 合并后的树:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/23
 * @Version 1.0
 */
public class Number0617_MergeTwoBinaryTrees {
    @Test
    public void solution() {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.left.left = new TreeNode(5);
        root1.right = new TreeNode(2);
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.left.right = new TreeNode(4);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(7);
        System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(mergeTreesOne(root1, root2)));
    }

    private TreeNode mergeTreesOne(TreeNode root1, TreeNode root2) {
        if (root1 == null)
            return root2;
        if (root2 == null)
            return root1;
        mergeTreesTwo(root1, root2);
        return root1;
    }

    private void mergeTreesTwo(TreeNode root1, TreeNode root2) {
        //值相加
        root1.val += root2.val;

        if (root2.left != null) {
            if (root1.left == null)
                root1.left = root2.left;
            else
                mergeTreesTwo(root1.left, root2.left);
        }
        if (root2.right != null) {
            if (root1.right == null)
                root1.right = root2.right;
            else
                mergeTreesTwo(root1.right, root2.right);
        }
    }
}
