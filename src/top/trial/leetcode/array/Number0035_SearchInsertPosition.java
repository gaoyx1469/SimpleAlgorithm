package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 
 * 你可以假设数组中无重复元素。
 * 
 * 示例 1:
 * 
 * 输入: [1,3,5,6], 5 输出: 2
 * 
 * 示例 2:
 * 
 * 输入: [1,3,5,6], 2 输出: 1
 * 
 * 示例 3:
 * 
 * 输入: [1,3,5,6], 7 输出: 4
 * 
 * 示例 4:
 * 
 * 输入: [1,3,5,6], 0 输出: 0
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */
public class Number0035_SearchInsertPosition {

	@Test
	public void solution() {

		int[] nums = { 1, 3, 5, 6 };
		int target = 0;
		int expected = 0;
		int result = searchInsertPositione(nums, target);
		Assert.assertEquals(expected, result);

	}

	/**
	 * 双指针寻找等于目标值的点，若未找到，放到小于
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	private int searchInsertPositione(int[] nums, int target) {

		int start = 0;
		int end = nums.length - 1;

		/* 特殊处理空集合 */
		if (end < 0) {
			return 0;
		}

		/* 处理目标值在数组范围外的情况 */
		if (nums[start] >= target) {
			return 0;
		} else if (nums[end] == target) {
			return end;
		} else if (nums[end] < target) {
			return end + 1;
		}

		int middle = (start + end) / 2;

		/* 双指针找到目标值点，或逼近目标值点【最终middle与start相等，end等于start+1】 */
		while (start < middle) {
			if (target == nums[middle]) {
				return middle;
			} else if (target > nums[middle]) {
				start = middle;
			} else {
				end = middle;
			}
			middle = (start + end) / 2;
		}
		
		
		return end;

	}

}
