package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 
 * 示例 1:
 * 
 * 输入: [2,3,-2,4] 输出: 6 解释: 子数组 [2,3] 有最大乘积 6。
 * 
 * 示例 2:
 * 
 * 输入: [-2,0,-1] 输出: 0 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0152_MaximumProductSubarray {

	@Test
	public void solution() {
		int[] nums = { 3, -1, 4 };
		int expected = 4;
		// int result = getMaximumProductSubarrayOne(nums);
		int result = getMaximumProductSubarrayTwo(nums);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 参考力扣官方题解
	 * 
	 * 待研究
	 * 
	 * @param nums
	 * @return
	 */
	private int getMaximumProductSubarrayTwo(int[] nums) {
		int maxF = nums[0], minF = nums[0], ans = nums[0];
		for (int i = 1; i < nums.length; ++i) {
			int mx = maxF, mn = minF;
			maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
			minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
			ans = Math.max(maxF, ans);
		}
		return ans;

	}

	/**
	 * 0作为分隔，段内取最大
	 * 
	 * 该方法有误，弃
	 * 
	 * @param nums2
	 * @return
	 */
	@Deprecated
	private int getMaximumProductSubarrayOne(int[] nums) {

		int temp = 0;
		int valf = 0;
		int valb = 0;
		int maxResult = 0;

		if (nums.length == 1) {
			return nums[0];
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				valf = 0;
				valb = 0;
				temp = 0;
				continue;
			} else if (nums[i] > 0) {
				if (temp == 0) {
					if (valf == 0) {
						valf = nums[i];
					} else {
						valf *= nums[i];
					}
				} else {
					if (valb == 0) {
						valb = nums[i];
					} else {
						valb *= nums[i];
					}
				}
			} else {
				if (temp == 0) {
					temp = nums[i];
				} else {
					if (valf == 0)
						valf = 1;
					if (valb == 0)
						valb = 1;
					valf = valf * temp * valb * nums[i];
					temp = 0;
					valb = 0;
				}
			}
			if (valf > maxResult) {
				maxResult = valf;
			}
			if (valb > maxResult) {
				maxResult = valb;
			}
		}

		return maxResult;
	}

}
