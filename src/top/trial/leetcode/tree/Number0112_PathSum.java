package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例:  给定如下二叉树，以及目标和 sum = 22，
 * 
 * 5
 * 
 * / \
 * 
 * 4 8
 * 
 * / / \
 * 
 * 11 13 4
 * 
 * / \ \
 *
 * 7 2 1
 * 
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0112_PathSum {

	@Test
	public void solution() {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);

		boolean expected = true;
		boolean result = hasPathSumOne(root, 22);

		Assert.assertEquals(expected, result);

	}

	/**
	 * 递归
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	private boolean hasPathSumOne(TreeNode root, int sum) {

		/*
		 * if (root != null) { if (root.left == null && root.right == null) {// 叶子节点 if
		 * (root.val == sum) return true; else return false; } return
		 * hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum -
		 * root.val); } else return false;
		 */
		// 优化版
		if (root == null)
			return false;
		if (root.left == null && root.right == null)
			return sum == root.val;
		return hasPathSumOne(root.left, sum - root.val) || hasPathSumOne(root.right, sum - root.val);
	}

	/**
	 * 官方题解提供了一个广度优先搜索方式
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	private boolean hasPathSumTwo(TreeNode root, int sum) {
		// TODO广度优先搜索
		return false;
	}
}
