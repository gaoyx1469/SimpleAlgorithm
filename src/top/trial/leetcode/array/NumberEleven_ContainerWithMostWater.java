package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线
 * i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 
 * 示例:
 * 
 * 输入: [1,8,6,2,5,4,8,3,7] 输出: 49
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class NumberEleven_ContainerWithMostWater {

	@Test
	public void solution() {
		int[] nums = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		int expected = 49;

		// int result = getMaxContainerOne(nums);
		int result = getMaxContainerTwo(nums);

		Assert.assertEquals(expected, result);
	}

	/**
	 * 暴力破解法。。。没价值，时间复杂度O(n*n)
	 * 
	 * @param nums
	 * @return
	 */
	private int getMaxContainerOne(int[] nums) {
		int maxContainer = 0;
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if ((i - j) * Math.min(nums[i], nums[j]) > maxContainer) {
					maxContainer = (i - j) * Math.min(nums[i], nums[j]);
				}
			}
		}
		return maxContainer;
	}

	/**
	 * 官方推荐的双指针法，时间复杂度O(n) 关键在于双指针法的原理
	 * 每次循环舍弃短的一个边
	 * 
	 * @param nums
	 * @return
	 */
	private int getMaxContainerTwo(int[] nums) {
		int maxContainer = 0;
		int i = 0;
		int j = nums.length - 1;

		while (i < j) {
			if ((j - i) * Math.min(nums[i], nums[j]) > maxContainer) {
				maxContainer = (j - i) * Math.min(nums[i], nums[j]);
			}
			if (nums[i] < nums[j]) {
				i++;
			} else {
				j--;
			}
		}

		return maxContainer;
	}
}
