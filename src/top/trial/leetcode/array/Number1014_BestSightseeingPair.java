package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 * 
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 * 
 * 返回一对观光景点能取得的最高分。
 * 
 * 示例：
 * 
 * 输入：[8,1,5,2,6] 输出：11 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 =
 * 11  
 * 
 * 提示：
 * 
 * 2 <= A.length <= 50000
 * 
 * 1 <= A[i] <= 1000
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/best-sightseeing-pair
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number1014_BestSightseeingPair {

	@Test
	public void solution() {
		int[] A = { 8, 1, 5, 2, 6 };
		int expected = 11;
		// int result = getBestSightseeingPairOne(A);
		int result = getBestSightseeingPairTwo(A);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 暴力法时间复杂度太高，尝试优化
	 * 
	 * 对A[i] + A[j] + i - j分解为A[i] + i 和 A[j] - j
	 * 
	 * 对于小于j的所有i，只需在遍历时记住最大的A[i] + i，并即时计算最大的A[i] + i与当前遍历的A[j] - j之和即可
	 * 
	 * @param a
	 * @return
	 */
	private int getBestSightseeingPairTwo(int[] A) {

		int result = 0;
		int temp = A[0];

		for (int i = 1; i < A.length; i++) {
			result = Math.max(result, temp + A[i] - i);// 得到目前为止最大的A[i] + i + A[j] - j（i<j）
			temp = Math.max(temp, A[i] + i);// 得到目前为止最大的A[i] + i
		}
		return result;
	}

	/**
	 * 暴力法破解，时间复杂度n^2
	 * 
	 * @param a
	 * @return
	 */
	private int getBestSightseeingPairOne(int[] A) {

		int result = 0;

		for (int i = 0; i < A.length - 1; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (A[i] + A[j] + i - j > result)
					result = A[i] + A[j] + i - j;
			}
		}

		return result;
	}

}
