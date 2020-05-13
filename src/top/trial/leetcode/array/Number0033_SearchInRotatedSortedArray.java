package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 
 * 你可以假设数组中不存在重复的元素。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 示例 1:
 * 
 * 输入: nums = [4,5,6,7,0,1,2], target = 0 输出: 4
 * 
 * 示例 2:
 * 
 * 输入: nums = [4,5,6,7,0,1,2], target = 3 输出: -1
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */
public class Number0033_SearchInRotatedSortedArray {

	@Test
	public void solution() {

		int[] nums = { 8, 9, 2, 3, 4 };
		int target = 9;
		int expected = 1;
		int result = searchInRotatedSortedArrayOne(nums, target);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 有序数组在某一位处分裂，两边顺序发生颠倒
	 * 
	 * 二分法找最小值的位置
	 * 
	 * 判断目标值位于哪个区间，二分法区间内寻找
	 * 
	 * @param nums
	 * @return
	 */
	private int searchInRotatedSortedArrayOne(int[] nums, int target) {

		int start = 0;
		int end = nums.length - 1;

		if (end < 0) {
			return -1;
		}
		if (end == 0) {
			return nums[end] == target ? 0 : -1;
		}

		int pivot = getPivot(nums, start, end);
		System.out.println(pivot);

		if (nums[end] >= target || pivot == 0) {
			return search(nums, pivot, end, target);
		} else if (nums[start] <= target) {
			return search(nums, start, pivot - 1, target);
		}
		return -1;
	}

	/**
	 * 有序数组二分法查找
	 * 
	 * @param nums
	 * @param pivot
	 * @param end
	 * @param target
	 * @return
	 */
	private int search(int[] nums, int start, int end, int target) {

		if (nums[start] == target) {
			return start;
		} else if (nums[end] == target) {
			return end;
		}

		int middle = (start + end + 1) / 2;

		while (start <= end) {
			if (nums[middle] == target) {
				return middle;
			}
			if (nums[middle] < target) {
				start = middle + 1;
			} else {
				end = middle - 1;
			}
			middle = (start + end + 1) / 2;
		}

		return -1;
	}

	/**
	 * 寻找数组最小值
	 * 
	 * @param nums
	 * @param start
	 * @param end
	 * @return
	 */
	private int getPivot(int[] nums, int start, int end) {

		int middle = (start + end + 1) / 2;
		while (start < end) {
			if (nums[start] < nums[end]) {
				return start;
			}
			if (nums[middle] > nums[end]) {
				start = middle + 1;
			} else if (nums[middle] < nums[end]) {
				end = middle;
			} else {
				return end;
			}
			middle = (start + end + 1) / 2;
		}

		return end;
	}
}
