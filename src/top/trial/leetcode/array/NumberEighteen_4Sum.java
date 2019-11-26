package top.trial.leetcode.array;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c
 * + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 
 * 注意：
 * 
 * 答案中不可以包含重复的四元组。
 * 
 * 示例：
 * 
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 
 * 满足要求的四元组集合为： [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class NumberEighteen_4Sum {

	@Test
	public void solution() {
		int[] nums = { 1, 0, -1, 0, -2, 2 };
		int target = 0;
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		List<Integer> ex1 = new ArrayList<Integer>();
		ex1.add(-1);
		ex1.add(0);
		ex1.add(0);
		ex1.add(1);

		List<Integer> ex2 = new ArrayList<Integer>();
		ex1.add(-2);
		ex1.add(-1);
		ex1.add(1);
		ex1.add(2);

		List<Integer> ex3 = new ArrayList<Integer>();
		ex1.add(-2);
		ex1.add(0);
		ex1.add(0);
		ex1.add(2);

		expected.add(ex1);
		expected.add(ex2);
		expected.add(ex3);

		List<List<Integer>> result = get3SumClosestOne(nums, target);

		Assert.assertEquals(expected, result);

	}

	private List<List<Integer>> get3SumClosestOne(int[] nums, int target) {
		return null;
	}

}
