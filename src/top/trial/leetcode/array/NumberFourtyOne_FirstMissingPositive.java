package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,0] 输出: 3
 * 
 * 示例 2:
 * 
 * 输入: [3,4,-1,1] 输出: 2
 * 
 * 示例 3:
 * 
 * 输入: [7,8,9,11,12] 输出: 1
 * 
 * 说明:
 * 
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */
public class NumberFourtyOne_FirstMissingPositive {

	@Test
	public void solution() {
		int[] nums = { 3, 4, -1, 1 };
		int expected = 2;

		int result = getFirstMissingPositiveOne(nums);

		Assert.assertEquals(expected, result);
	}

	/**
	 * 要求时间复杂度为O(n) 空间复杂度为O(1)
	 * 
	 * 参考官方题解
	 * 
	 * @param nums
	 * @return
	 */
	private int getFirstMissingPositiveOne(int[] nums) {

		// 看是否有1
		int oneFlag = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 1) {
				oneFlag++;
				break;
			}
		}

		// 没有1，直接返回1即可
		if (oneFlag == 0) {
			return 1;
		}

		// 数组长度为1，返回2
		if (nums.length == 1) {
			return 2;
		}

		// 看是否有大于数组长度的数或者负数3，全部赋值为1
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > nums.length || nums[i] <= 0)
				nums[i] = 1;

		}

		// 遍历，将出现的值的绝对值作为下标，将该下标位置的数值变为负数
		for (int i = 0; i < nums.length; i++) {

			int a = Math.abs(nums[i]);

			// 下标0其实替代了下标nums.length,因为使用nums.length会越界
			if (a == nums.length) {
				nums[0] = -Math.abs(nums[0]);
			} else {
				nums[a] = -Math.abs(nums[a]);
			}
		}

		// 从下标1开始（因为前面已经排除最小未出现数为1的情况）
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > 0)
				return i;
		}
		// 下标0表示的是下标nums.length
		if (nums[0] > 0)
			return nums.length;

		// 以上都未返回，表示从1到nums.length都出现了
		return nums.length + 1;
	}
}
