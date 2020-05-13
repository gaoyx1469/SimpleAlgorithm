package top.trial.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的数字可以无限制重复被选取。
 * 
 * 说明：
 * 
 * 所有数字（包括 target）都是正整数。 解集不能包含重复的组合。  示例 1:
 * 
 * 输入: candidates = [2,3,6,7], target = 7, 所求解集为: [ [7], [2,2,3] ]
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,3,5], target = 8, 所求解集为: [   [2,2,2,2],   [2,3,3],  
 * [3,5] ]
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */
public class Number0039_CombinationNum {

	@Test
	public void solution() {
		int[] candidates = { 2, 3, 6, 7 };
		int target = 7;
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		List<Integer> e1 = new ArrayList<Integer>();
		e1.add(7);
		List<Integer> e2 = new ArrayList<Integer>();
		e2.add(3);
		e2.add(2);
		e2.add(2);
		expected.add(e1);
		expected.add(e2);

		List<List<Integer>> result = getCombinationNum(candidates, target);
		System.out.println(result.toString());

		Assert.assertEquals(expected, result);
	}

	private List<List<Integer>> getCombinationNum(int[] candidates, int target) {

		// 数组先排序
		Arrays.parallelSort(candidates);

		// 定义结果集
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> temp = new ArrayList<Integer>();

		// 主体方法
		combinationNum(candidates, target, result, temp);

		return result;
	}

	/**
	 * 寻找candidates数组中小于或等于target的值组成的数组，target依次减去数组中的值，得到新的target以及小于等于减掉值的数组
	 * 
	 * @param candidates
	 * @param target
	 * @param result
	 * @param temp
	 */
	private void combinationNum(int[] candidates, int target, List<List<Integer>> result, List<Integer> temp) {
		// 找到数组中等于target或小于target的最大值
		int[] tempArray = getTinyArray(candidates, target);
		if (tempArray.length == 0)
			return;
		// 从大到小遍历元素，递归调用自身，判断是否有解集可以得到target-元素的情况
		for (int i = tempArray.length - 1; i >= 0; i--) {
			List<Integer> res = new ArrayList<Integer>();
			res.addAll(temp);
			res.add(tempArray[i]);
			if (target == tempArray[i]) {// 恰好相等
				result.add(res);
			} else {
				int[] tempArrayL = getTinyArray(candidates, tempArray[i]);
				combinationNum(tempArrayL, target - tempArray[i], result, res);
			}

		}

	}

	/**
	 * 返回数组中小于target的部分
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
