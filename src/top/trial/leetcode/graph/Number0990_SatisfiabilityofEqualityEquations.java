package top.trial.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b"
 * 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * 
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
 * 
 * 示例 1：输入：["a==b","b!=a"] 输出：false 解释：如果我们指定，a = 1 且 b =
 * 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 * 
 * 示例 2： 输出：["b==a","a==b"] 输入：true 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
 * 
 * 示例 3：输入：["a==b","b==c","a==c"] 输出：true
 * 
 * 示例 4：输入：["a==b","b!=c","c==a"] 输出：false
 * 
 * 示例 5：输入：["c==c","b==d","x!=z"] 输出：true  
 * 
 * 提示：
 * 
 * 1 <= equations.length <= 500
 * 
 * equations[i].length == 4
 * 
 * equations[i][0]和 equations[i][3] 是小写字母
 * 
 * equations[i][1] 要么是 '='，要么是 '!'
 * 
 * equations[i][2] 是 '='
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0990_SatisfiabilityofEqualityEquations {

	@Test
	public void solution() {

		// String[] equations = { "a==b", "b!=c", "c==a" };
		String[] equations = { "a!=a" };
		Boolean expected = false;
		Boolean result = getSatisfiabilityofEqualityEquationsOne(equations);
		Assert.assertEquals(expected, result);

	}

	/**
	 * 等式成图，循环不等式看是否满足
	 * 
	 * @param equations
	 * @return
	 */
	private Boolean getSatisfiabilityofEqualityEquationsOne(String[] equations) {

		// 循环拿到等式组
		int len = equations.length;

		List<List<String>> graph = new ArrayList<List<String>>();
		List<String> differEquations = new ArrayList<String>();

		for (int i = 0; i < len; i++) {
			if ("=".equals(equations[i].substring(1, 2))) {// 等式
				// 入图
				insertIntoGraph(equations[i], graph);
			} else
				differEquations.add(equations[i]);
		}

		// 遍历differEquations，看是否存在问题
		for (String str : differEquations) {
			String left = str.substring(0, 1);
			String right = str.substring(3);
			if (left.equals(right))
				return false;
			for (List<String> list : graph) {
				if (list.contains(left) && list.contains(right))
					return false;
			}
		}

		return true;
	}

	/**
	 * 入图操作
	 * 
	 * @param string
	 * @param graph
	 */
	private void insertIntoGraph(String str, List<List<String>> graph) {

		if (graph == null || graph.isEmpty()) {// 原图为空
			List<String> list = new ArrayList<String>();
			list.add(str.substring(0, 1));
			list.add(str.substring(3));
			graph.add(list);
		} else {// 原图不为空
			String left = str.substring(0, 1);
			String right = str.substring(3);

			// 看left和right是否在图中
			Boolean isLeftInGraph = false;
			Boolean isRightInGraph = false;
			List<String> leftList = null;
			List<String> rightList = null;
			for (List<String> list : graph) {
				if (list.contains(left)) {
					isLeftInGraph = true;
					leftList = list;
				}
				if (list.contains(right)) {
					isRightInGraph = true;
					rightList = list;
				}
			}

			if (isLeftInGraph && isRightInGraph) {// 都满足，在同一子图则不处理，在不同子图合并
				if (leftList != rightList) {// 在不同子图
					graph.remove(rightList);
					graph.remove(leftList);
					leftList.addAll(rightList);
					graph.add(leftList);
				}
			} else if (isLeftInGraph) // left存在，将right入图
				leftList.add(right);
			else if (isRightInGraph) // right存在，将left入图
				rightList.add(left);
			else {// 都不存在，新建子图
				List<String> newSubGraph = new ArrayList<String>();
				newSubGraph.add(left);
				newSubGraph.add(right);
				graph.add(newSubGraph);
			}
		}
	}

}
