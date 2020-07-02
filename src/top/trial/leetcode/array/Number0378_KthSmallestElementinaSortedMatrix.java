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
		int result = getKthSmallestElementinaSortedMatrixOne(matrix, k);
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

		// 使用二分法找到某个值X，计算矩阵中有多少个值不大于X，如果数量不少于k，说明结果应不大于X；如果数量少于k，说明结果大于X。

		// 计算矩阵中有多少个值不大于X这步操作，可以单独拎出来，从左下角走起，如果矩阵中值小于等于X，则往右移动；如果大于X，则累加当前位置值并向上移动。

		return 0;
	}
}
