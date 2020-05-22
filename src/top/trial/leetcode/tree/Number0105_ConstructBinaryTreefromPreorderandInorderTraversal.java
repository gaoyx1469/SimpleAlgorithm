package top.trial.leetcode.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 
 * 注意: 你可以假设树中没有重复的元素。
 * 
 * 例如，给出
 * 
 * 前序遍历 preorder = [3,9,20,15,7] 中序遍历 inorder = [9,3,15,20,7] 返回如下的二叉树：
 * 
 * 3
 * 
 * / \
 * 
 * 9 20
 * 
 * / \
 * 
 * 15 7
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0105_ConstructBinaryTreefromPreorderandInorderTraversal {

	@Test
	public void solution() {
		int[] preorder = { 3, 9, 20, 15, 7 };
		int[] inorder = { 9, 3, 15, 20, 7 };
		TreeNode tn15 = new TreeNode(15);
		TreeNode tn7 = new TreeNode(7);
		TreeNode tn20 = new TreeNode(20);
		TreeNode tn9 = new TreeNode(9);
		TreeNode tn3 = new TreeNode(3);
		tn20.left = tn15;
		tn20.right = tn7;
		tn3.left = tn9;
		tn3.right = tn20;
		TreeNode result = constructBinaryTreeOne(preorder, inorder);
		getResult(result);

	}

	private void getResult(TreeNode result) {
		System.out.println(result.val);
		if (result.left != null)
			getResult(result.left);
		if (result.right != null)
			getResult(result.right);
	}

	/**
	 * 数据结构课上好像讲过
	 * 
	 * 可以假设树中没有重复的元素，这一条为查找元素位置提供可能
	 * 
	 * @param preorder 前序遍历数组
	 * @param inorder  中序遍历数组
	 * @return
	 */
	private TreeNode constructBinaryTreeOne(int[] preorder, int[] inorder) {

		int len = preorder.length;
		if (len == 0)
			return null;

		TreeNode root = new TreeNode(preorder[0]);

		getLeftAndRightByRoot(root, preorder, inorder);

		return root;
	}

	/**
	 * 获取左右子元素，填回root
	 * 
	 * @param root
	 * @param inorder
	 * @param preorder
	 */
	private void getLeftAndRightByRoot(TreeNode root, int[] preorder, int[] inorder) {

		int rootVal = root.val;
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == rootVal) {
				// 左子树从下标1到i,右子树从i+1到最后
				if (i > 0) {
					TreeNode left = new TreeNode(preorder[1]);
					root.left = left;
					int[] leftPre = Arrays.copyOfRange(preorder, 1, i);
					int[] leftIn = Arrays.copyOfRange(inorder, 0, i - 1);
					getLeftAndRightByRoot(left, leftPre, leftIn);
				}
				if (i < preorder.length - 1) {
					TreeNode right = new TreeNode(preorder[i + 1]);
					root.right = right;
					int[] rightPre = Arrays.copyOfRange(preorder, i + 1, preorder.length);
					int[] rightIn = Arrays.copyOfRange(inorder, i + 1, inorder.length);
					getLeftAndRightByRoot(right, rightPre, rightIn);
				}
				return;
			}
		}

	}

}
