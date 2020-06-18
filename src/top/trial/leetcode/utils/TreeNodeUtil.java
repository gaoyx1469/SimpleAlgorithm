package top.trial.leetcode.utils;

import java.util.ArrayDeque;
import java.util.Queue;

import top.trial.leetcode.tree.TreeNode;

/**
 * TreeNode类的工具类
 * 
 * @author Gaoyx
 *
 */
public class TreeNodeUtil {

	/**
	 * 传入TreeNode根结点，按节点深度输出字符串，没有的节点由null代替
	 * 
	 * @param treeNode
	 * @return
	 */
	public static String getStringOfTreeNodeAccordingDepth(TreeNode root) {

		StringBuilder result = new StringBuilder();

		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

		queue.add(root);
		result.append(root.val + ",");

		while (!queue.isEmpty()) {

			TreeNode treeNode = queue.remove();// 拿到treeNode
			if (treeNode.left != null) {
				result.append(treeNode.left.val + ",");
				queue.add(treeNode.left);
			} else {
				result.append("null,");
			}
			if (treeNode.right != null) {
				result.append(treeNode.right.val + ",");
				queue.add(treeNode.right);
			} else {
				result.append("null,");
			}

		}

		return FormatUtil.addSquareBracketsPlus(result.toString());
	}

}
