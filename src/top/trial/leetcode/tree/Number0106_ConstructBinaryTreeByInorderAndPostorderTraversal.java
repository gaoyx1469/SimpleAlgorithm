package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.TreeNodeUtil;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/25
 * @Version 1.0
 */
public class Number0106_ConstructBinaryTreeByInorderAndPostorderTraversal {
    @Test
    public void solution() {
        //int[] inOrder = {9, 3, 15, 20, 7};
        int[] inOrder = {2, 3, 1};
        //int[] postOrder = {9, 15, 7, 20, 3};
        int[] postOrder = {3, 2, 1};
        TreeNode root = buildTreeOne(inOrder, postOrder);
        System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(root));
    }

    private TreeNode buildTreeOne(int[] inOrder, int[] postOrder) {
        int len = inOrder.length;
        if (len == 0)
            return null;
        return doBuildTree(inOrder, 0, postOrder, 0, len);
    }

    private TreeNode doBuildTree(int[] inOrder, int inStartIndex, int[] postOrder, int postStartIndex, int len) {
        TreeNode root;
        if (len == 1) {
            root = new TreeNode(inOrder[inStartIndex]);
        } else {
            int leftLen = len - 1;
            int minVal = postOrder[postStartIndex + len - 1];
            root = new TreeNode(minVal);
            for (int i = 0; i < len; ++i) {//此处可以使用哈希表优化，先将中序遍历的结点值和下标的对应关系存入哈希表，就不用循环了
                if (inOrder[inStartIndex + i] == minVal) {
                    leftLen = i;
                    break;
                }
            }
            if (leftLen > 0) {//存在左子树
                root.left = doBuildTree(inOrder, inStartIndex, postOrder, postStartIndex, leftLen);
            }
            if (leftLen < len - 1) {//存在右子树
                root.right = doBuildTree(inOrder, inStartIndex + leftLen + 1, postOrder, postStartIndex + leftLen, len - leftLen - 1);
            }
        }
        return root;
    }
}
