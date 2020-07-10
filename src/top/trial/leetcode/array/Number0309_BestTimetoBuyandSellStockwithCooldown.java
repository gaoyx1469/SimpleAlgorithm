package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 
 * 示例:
 * 
 * 输入: [1,2,3,0,2] 输出: 3 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0309_BestTimetoBuyandSellStockwithCooldown {

	@Test
	public void solution() {
		int[] prices = new int[] { 1, 2, 3, 0, 2 };
		int expected = 3;
		int result = getMaxProfitOne(prices);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 看着像动态规划，怎么写状态转移方程？
	 * 
	 * @param prices
	 * @return
	 */
	private int getMaxProfitOne(int[] prices) {

		int len = prices.length;
		if (len < 2)
			return 0;
		int[] result = new int[len + 1];
		for (int i = 2; i <= len; ++i) {
			int temp = result[i - 1];
			int diff = 0;
			for (int j = i - 2; j >= 0 && (diff = (prices[i - 1] - prices[j])) > 0; --j) {
				if (j > 0)// 前面有个冷冻期
					temp = Math.max(temp, diff + result[j - 1]);
				else// 最开始购买前没有冷冻期
					temp = Math.max(temp, diff + result[j]);
			}
			result[i] = temp;
		}
		return result[len];
	}

	/**
	 * 官方题解，不存在嵌套循环的更简单版本？
	 * 
	 * @param prices
	 * @return
	 */
	public int getMaxProfitTwo(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}

		int n = prices.length;
		int f0 = -prices[0];
		int f1 = 0;
		int f2 = 0;
		for (int i = 1; i < n; ++i) {
			int newf0 = Math.max(f0, f2 - prices[i]);
			int newf1 = f0 + prices[i];
			int newf2 = Math.max(f1, f2);
			f0 = newf0;
			f1 = newf1;
			f2 = newf2;
		}

		return Math.max(f1, f2);
	}
}
