package top.trial.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * 
 *   3
 *  / \ 
 *  9 20 
 *    / \ 
 *   15 7 
 * 返回其层次遍历结果：
 * 
 * [ [3], [9,20], [15,7] ]
 *
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx-vm
 *
 */
public class Number0102_BinaryTreeLevelOrderTraversal {

	@Test
	public void solution() {

		TreeNode t15 = new TreeNode(15);
		TreeNode t7 = new TreeNode(7);
		TreeNode t20 = new TreeNode(20);
		TreeNode t9 = new TreeNode(9);
		TreeNode t3 = new TreeNode(3);
		t20.left = t15;
		t20.right = t7;
		t3.left = t9;
		t3.right = t20;

		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(3);
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(9);
		l2.add(20);
		List<Integer> l3 = new ArrayList<Integer>();
		l3.add(15);
		l3.add(7);
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		expected.add(l1);
		expected.add(l2);
		expected.add(l3);

		List<List<Integer>> result = getBinaryTreeLevelOrderTraversalOne(t3);

		Assert.assertEquals(expected, result);
	}

	/**
	 * 采用递归方式，一次通过
	 * 
	 * @param t
	 * @return
	 */
	private List<List<Integer>> getBinaryTreeLevelOrderTraversalOne(TreeNode t) {
		
		

		List<List<Integer>> ll = new ArrayList<List<Integer>>();
		
		if(t == null) {
			return ll;
		}

		int level = 1;

		getBinaryTreeLevelOrderTraversal(t, level, ll);

		return ll;
	}

	private void getBinaryTreeLevelOrderTraversal(TreeNode t, int level, List<List<Integer>> ll) {

		if (ll.size() < level) {
			List<Integer> list = new ArrayList<Integer>();
			ll.add(list);
		}
		
		List<Integer> thisList = ll.get(level-1);
		thisList.add(t.val);
		
		if(t.left != null) {
			getBinaryTreeLevelOrderTraversal(t.left,level+1,ll);
		}
		if(t.right != null) {
			getBinaryTreeLevelOrderTraversal(t.right,level+1,ll);
		}

	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}
