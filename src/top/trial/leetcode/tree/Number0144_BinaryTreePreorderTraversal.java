package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.*;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/27
 * @Version 1.0
 */
public class Number0144_BinaryTreePreorderTraversal {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> result = preorderTraversalTwo(root);
        ArrayListUtil.getOneDimensionalSysoLikeArray(result);
    }

    //如果使用迭代
    private List<Integer> preorderTraversalTwo(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null)
            stack.push(root);
        while (!stack.empty()) {
            TreeNode curr = stack.pop();
            list.add(curr.val);
            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);
        }
        return list;
    }

    //前序遍历，很简单啊
    private List<Integer> preorderTraversalOne(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderOne(root, list);
        return list;
    }

    private void preorderOne(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        preorderOne(root.left, list);
        preorderOne(root.right, list);
    }
}
