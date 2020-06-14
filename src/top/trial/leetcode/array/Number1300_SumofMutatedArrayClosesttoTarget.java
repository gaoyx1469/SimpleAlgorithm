package top.trial.leetcode.array;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value
 * 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 * 
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 * 
 * 请注意，答案不一定是 arr 中的数字。
 * 
 *  
 * 
 * 示例 1：
 * 
 * 输入：arr = [4,9,3], target = 10 输出：3 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9
 * ，这是最接近 target 的方案。
 * 
 * 示例 2：
 * 
 * 输入：arr = [2,3,5], target = 10 输出：5
 * 
 * 示例 3：
 * 
 * 输入：arr = [60864,25176,27249,21296,20204], target = 56803 输出：11361  
 * 
 * 提示：
 * 
 * 1 <= arr.length <= 10^4 1 <= arr[i], target <= 10^5
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx-vm
 *
 */
public class Number1300_SumofMutatedArrayClosesttoTarget {

	@Test
	public void solution() {
		int[] arr = { 2, 3, 5 };
		int target = 10;
		int expected = 5;
		int result = getSumofMutatedArrayClosesttoTargetOne(arr, target);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 暴力法破解
	 * 
	 * @param arr
	 * @param target
	 * @return
	 */
	private int getSumofMutatedArrayClosesttoTargetOne(int[] arr, int target) {

		int len = arr.length;

		int average = target % len > len / 2 ? target / len + 1 : target / len;

		Arrays.sort(arr);// 排序

		if (arr[0] > average) {
			return average;
		}

		int sum = 0;
		for (int i = 0; i < len - 1; i++) {
			sum += arr[i];
			if ((target - sum) / (len - i - 1) > arr[i + 1])
				continue;
			// 此处判断返回(target-sum)/(len-i-1)还是(target-sum)/(len-i-1)+1
			return (target - sum) % (len - i - 1) > (len - i - 1) / 2 ? (target - sum) / (len - i - 1) + 1
					: (target - sum) / (len - i - 1);
		}

		return arr[len - 1];
	}

}
