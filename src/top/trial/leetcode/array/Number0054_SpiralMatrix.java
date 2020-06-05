package top.trial.leetcode.array;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 
 * 示例 1:
 * 
 * 输入: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] 输出: [1,2,3,6,9,8,7,4,5]
 * 
 * 示例 2:
 * 
 * 输入: [ [1, 2, 3, 4], [5, 6, 7, 8], [9,10,11,12] ] 输出:
 * [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * 此题与NumberF029相同
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0054_SpiralMatrix {

	@Test
	public void solution() {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		expected.add(4);
		expected.add(8);
		expected.add(12);
		expected.add(11);
		expected.add(10);
		expected.add(9);
		expected.add(5);
		expected.add(6);
		expected.add(7);
		List<Integer> result = sysoArrayOne(matrix);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 
	 * @param matrix
	 * @return
	 */
	private List<Integer> sysoArrayOne(int[][] matrix) {

		// 获取矩阵短边
		int m = matrix.length;
		if (m == 0)
			return new ArrayList<Integer>();
		int n = matrix[0].length;
		if (n == 0)
			return new ArrayList<Integer>();

		int minSide = Math.min(m, n);

		int x = 0;
		List<Integer> result = new ArrayList<Integer>();

		while (x < minSide / 2) {
			for (int i = x; i < n - x - 1; i++)
				result.add(matrix[x][i]);
			for (int i = x; i < m - x - 1; i++)
				result.add(matrix[i][n - x - 1]);
			for (int i = n - x - 1; i > x; i--)
				result.add(matrix[m - x - 1][i]);
			for (int i = m - x - 1; i > x; i--)
				result.add(matrix[i][x]);
			x++;
		}
		if (minSide % 2 == 1) {
			if (m > n) {// 行大于列
				for (int i = x; i < m - x; i++)
					result.add(matrix[i][x]);
			} else {
				for (int i = x; i < n - x; i++)
					result.add(matrix[x][i]);
			}
		}

		return result;
	}

}
