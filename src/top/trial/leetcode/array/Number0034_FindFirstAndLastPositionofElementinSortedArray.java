package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 
 * 示例 1:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 8 输出: [3,4]
 * 
 * 示例 2:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 6 输出: [-1,-1]
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */
public class Number0034_FindFirstAndLastPositionofElementinSortedArray {

	@Test
	public void solution() {

		int[] nums = { 5, 7, 7, 8, 8, 10 };
		int target = 8;
		int[] expected = { 3, 4 };
		int[] result = findFirstAndLastPositionofElementinSortedArray(nums, target);
		Assert.assertArrayEquals(expected, result);
	}

	private int[] findFirstAndLastPositionofElementinSortedArray(int[] nums, int target) {
		int[] result = { -1, -1 };

		if (nums.length == 0) {
			return result;
		}
		// 二分法寻找左边界
		int left = getBorder(nums, target, true);

		if (left == nums.length || nums[left] != target) {
			return result;
		}

		// 寻找右边界
		int right = getBorder(nums, target, false) - 1;

		result[0] = left;
		result[1] = right;
		return result;
	}

	private int getBorder(int[] nums, int target, boolean flag) {

		int start = 0;
		int end = nums.length - 1;

		while (start <= end) {
			int middle = (start + end + 1) / 2;
			if (nums[middle] > target || (flag && nums[middle] == target)) {
				end = middle - 1;
			} else {
				start = middle + 1;
			}

		}

		return start;
	}

}
