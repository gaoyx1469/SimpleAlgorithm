package top.trial.leetcode.array;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 
 * 必须原地修改，只允许使用额外常数空间。
 * 
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。 1,2,3 → 1,3,2 || 3,2,1 → 1,2,3 || 1,1,5 → 1,5,1
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */
public class NumberThirtyOne_NextPermutation {

	@Test
	public void solution() {
		int[] nums = { 1, 3, 7, 5, 2 };
		int[] expected = { 1, 5, 2, 3, 7 };

		nextPermutationOne(nums);

		Assert.assertArrayEquals(expected, nums);
	}

	/**
	 * 首先理解“字典序”
	 * 
	 * 从后向前依次判断是否倒序，若全是倒序，则反转，若某一位是正序，则将前一位与后面倒序中大于它的最小数字互换，然后后面排成正序即可。
	 * 
	 * @param nums
	 */
	private void nextPermutationOne(int[] nums) {

		for (int i = nums.length - 1; i > 0; i--) {
			if (nums[i - 1] < nums[i]) {// 出现正序

				// 排序i到nums.length-1部分
				Arrays.parallelSort(nums, i, nums.length);

				// 将比nums[i-1]大的最小元素与其互换
				for (int j = i; j <= nums.length - 1; j++) {
					if (nums[j] > nums[i - 1] && nums[j - 1] <= nums[i - 1]) {
						int temp = nums[i - 1];
						nums[i - 1] = nums[j];
						nums[j] = temp;
						return;
					}
				}
			}
		}
		Arrays.sort(nums);
	}
}
