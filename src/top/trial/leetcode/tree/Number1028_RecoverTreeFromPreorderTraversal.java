package top.trial.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import top.trial.leetcode.utils.TreeNodeUtil;

/**
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * 
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D +
 * 1。根节点的深度为 0）。
 * 
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * 
 * 给出遍历输出 S，还原树并返回其根节点 root。
 * 
 * 示例 1： 输入："1-2--3--4-5--6--7" 输出：[1,2,5,3,4,6,7]
 * 
 * 示例 2：输入："1-2--3---4-5--6---7" 输出：[1,2,5,3,null,6,null,4,null,7]
 * 
 * 示例 3：输入："1-401--349---90--88" 输出：[1,401,null,349,88,90]
 * 
 * 提示：原始树中的节点数介于 1 和 1000 之间。 每个节点的值介于 1 和 10 ^ 9 之间。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number1028_RecoverTreeFromPreorderTraversal {

	@Test
	public void solution() {
		String S = "1-2--3---4-5--6---7";
		TreeNode root = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		root.left = node2;
		root.right = node5;
		node2.left = node3;
		node3.left = node4;
		node5.left = node6;
		node6.left = node7;

		TreeNode result = recoverTreeFromPreorderTraversalOne(S);

		String expectedStr = TreeNodeUtil.getStringOfTreeNodeAccordingDepth(root);
		String resultStr = TreeNodeUtil.getStringOfTreeNodeAccordingDepth(result);
		System.out.println("expectedStr:" + expectedStr);
		System.out.println("resultStr:" + resultStr);

		Assert.assertEquals(true, expectedStr.equals(resultStr));
	}

	/**
	 * 根据字符串构造树，字符串是树的前序遍历，且节点只有一个子节点，那么保证该子节点为左子节点。
	 * 
	 * @param s
	 * @return
	 */
	private TreeNode recoverTreeFromPreorderTraversalOne(String S) {

		// 加个哨兵
		S = S + '-';
		char[] chars = S.toCharArray();
		int tempLen = 0;
		StringBuilder sb = new StringBuilder();
		boolean lastIsVal = false;

		// 存储路径
		Deque<TreeNode> path = new LinkedList<TreeNode>();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '-') {// 当前字符为-
				if (lastIsVal) { // 上个字符为数字，此处可以存储上一数据了

					if (tempLen == path.size()) {
						TreeNode left = new TreeNode(Integer.parseInt(sb.toString()));
						if (path.size() != 0) {
							path.getLast().left = left;
						}
						path.add(left);
					} else {
						while (tempLen != path.size()) {
							path.pollLast();
						}
						TreeNode right = new TreeNode(Integer.parseInt(sb.toString()));
						path.getLast().right = right;
						path.add(right);
					}

					tempLen = 0;// 清空
					sb.delete(0, sb.capacity());// 清空
					lastIsVal = false;
				}
				tempLen++;
			} else {
				lastIsVal = true;
				sb.append(chars[i]);
			}
		}
		return path.peek();
	}

}
