package top.trial.leetcode.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 /
 * 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 
 * 示例: 
 * 
 * 你可以将以下二叉树：
 * 
 * 1 / \ 2 3 / \ 4 5
 * 
 * 序列化为 "[1,2,3,null,null,4,5]" 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode
 * 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0297_SerializeandDeserializeBinaryTree {

	@Test
	public void solution() {
		String data = "1,2,null,null,3,4,null,null,5,null,null,";
		TreeNode root = new TreeNode(1);
		TreeNode tn2 = new TreeNode(2);
		TreeNode tn3 = new TreeNode(3);
		TreeNode tn4 = new TreeNode(4);
		TreeNode tn5 = new TreeNode(5);
		tn3.left = tn4;
		tn3.right = tn5;
		root.left = tn2;
		root.right = tn3;

		TreeNode tn = deserialize(data);
		String str = serialize(tn);
		System.out.println(str);

		Assert.assertEquals(true, data.equals(str));
	}

	private String serialize(TreeNode root) {

		// 增加参数，递归操作
		return getSerializeStr(root, "");
	}

	private String getSerializeStr(TreeNode root, String str) {

		if (root == null) {
			str += "null,";
		} else {
			str += root.val + ",";
			str = getSerializeStr(root.left, str);
			str = getSerializeStr(root.right, str);
		}
		return str;
	}

	private TreeNode deserialize(String data) {
		String[] data_array = data.split(",");
		List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
		return getDeserializeTree(data_list);
	}

	private TreeNode getDeserializeTree(List<String> data_list) {
		if (data_list.get(0).equals("null")) {
			data_list.remove(0);
			return null;
		}

		TreeNode root = new TreeNode(Integer.valueOf(data_list.get(0)));
		data_list.remove(0);
		root.left = getDeserializeTree(data_list);
		root.right = getDeserializeTree(data_list);
		return root;
	}
}
