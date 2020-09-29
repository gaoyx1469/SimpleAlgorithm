package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/29
 * @Version 1.0
 */
public class Number0145_BinaryTreePostorderTraversal {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> list = postorderTraversalTwo(root);
        ArrayListUtil.getOneDimensionalSysoLikeArray(list);
    }

    private List<Integer> postorderTraversalTwo(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.getFirst();
            if (node.left == null && node.right == null) {
                result.add(node.val);
                deque.pollFirst();
            } else {
                if (node.right != null) {
                    deque.addFirst(node.right);
                    node.right = null;
                }
                if (node.left != null) {
                    deque.addFirst(node.left);
                    node.left = null;
                }
            }
        }
        return result;
    }

    private List<Integer> postorderTraversalOne(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        doPostorderTraversalOne(root, result);
        return result;
    }

    private void doPostorderTraversalOne(TreeNode root, List<Integer> result) {
        if (root == null)
            return;
        doPostorderTraversalOne(root.left, result);
        doPostorderTraversalOne(root.right, result);
        result.add(root.val);
    }
}
