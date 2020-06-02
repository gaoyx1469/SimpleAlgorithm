package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * 
 * 1 <= n <= 10000
 * 
 * @author Gaoyx
 *
 */
public class NumberF064_NumberSum {

	@Test
	public void solution() {
		int n = 9;
		int expected = 45;
		int result = getNumberSum(n);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 结果是：(n + n^2)/2
	 * 
	 * 啥都不让用，咋算。。。
	 * 
	 * 参考官方题解，使用递归替代for循环，使用逻辑运算替代判断
	 * 
	 * @param n
	 * @return
	 */
	private int getNumberSum(int n) {
		// int sum = 0;

		// 使用for循环（for循环内有判断）
		// for (int i = n; i > 0; i--) {
		// sum = sum + i;
		// }

		// 使用递归替代for循环后
		// if(n > 0)
		// sum = n + getNumberSum(n-1);
		// return sum;

		// 使用三元运算替代if判断后
		// return n == 0 ? n : n + getNumberSum(n - 1);

		// 使用逻辑运算符代替三元运算符后
		boolean flag = n > 0 && (n += getNumberSum(n - 1)) > 0;
		return n;

	}

}
