package top.trial.leetcode.array;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 
 * 示例 1 :
 * 
 * 输入:nums = [1,1,1], k = 2 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 
 * 说明 :
 * 
 * 数组的长度为 [1, 20,000]。 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0560_SubarraySumEqualsK {

	@Test
	public void solution() {
		int[] nums = { 1, 1, 1 };
		int k = 2;
		int expected = 2;
		// int result = getSubarraySumEqualsKOne(nums, k);
		int result = getSubarraySumEqualsKTwo(nums, k);

		Assert.assertEquals(expected, result);
	}

	/**
	 * 优化方法，由于暴力法时间复杂度不满足要求，对其进行优化 参考力扣官方那个题解，前缀和+哈希表优化
	 * 
	 * 前缀和思想是只需计算一次从头开始到每一位的和，当0到i的和与0到j的和之间的差为k时，可知i+1到j的和为k
	 * 
	 * 哈希表优化是使用哈希表，在计算从头开始到每一位的和的时候，直接判断是否存在某一位，使得前缀和思想成立
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	private int getSubarraySumEqualsKTwo(int[] nums, int k) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 1);
		int sum = 0;
		int count = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (map.containsKey(sum - k)) // sum-k已在map中
				count += map.get(sum - k);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}

		return count;
	}

	/**
	 * 暴力法，一次通过 时间复杂度O(n^2)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	private int getSubarraySumEqualsKOne(int[] nums, int k) {

		int len = nums.length;

		int result = 0;

		for (int i = 0; i < len; i++) {
			int sum = 0;
			for (int j = i; j < len; j++) {
				sum = sum + nums[j];
				if (sum == k)
					result++;
			}
		}

		return result;
	}

}
