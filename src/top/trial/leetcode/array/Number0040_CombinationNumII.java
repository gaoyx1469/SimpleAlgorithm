package top.trial.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 
 * 说明：
 * 
 * 所有数字（包括目标数）都是正整数。 解集不能包含重复的组合。  示例 1:
 * 
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 
 * 所求解集为: [ [1, 7], [1, 2, 5], [2, 6], [1, 1, 6] ]
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 
 * 所求解集为: [   [1,2,2],   [5] ]
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */
public class Number0040_CombinationNumII {

	@Test
	public void solution() {
		int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
		int target = 8;
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		List<Integer> e1 = new ArrayList<Integer>();
		e1.add(7);
		e1.add(1);
		List<Integer> e2 = new ArrayList<Integer>();
		e2.add(5);
		e2.add(2);
		e2.add(1);
		List<Integer> e3 = new ArrayList<Integer>();
		e3.add(6);
		e3.add(2);
		List<Integer> e4 = new ArrayList<Integer>();
		e4.add(6);
		e4.add(1);
		e4.add(1);
		expected.add(e1);
		expected.add(e2);
		expected.add(e3);
		expected.add(e4);

		List<List<Integer>> result = getCombinationNum(candidates, target);
		System.out.println(result.toString());

		Assert.assertEquals(expected, result);
	}

	private List<List<Integer>> getCombinationNum(int[] candidates, int target) {

		// 排序
		Arrays.parallelSort(candidates);

		// 定义结果集
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		// 定义临时结果集
		List<Integer> temp = new ArrayList<Integer>();

		// 主体方法
		combinationNum(candidates, target, result, temp);

		// 去重
		Set<List<Integer>> set = new HashSet<List<Integer>>(result);
		result = new ArrayList<List<Integer>>(set);

		return result;
	}

	private void combinationNum(int[] candidates, int target, List<List<Integer>> result, List<Integer> temp) {
		// 小于等于target的数组
		int[] lowerThanTarget = getTinyArray(candidates, target);

		// 数组为空，则直接返回
		if (lowerThanTarget.length == 0)
			return;

		// 从尾部遍历
		for (int i = lowerThanTarget.length - 1; i >= 0; i--) {

			List<Integer> res = new ArrayList<Integer>();
			res.addAll(temp);
			res.add(lowerThanTarget[i]);

			if (lowerThanTarget[i] == target) {
				result.add(res);
			} else {
				// 找到小于等于lowerThanTarget[i]的数组，且不包含当前lowerThanTarget[i]
				combinationNum(Arrays.copyOfRange(lowerThanTarget, 0, i), target - lowerThanTarget[i], result, res);
			}
		}
	}

	/**
	 * 返回数组中小于target的部分,由于candidates从小到大排序，因此从小开始遍历，遇到大于target的部分就直接return前面的数据即可
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	private int[] getTinyArray(int[] candidates, int target) {

		for (int i = 0; i < candidates.length; i++) {
			if (candidates[i] > target) {
				return Arrays.copyOfRange(candidates, 0, i);
			}
		}

		return candidates;
	}

}
