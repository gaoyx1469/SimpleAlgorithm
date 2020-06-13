package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * 注意：给定 n 是一个正整数。
 * 
 * 示例 1：
 * 
 * 输入： 2 输出： 2 解释： 有两种方法可以爬到楼顶。 1. 1 阶 + 1 阶 2. 2 阶
 * 
 * 示例 2：
 * 
 * 输入： 3 输出： 3 解释： 有三种方法可以爬到楼顶。 1. 1 阶 + 1 阶 + 1 阶 2. 1 阶 + 2 阶 3. 2 阶 + 1 阶
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx-vm
 *
 */
public class Number0070_ClimbingStairs {

	@Test
	public void solution() {
		int n = 3;
		int expected = 3;
		// int result = climbingStairsOne(n);
		int result = climbingStairsTwo(n);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 优化方案
	 * 
	 * @param n
	 * @return
	 */
	private int climbingStairsTwo(int n) {

		int[] result = new int[n + 1];
		result[0] = 0;
		result[1] = 1;
		result[2] = 2;

		for (int i = 3; i < n + 1; i++)
			result[i] = result[i - 1] + result[i - 2];
		return result[n];
	}

	/**
	 * 很明显的动态规划题,但是这样有大量的重复计算
	 * 
	 * @param n
	 * @return
	 */
	private int climbingStairsOne(int n) {

		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		return climbingStairsOne(n - 1) + climbingStairsOne(n - 2);
	}

}
