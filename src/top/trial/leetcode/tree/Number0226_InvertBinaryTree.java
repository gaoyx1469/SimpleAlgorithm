package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.TreeNodeUtil;

/**
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/16
 * @Version 1.0
 */
public class Number0226_InvertBinaryTree {

    @Test
    public void solution() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(invertBinaryTreeOne(root)));
    }

    /**
     * 对于每一个非叶子结点，左右子结点互换即可，至于遍历所有非子结点，BDF和DFS都行嘛
     * <p>
     * 此方法的作用是：互换自己的左右结点，并递归调用其子结点
     *
     * @param root TreeNode 根结点
     */
    private TreeNode invertBinaryTreeOne(TreeNode root) {
        if (root == null)//空结点
            return null;
        if (root.left != null || root.right != null) {//非叶子结点，互换
            TreeNode tempNode = root.left;
            root.left = root.right;
            root.right = tempNode;
            invertBinaryTreeOne(root.left);
            invertBinaryTreeOne(root.right);
        }
        return root;
    }
}
