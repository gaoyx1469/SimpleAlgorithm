package top.trial.leetcode.math;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 
 * 示例 1:
 * 
 * 输入: 121 输出: true
 * 
 * 示例 2:
 * 
 * 输入: -121 输出: false 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 
 * 示例 3:
 * 
 * 输入: 10 输出: false 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 
 * 进阶:
 * 
 * 你能不将整数转为字符串来解决这个问题吗？
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0009_PalindromeNumber {

	@Test
	public void solution() {
		int x = 121;
		boolean expected = true;
		// boolean result = isPalindromeNumberOne(x);
		// boolean result = isPalindromeNumberTwo(x);
		boolean result = isPalindromeNumberThree(x);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 研究官方那个题解，得到第三种解法
	 * 
	 * 额外空间是一个int变量
	 * 
	 * @param x
	 * @return
	 */
	private boolean isPalindromeNumberThree(int x) {

		if (x < 0 || (x % 10 == 0 && x != 0))
			return false;

		int reversedNum = 0;

		// 例：将12321分成12和123
		while (x > reversedNum) {
			reversedNum = reversedNum * 10 + x % 10;
			x /= 10;
		}

		return x == reversedNum || x == reversedNum / 10;
	}

	/**
	 * 进阶要求是不将整数转为字符串来解决这个问题
	 * 
	 * 额外空间是一个长度10的int数组
	 * 
	 * @param x
	 * @return
	 */
	private boolean isPalindromeNumberTwo(int x) {

		if (x < 0)
			return false;

		boolean result = true;

		// 构造数组
		int[] nums = new int[10];
		int i = 0;
		for (; x != 0; i++) {
			nums[i] = x % 10;
			x /= 10;
		}

		int j = 0;
		while (j < i - 1 - j) {
			if (nums[j] != nums[i - 1 - j]) {
				result = false;
				break;
			}
			j++;
		}
		return result;
	}

	/**
	 * 先不管进阶要求，题目明显提示，反转字符串比较是否相等就行
	 * 
	 * @param x
	 * @return
	 */
	private boolean isPalindromeNumberOne(int x) {

		StringBuilder original = new StringBuilder(x + "");
		StringBuilder newStr = new StringBuilder(x + "").reverse();
		return original.compareTo(newStr) == 0;

	}

}
