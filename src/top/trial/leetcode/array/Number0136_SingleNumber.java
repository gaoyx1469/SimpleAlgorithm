package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 
 * 说明：
 * 
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 
 * 示例 1:
 * 
 * 输入: [2,2,1] 输出: 1
 * 
 * 示例 2:
 * 
 * 输入: [4,1,2,1,2] 输出: 4
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0136_SingleNumber {

	@Test
	public void solution() {
		int[] nums = { 4, 1, 2, 1, 2 };
		int expected = 4;
		int result = getSingleNumberOne(nums);

		Assert.assertEquals(expected, result);
	}

	/**
	 * 要求具有线性时间复杂度，不使用额外空间 异或运算，相同的数异或运算结果为0，因此所有数异或后的结果就是多出来的那个数
	 * 
	 * @param nums
	 * @return
	 */
	private int getSingleNumberOne(int[] nums) {

		if (nums.length == 1) {
			return nums[0];
		}

		for (int i = 1; i < nums.length; i++) {
			nums[0] = nums[0] ^ nums[i];
		}

		return nums[0];
	}

}
