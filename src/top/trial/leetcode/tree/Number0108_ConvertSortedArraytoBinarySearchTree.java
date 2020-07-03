package top.trial.leetcode.tree;

import org.junit.Test;

import top.trial.leetcode.utils.TreeNodeUtil;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 
 * 示例:
 * 
 * 给定有序数组: [-10,-3,0,5,9],
 * 
 * 一个可能的答案是：[0,-3,9,-10,null,5]
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0108_ConvertSortedArraytoBinarySearchTree {

	@Test
	public void solution() {
		int[] nums = { -10, -3, 0, 5, 9 };
		TreeNode root = convertSortedArraytoBinarySearchTreeOne(nums);
		System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(root));
	}

	/**
	 * 思想是，取中间值作为根，其余左右排布
	 * 
	 * @param nums2
	 * @return
	 */
	private TreeNode convertSortedArraytoBinarySearchTreeOne(int[] nums) {
		int len = nums.length;
		if(len == 0)
			return null;

		TreeNode root = new TreeNode(nums[len / 2]);

		setChildNode(root, nums, 0, len / 2 - 1, true);
		setChildNode(root, nums, len / 2 + 1, len - 1, false);

		return root;
	}

	/**
	 * 
	 * 
	 * @param root  根结点
	 * @param nums  原始数组
	 * @param start 起始下标
	 * @param end   结束下表
	 * @param left  是否是左子结点
	 */
	private void setChildNode(TreeNode root, int[] nums, int start, int end, boolean left) {
		if (start > end) {// 无此结点，直接return
			return;
		} else {
			int mid = (start + end) / 2;
			TreeNode node = new TreeNode(nums[mid]);
			if (left)
				root.left = node;
			else
				root.right = node;

			if (start != end) {
				setChildNode(node, nums, start, mid - 1, true);
				setChildNode(node, nums, mid + 1, end, false);
			}
			return;
		}
	}

}
