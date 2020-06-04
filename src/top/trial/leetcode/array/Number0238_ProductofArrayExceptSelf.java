package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中
 * output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 
 * 示例:
 * 
 * 输入: [1,2,3,4] 输出: [24,12,8,6]  
 * 
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 
 * 进阶： 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0238_ProductofArrayExceptSelf {

	@Test
	public void solution() {
		int[] nums = { 1, 2, 3, 4 };
		int[] expected = { 24, 12, 8, 6 };
		int[] result = getProductofArrayExceptSelfOne(nums);
		Assert.assertArrayEquals(expected, result);
	}

	/**
	 * 要求是不要使用除法，且在 O(n) 时间复杂度内完成，乘积都在 32 位整数范围内
	 * 
	 * 使用前缀积和后缀积。
	 * 
	 * @param nums
	 * @return
	 */
	private int[] getProductofArrayExceptSelfOne(int[] nums) {

		// 前缀
		int[] pre = new int[nums.length];

		// 后缀
		int[] suf = new int[nums.length];

		// 结果
		int[] result = new int[nums.length];

		for (int i = 0, k = 1; i < nums.length; i++) {
			k = k * nums[i];
			pre[i] = k;
		}

		for (int i = nums.length - 1, k = 1; i > 0; i--) {
			k = k * nums[i];
			suf[i] = k;
		}

		result[0] = suf[1];
		if (nums.length > 1)
			result[nums.length - 1] = pre[nums.length - 2];

		for (int i = 1; i < nums.length - 1; i++) {
			result[i] = pre[i - 1] * suf[i + 1];
		}

		return result;
	}

}
