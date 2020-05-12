package top.trial.leetcode.array;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 
 * 将图像顺时针旋转 90 度。
 * 
 * 说明：
 * 
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 
 * 示例 1:
 * 
 * 给定 matrix = [ [1,2,3], [4,5,6], [7,8,9] ],
 * 
 * 原地旋转输入矩阵，使其变为: [ [7,4,1], [8,5,2], [9,6,3] ]
 * 
 * 示例 2:
 * 
 * 给定 matrix = [ [ 5, 1, 9,11], [ 2, 4, 8,10], [13, 3, 6, 7], [15,14,12,16] ],
 * 
 * 原地旋转输入矩阵，使其变为: [ [15,13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7,10,11] ]
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */
public class Number0048_RotateImage {

	@Test
	public void solution() {
		int[][] matrix = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };

		int[][] expected = { { 15, 13, 2, 5 }, { 14, 3, 4, 1 }, { 12, 6, 8, 9 }, { 16, 7, 10, 11 } };

		rotate(matrix);

		Assert.assertArrayEquals(expected, matrix);
		System.out.println(Arrays.deepToString(matrix));
	}

	// 将二维数组分成四部分，循环遍历即可，时间复杂度O(n^2)
	private void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int x = 0; x < n / 2; x++) {
			for (int y = x; y < n - 1 - x; y++) {
				int temp = matrix[y][x];
				matrix[y][x] = matrix[n - 1 - x][y];
				matrix[n - 1 - x][y] = matrix[n - 1 - y][n - 1 - x];
				matrix[n - 1 - y][n - 1 - x] = matrix[x][n - 1 - y];
				matrix[x][n - 1 - y] = temp;
			}
		}
	}
}
