package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层次遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/6
 * @Version 1.0
 */
public class Number0107_BinaryTreeLevelOrderTraversalTwo {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        ArrayListUtil.getTwoDimensionalSysoLikeArray(levelOrderBottomOne(root));
    }

    LinkedList<List<Integer>> results = new LinkedList<>();

    private List<List<Integer>> levelOrderBottomOne(TreeNode root) {
        traversal(root, 0);
        return results;
    }

    private void traversal(TreeNode root, int i) {
        if (root == null)
            return;
        if (results.size() == i) {
            List<Integer> curr = new ArrayList<>();
            curr.add(root.val);
            results.addFirst(curr);
        } else {
            results.get(results.size() - i - 1).add(root.val);
        }
        traversal(root.left, i + 1);
        traversal(root.right, i + 1);

    }
}
