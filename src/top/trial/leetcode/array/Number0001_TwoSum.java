package top.trial.leetcode.array;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 力扣数组题1：两数之和
 * 
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 
 * 示例:
 * 
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 
 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */
public class Number0001_TwoSum {

	@Test
	public void solution() {
		int[] nums = { 2, 7, 11, 15 };
		int target = 9;
		int[] expected = { 0, 1 };

		// int[] result = methodOne(nums, target);
		int[] result = methodTwo(nums, target);

		Assert.assertArrayEquals(expected, result);

	}

	/**
	 * 暴力破解法，两层嵌套循环，时间复杂度O(n*n),空间复杂度O(1) 显然这是一种能得到正确答案的最差方法-_-
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unused")
	private int[] methodOne(int[] nums, int target) {
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if ((nums[i] + nums[j]) == target) {
					return new int[] { j, i };
				}
			}
		}
		return null;
	}

	/**
	 * 以空间换时间
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	private int[] methodTwo(int[] nums, int target) {

		// 定义哈希表，以数组中的值为key，以对应数组下标为value，则可将一层循环改变为哈希表查找
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int j = 0; j < nums.length; j++) {
			int difference = target - nums[j];
			if (map.containsKey(difference) && map.get(difference) != j) {
				return new int[] { j, map.get(difference) };
			}
		}

		return null;
	}
}
