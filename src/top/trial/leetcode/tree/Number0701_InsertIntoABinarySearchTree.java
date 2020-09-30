package top.trial.leetcode.tree;

import org.junit.Test;

import top.trial.leetcode.utils.TreeNodeUtil;

/**
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。
 * 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
 * 
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0701_InsertIntoABinarySearchTree {
	@Test
	public void solution() {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		insertIntoBSTOne(root, 5);
		System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(root));

	}

	private TreeNode insertIntoBSTOne(TreeNode root, int val) {
		if (root == null)
			return new TreeNode(val);
		doInsert(root, val);
		return root;
	}

	private void doInsert(TreeNode root, int val) {
		int nodeVal = root.val;
		if (nodeVal > val) {
			if (root.left == null)
				root.left = new TreeNode(val);
			else
				doInsert(root.left, val);
		} else {
			if (root.right == null)
				root.right = new TreeNode(val);
			else
				doInsert(root.right, val);
		}
	}

}
