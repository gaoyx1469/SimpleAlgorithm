package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 * 
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。
 * 每次抽取都是独立的，其结果具有相同的概率。
 * 
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 * 
 * 示例 ：
 * 
 * 输入：N = 21, K = 17, W = 10 输出：0.73278
 * 
 * 提示：
 * 
 * 0 <= K <= N <= 10000 1 <= W <= 10000 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
 * 此问题的判断限制时间已经减少。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/new-21-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0837_New21Game {

	@Test
	public void solution() {

		int N = 21;
		int K = 17;
		int W = 10;
		double expected = 0.73278;
		double result = getResultOne(N, K, W);
		Assert.assertEquals(expected, result, 0.00001);
	}

	/**
	 * 动态规划问题，参考官方题解，拓展【tinyNote--算法--2_动态规划分析】
	 * 
	 * @param N
	 * @param K
	 * @param W
	 * @return
	 */
	private double getResultOne(int N, int K, int W) {
		if (K == 0) {
			return 1.0;
		}
		double[] dp = new double[K + W];
		for (int i = K; i <= N && i < K + W; i++) {
			dp[i] = 1.0;
		}
		dp[K - 1] = 1.0 * Math.min(N - K + 1, W) / W;
		for (int i = K - 2; i >= 0; i--) {
			dp[i] = dp[i + 1] - (dp[i + W + 1] - dp[i + 1]) / W;
		}
		return dp[0];
	}
}
