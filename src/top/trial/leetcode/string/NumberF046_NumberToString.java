package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成
 * “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 
 * 示例 1:
 * 
 * 输入: 12258 输出: 5 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * 
 * 提示：
 * 
 * 0 <= num < 231
 * 
 * @author Gaoyx
 *
 */
public class NumberF046_NumberToString {

	@Test
	public void solution() {

		int num = 12258;
		int expected = 5;
		int result = numberToStringOne(num);
		Assert.assertEquals(expected, result);

	}

	/**
	 * 似乎可以动态规划可以解
	 * 
	 * 对于n>2，当n-1位是1或2，f(n) = f(n-1) + f(n-2);当n-1位不是1或2，f(n) = f(n-1)
	 * 
	 * 
	 * @param num
	 * @return
	 */
	private int numberToStringOne(int num) {

		byte[] nums = (num + "").getBytes();
		int len = nums.length;
		int[] results = new int[len + 1];

		// 铺底数据
		results[0] = 1;
		results[1] = 1;

		int curr = 2;
		while (curr <= len) {
			if (nums[curr - 2] == '1' || (nums[curr - 2] == '2' && (nums[curr - 1] - '6' < 0)))
				results[curr] = results[curr - 1] + results[curr - 2];
			else
				results[curr] = results[curr - 1];
			curr++;
		}
		return results[len];
	}

}
