package top.trial.leetcode.array;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个包括 n 个整数的数组 nums 和
 * 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class NumberSixteen_3SumClosest {

	@Test
	public void solution() {
		int[] nums = { 1, 1, -1, -1, 3 };
		int target = -1;
		int expected = -1;

		// int result = get3SumClosestOne(nums, target);
		int result = get3SumClosestTwo(nums, target);

		Assert.assertEquals(expected, result);

	}

	/**
	 * 暴力法，三层循环遍历，时间复杂度O(n*n*n)
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	private int get3SumClosestOne(int[] nums, int target) {

		int result = Integer.MAX_VALUE;

		for (int x = 2; x < nums.length; x++) {
			for (int y = 1; y < x; y++) {
				for (int z = 0; z < y; z++) {
					if (Math.abs(nums[x] + nums[y] + nums[z] - target) < Math.abs(result)) {
						result = nums[x] + nums[y] + nums[z] - target;
					}
				}
			}
		}

		return result + target;
	}

	/**
	 * 采用3数之和的方法，先排序，然后遍历小于目标值的元素，双指针法得到三数之和与目标值最接近的。
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	private int get3SumClosestTwo(int[] nums, int target) {

		Arrays.sort(nums);//O(nlogn)

		int result = Integer.MAX_VALUE;
		int temp;
		int start;
		int end;

		for (int i = 0; i < nums.length - 2; i++) {
			start = i + 1;
			end = nums.length - 1;
			while (start < end) {
				temp = nums[i] + nums[start] + nums[end];
				if (Math.abs(temp - target) < Math.abs(result - target) || (result == Integer.MAX_VALUE)) {
					result = temp;
				}

				if (temp - target < 0) {
					start++;
				} else if (temp - target > 0) {
					end--;
				} else {
					break;
				}
			}
		}

		return result;
	}
}
