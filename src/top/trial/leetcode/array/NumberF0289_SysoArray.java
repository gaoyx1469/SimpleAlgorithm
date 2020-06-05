package top.trial.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * 
 * 示例 1：
 * 
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]] 输出：[1,2,3,6,9,8,7,4,5]
 * 
 * 示例 2：
 * 
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]  
 * 
 * 限制：
 * 
 * 0 <= matrix.length <= 100 0 <= matrix[i].length <= 100
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class NumberF0289_SysoArray {

	@Test
	public void solution() {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		int[] expected = { 1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7 };
		int[] result = sysoArrayOne(matrix);
		System.out.println(Arrays.toString(result));
		Assert.assertArrayEquals(expected, result);
	}

	/**
	 * 
	 * @param matrix
	 * @return
	 */
	private int[] sysoArrayOne(int[][] matrix) {

		// 获取矩阵短边
		int m = matrix.length;
		if (m == 0)
			return new int[0];
		int n = matrix[0].length;
		if (n == 0)
			return new int[0];

		int minSide = Math.min(m, n);

		int len = m * n;

		int x = 0;
		int y = 0;
		int[] result = new int[len];

		while (x < minSide / 2) {
			for (int i = x; i < n - x - 1; i++, y++)
				result[y] = matrix[x][i];
			for (int i = x; i < m - x - 1; i++, y++)
				result[y] = matrix[i][n - x - 1];
			for (int i = n - x - 1; i > x; i--, y++)
				result[y] = matrix[m - x - 1][i];
			for (int i = m - x - 1; i > x; i--, y++)
				result[y] = matrix[i][x];
			x++;
		}
		if (minSide % 2 == 1) {
			if (m > n) {// 行大于列
				for (int i = x; i < m - x; i++, y++)
					result[y] = matrix[i][x];
			} else {
				for (int i = x; i < n - x; i++, y++)
					result[y] = matrix[x][i];
			}
		}

		return result;
	}

}
