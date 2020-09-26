package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/26
 * @Version 1.0
 */
public class Number0113_PathSum {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        int sum = 22;
        ArrayListUtil.getTwoDimensionalSysoLikeArray(pathSum(root, sum));
    }


    private List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        doPathSum(root, sum, temp, result, 0);
        return result;
    }

    private void doPathSum(TreeNode root, int sum, List<Integer> temp, List<List<Integer>> result, int val) {
        if (root.left == null && root.right == null) {//根节点
            if (val + root.val == sum) {
                List<Integer> list = new ArrayList<>(temp);
                list.add(root.val);
                result.add(list);
            }
            return;
        }
        temp.add(root.val);
        if (root.left != null) {
            doPathSum(root.left, sum, temp, result, val + root.val);
        }
        if (root.right != null) {
            doPathSum(root.right, sum, temp, result, val + root.val);
        }
        temp.remove(temp.size() - 1);
    }

}
