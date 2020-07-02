package top.trial.leetcode.array;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 * 
 * 示例：
 * 
 * matrix = [ [ 1, 5, 9], [10, 11, 13], [12, 13, 15] ], k = 8,
 * 
 * 返回 13。  
 * 
 * 提示： 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n^2 。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0378_KthSmallestElementinaSortedMatrix {

	@Test
	public void solution() {
		int[][] matrix = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
		int k = 8;
		int expected = 13;
		// int result = getKthSmallestElementinaSortedMatrixOne(matrix, k);
		int result = getKthSmallestElementinaSortedMatrixThree(matrix, k);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 每行每列都升序的二维数组，暴力法
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	private int getKthSmallestElementinaSortedMatrixOne(int[][] matrix, int k) {

		int n = matrix.length;
		// 弄成一个一位数组
		int[] newArray = new int[n * n];
		int temp = 0;
		// 排序
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++, temp++) {
				newArray[temp] = matrix[i][j];

			}
		}
		Arrays.parallelSort(newArray);
		return newArray[k - 1];
	}

	/**
	 * 参考官方题解，使用归并排序
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	private int getKthSmallestElementinaSortedMatrixTwo(int[][] matrix, int k) {

		// 归并排序的思想就是多个有序数组排序，可参考Number0023
		// n个有序数组
		// TODO

		return 0;
	}

	/**
	 * 参考官方题解，使用二分查找
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	private int getKthSmallestElementinaSortedMatrixThree(int[][] matrix, int k) {

		// 确定二分法的上下边界为左上和右下的值
		int len = matrix.length;
		int min = matrix[0][0];
		int max = matrix[len - 1][len - 1];
		int x = (min + max) / 2;

		// 使用二分法找到某个值X，计算矩阵中有多少个值不大于X，如果数量不少于k，说明结果应不大于X；如果数量少于k，说明结果大于X。
		while (min != max) {
			if (getNumLessThanX(matrix, x) < k) {
				min = x + 1;
			}
			// else if (getNumLessThanX(matrix, x) == k) {
			// break;
			// }
			else {
				max = x;
			}
			x = (min + max) / 2;
		}

		// 计算矩阵中有多少个值不大于X这步操作，可以单独拎出来，从左下角走起，如果矩阵中值小于等于X，则往右移动；如果大于X，则累加当前位置值并向上移动。

		// 找到矩阵中小于x的最大值
		// 根据官方题解，这一步可以优化，当前面不break而是将后两个合并，最终直接返回min即可
		// int result = matrix[0][0];
		// for (int i = 0; i < len; i++) {
		// for (int j = 0; j < len; j++) {
		// if (matrix[i][j] == x)
		// return x;
		// if (matrix[i][j] < x && matrix[i][j] > result)
		// result = matrix[i][j];
		// }
		// }
		// return result;
		return min;
	}

	// 计算矩阵中有多少个值不大于X这步操作，可以单独拎出来，从左下角走起，如果矩阵中值小于等于X，则往右移动；如果大于X，则累加当前位置值并向上移动。
	private int getNumLessThanX(int[][] matrix, int x) {

		int i = matrix.length - 1;
		int j = 0;
		int sum = 0;

		while (i >= 0 && j <= matrix.length - 1) {
			if (matrix[i][j] <= x && j < matrix.length - 1) {
				j++;
			} else if (matrix[i][j] <= x && j == matrix.length - 1) {
				sum += matrix.length * (i + 1);
				return sum;
			} else if (matrix[i][j] > x) {
				i--;
				sum += j;
			}
		}
		return sum;
	}
}
