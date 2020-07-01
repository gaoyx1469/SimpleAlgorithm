package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 
 * 示例 1:
 * 
 * 输入: A: [1,2,3,2,1] B: [3,2,1,4,7]
 * 
 * 输出: 3 解释: 长度最长的公共子数组是 [3, 2, 1]。
 * 
 * 说明:
 * 
 * 1 <= len(A), len(B) <= 1000
 * 
 * 0 <= A[i], B[i] < 100
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0718_MaximumLengthofRepeatedSubarray {

	@Test
	public void solution() {
		int[] A = { 1, 2, 3, 2, 1 };
		int[] B = { 3, 2, 1, 4, 7 };
		int expected = 3;
		int result = getMaximumLengthofRepeatedSubarrayOne(A, B);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 第一眼感觉要用动态规划
	 * 
	 * 使用二维数组，表示截断的子数组的最长公共子数组长度
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	private int getMaximumLengthofRepeatedSubarrayOne(int[] A, int[] B) {

		int result = 0;
		int aLen = A.length;
		int bLen = B.length;
		int[][] resultArray = new int[aLen + 1][bLen + 1];// 此处数组默认全部赋值为0
		for (int i = 1; i <= aLen; i++) {
			for (int j = 1; j <= bLen; j++) {
				resultArray[i][j] = A[i - 1] == B[j - 1] ? resultArray[i - 1][j - 1] + 1 : 0;
				result = Math.max(result, resultArray[i][j]);
			}
		}

		return result;
	}

	// TODO 官方题解中另有滑动窗口法（两数组滑动对齐）和二分查找+哈希两种方式

}
