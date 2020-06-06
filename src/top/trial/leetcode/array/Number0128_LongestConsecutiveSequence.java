package top.trial.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 
 * 要求算法的时间复杂度为 O(n)。
 * 
 * 示例:
 * 
 * 输入: [100, 4, 200, 1, 3, 2] 输出: 4 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx-vm
 *
 */
public class Number0128_LongestConsecutiveSequence {

	@Test
	public void solution() {
		int[] nums = { 100, 4, 200, 1, 3, 2 };
		int expected = 4;
		// int result = getLongestConsecutiveSequenceOne(nums);
		int result = getLongestConsecutiveSequenceTwo(nums);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 方法One中Arrays.sort(nums)方法就已经时nlog(n)的时间复杂度了，此处需要有不排序就可以的计数的方法。另外没有空间复杂度要求，是不是有
	 * 
	 * 链表？
	 * 
	 * 参考蠡口官方题解，使用set
	 * 
	 * @param nums
	 * @return
	 */
	private int getLongestConsecutiveSequenceTwo(int[] nums) {

		int result = 0;
		// set去重
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++)
			set.add(nums[i]);

		for (int num : set) {
			// 遍历set，看是否有等于num-1的数字，有则不管，原理时所有序列都从最小开始计数
			if (!set.contains(num - 1)) {// 找到序列起始值
				int sequence = num;// 序列值
				int tempLen = 1;
				while (set.contains(sequence + 1)) {
					sequence++;
					tempLen++;
				}
				result = Math.max(result, tempLen);
			}
		}

		return result;

	}

	/**
	 * 先不管时间复杂度O(n) 的要求，则可先排序，后遍历一次计数即可
	 * 
	 * @param nums
	 * @return
	 */
	private int getLongestConsecutiveSequenceOne(int[] nums) {

		// 前面是否需要去重
		Arrays.sort(nums);
		int count = 1;
		int result = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1] + 1) {
				count++;
				result = Math.max(result, count);
			} else
				count = 1;
		}

		return result;
	}
}