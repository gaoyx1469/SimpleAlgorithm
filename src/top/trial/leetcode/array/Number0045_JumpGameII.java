package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 
 * 示例:
 * 
 * 输入: [2,3,1,1,4] 输出: 2 解释: 跳到最后一个位置的最小跳跃数是 2。   从下标为 0 跳到下标为 1
 * 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 
 * 说明:
 * 
 * 假设你总是可以到达数组的最后一个位置。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0045_JumpGameII {
	@Test
	public void solution() {
		int[] nums = { 2, 3, 1, 1, 4 };
		int expected = 2;

		// int result = getMinStepOne(nums);
		int result = getMinStepTwo(nums);

		Assert.assertEquals(expected, result);
	}

	/**
	 * 自己想的算法，内存占用小，用时太长，时间复杂度O(n^2)，空间复杂度O(1)
	 * 
	 * @param nums
	 * @return
	 */
	@SuppressWarnings("unused")
	private int getMinStepOne(int[] nums) {

		int nextStepIndex = nums.length - 1;
		int stepNum = 0;

		while (nextStepIndex > 0) {
			nextStepIndex = getNextStep(nums, nextStepIndex);
			stepNum++;
		}

		return stepNum;
	}

	// 获取能到达nextStepIndex处的最远点的下标并返回
	private int getNextStep(int[] nums, int nextStepIndex) {
		int farestIndex = nextStepIndex - 1;
		for (int i = nextStepIndex - 1; i >= 0; i--) {
			if (i + nums[i] >= nextStepIndex) {
				farestIndex = i;
			}
		}
		return farestIndex;
	}

	/**
	 * 参考题解，从前往后的贪心算法，时间复杂度O(n),空间复杂复O(1)
	 * 
	 * @param nums
	 * @return
	 */
	private int getMinStepTwo(int[] nums) {
		int end = 0;
		int maxPosition = 0;
		int steps = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			// 找能跳的最远的
			maxPosition = Math.max(maxPosition, nums[i] + i);
			if (i == end) { // 遇到边界，就更新边界，并且步数加一
				end = maxPosition;
				steps++;
			}
		}
		return steps;
	}

}
