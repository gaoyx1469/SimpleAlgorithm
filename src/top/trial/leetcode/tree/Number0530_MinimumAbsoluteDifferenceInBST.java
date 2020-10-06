package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 1
 * \
 * 3
 * /
 * 2
 * <p>
 * 输出：
 * 1
 * <p>
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中至少有 2 个节点。
 *
 * @Author gaoyx1469
 * @Date 2020/7/21
 * @Version 1.0
 */
public class Number0530_MinimumAbsoluteDifferenceInBST {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        int expected = 1;
        int result = getMinimumDifferenceOne(root);
        Assert.assertEquals(expected, result);
    }

    private int getMinimumDifferenceOne(TreeNode root) {

        //遍历到list中
        List<Integer> list = new ArrayList<>();
        putIntoList(root, list);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; ++i) {
            result = Math.min(result, list.get(i + 1) - list.get(i));
        }
        return result;
    }

    private void putIntoList(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        putIntoList(root.left, list);
        list.add(root.val);
        putIntoList(root.right, list);
    }

}
